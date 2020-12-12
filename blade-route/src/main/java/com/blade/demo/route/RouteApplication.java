package com.blade.demo.route;

import com.blade.Blade;
import com.blade.demo.route.test.ChatServer;

import java.io.IOException;

/**
 * @author zhaoweihao
 * @date 2017/9/28
 */
public class RouteApplication {

    public static void main(String[] args) {
        //启动blade
        Blade.me()
                //测试版更改端口为9001
                .listen(9001)
                .showFileList(true)
                .start(RouteApplication.class, args);

        //启动Java-WebSocket
//        System.out.println("启动websocket");
//        try {
//            ChatServer.main(null);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

}
