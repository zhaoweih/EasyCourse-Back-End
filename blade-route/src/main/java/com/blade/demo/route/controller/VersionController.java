package com.blade.demo.route.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;

/**
 * @author zhaoweihao
 * @date 2019/01/05
 */
@Path("api")
public class VersionController {

    @GetRoute("version")
    @JSON
    public String hello(){
        return "@Version : 1.1.7-9 alpha @date 2019/3/26";
    }

}
