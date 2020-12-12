package com.blade.demo.route.test;/*
 * Copyright (c) 2010-2019 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.blade.demo.route.model.chat.Chat;
import com.blade.demo.route.model.chat.ChatRequestBean;
import com.blade.demo.route.util.GsonUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
/**
 * 聊天服务器端
 */
public class ChatServer extends WebSocketServer {

    /**
     * 连接对象集合
     */
    private List<WebSocket> webSockets = new ArrayList<>();

    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //添加连接对象
        webSockets.add(conn);
//        conn.send("Welcome to the server!"); //This method sends a message to the new client
//        broadcast("new connection: " + handshake.getResourceDescriptor()); //This method sends a message to all clients connected
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");


    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //删除连接对象
        webSockets.remove(conn);
//        broadcast(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("onMessage message === " + message);
        ChatRequestBean chatRequestBean = GsonUtil.GsonToBean(message, ChatRequestBean.class);

        if (chatRequestBean.getInfoType() == 0) {
            //首次进入，发送聊天记录
            List<Chat> chats = new Chat().where("course_id", chatRequestBean.getClassId()).findAll();
            chatRequestBean.setData(chats);
            conn.send(GsonUtil.GsonString(chatRequestBean));
            return;
        }

        if (chatRequestBean.getInfoType() == 2) {
            //好友聊天首次进入，发送聊天记录
            List<Chat> allChats = new ArrayList<>();
            List<Chat> chats_1 = new Chat().where("sender_id", chatRequestBean.getUserId()).and("receiver_id", chatRequestBean.getFriendId()).findAll();
            List<Chat> chats_2 = new Chat().where("receiver_id", chatRequestBean.getUserId()).and("sender_id", chatRequestBean.getFriendId()).findAll();
            allChats.clear();
            allChats.addAll(chats_1);
            allChats.addAll(chats_2);
            allChats.sort(Comparator.comparing(Chat::getMsg_time));
            chatRequestBean.setData(allChats);
            conn.send(GsonUtil.GsonString(chatRequestBean));
            return;
        }

        if (chatRequestBean.getInfoType() == 1) {
            //群聊发送消息
            //保存聊天记录到数据库
            System.out.println("data === " + chatRequestBean.getData().toString());
            Chat chat = GsonUtil.GsonToBean(chatRequestBean.getData().toString(), Chat.class);
            try {
                chat.setMsg_time(System.currentTimeMillis());
                chat.save();
            } catch (Exception e) {

            }
            broadcast(GsonUtil.GsonString(chatRequestBean));
        }

        if (chatRequestBean.getInfoType() == 3) {
            //好友聊天发送消息
            Chat chat = GsonUtil.GsonToBean(chatRequestBean.getData().toString(), Chat.class);
            try {
                chat.setMsg_time(System.currentTimeMillis());
                chat.save();
            } catch (Exception e) {

            }
            broadcast(GsonUtil.GsonString(chatRequestBean));
        }



    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
//        broadcast(message.array());
        System.out.println(conn + ": " + message);
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
        }
        ChatServer s = new ChatServer(port);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("启动websocket成功");
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

}
