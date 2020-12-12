package com.blade.demo.route.controller;

import com.blade.demo.route.model.broadcast.TeacherBroadcast;
import com.blade.demo.route.model.chat.Chat;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * 聊天系统
 * @author zhaoweihao
 * @date 2019/1/9
 */
@Path("api/chat")
public class ChatSysController {


    /**
     * 发送消息
     * @param chat
     * @return
     */
    @PostRoute("send")
    @JSON
    public RestResponse sendMsg (@BodyParam Chat chat) {
       try {
           chat.setMsg_time(System.currentTimeMillis());
           chat.save();
           return RestResponse.ok(null, 200);
       } catch (Exception e) {
           return RestResponse.fail(400, e.getMessage());
       }
    }


    /**
     * 获得私密消息 个人对个人
     * @param sender_id
     * @param receiver_id
     * @return
     */
    @GetRoute("get_personal_msgs")
    @JSON
    public RestResponse getPersonalMsgs(@Param String sender_id, @Param String receiver_id) {
        try {
            System.out.println("sender_id === " + sender_id + "receiver_id" + receiver_id);
            Chat chat = new Chat();
            List<Chat> chats = new ArrayList<>();
            List<Chat> chats1 = chat.like("sender_id", sender_id).and("receiver_id", receiver_id).findAll();
            List<Chat> chats2 = chat.like("sender_id", receiver_id).and("receiver_id", sender_id).findAll();
            chats.clear();
            chats.addAll(chats1);
            chats.addAll(chats2);
            //按照id排序
            chats.sort(Comparator.comparing(Chat::getId));
            System.out.println(" chats1 === " + chats1 + "chats2 === " + chats2 + "chats === " + chats);
            return RestResponse.ok(chats, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 获取课程聊天信息
     * @param course_id
     * @return
     */
    @GetRoute("get_group_msgs")
    @JSON
    public RestResponse getGroupMsgs(@Param String course_id) {
        try {
            List<Chat> groupChats = new Chat().where("course_id", course_id).findAll();
            return RestResponse.ok(groupChats, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

}
