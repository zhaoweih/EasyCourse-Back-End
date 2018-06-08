package com.blade.demo.route;

import com.blade.Blade;

/**
 * @author biezhi
 * @date 2017/9/28
 */
public class RouteApplication {

    public static void main(String[] args) {
        Blade.me()
                .listen(9001)//测试版更改端口为9001
                .showFileList(true)
                .start(RouteApplication.class, args);
    }

}
