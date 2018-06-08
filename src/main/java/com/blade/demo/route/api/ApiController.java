package com.blade.demo.route.api;


import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;

import java.util.ArrayList;
import java.util.List;

@Path
public class ApiController {
    @GetRoute("api")
    @JSON
    public Api getApi(){
        Api api = new Api();
        api.setPrefix("http://zhaoweihao.com:9001/api/");

        String[] methods = {"post", "post","get", "post", "get", "post",
        "post", "post", "get", "post", "get", "get",
                "post", "get"};
        String[] suffixs = {"user/login", "user/register", "seat/get", "seat/post", "leave/query", "leave/submit",
        "leave/confirm", "course/submit", "course/query", "course/select", "course/querySelectByStuId", "course/querySelectByCourseId",
        "noti/sendCourseNoti", "noti/queryCourseNotiByCourseId"};
        String[] descriptions = {"登录功能", "注册功能", "获取点名座位表数据", "提交点名座位表数据", "查询请假条", "提交请假条",
        "确认请假条状态", "老师提交课程", "查询课程信息", "学生选课", "查看学生选课纪录", "查看课程被选纪录",
        "老师发送通知", "查询课程通知"};
        String[] params = {null, null, null, null, "@Param studentId,@Param teacherId", null,
                null, "@Param int tecId,@Param String teacherId,@Param String courseName", "@Param courseName,@Param teacherId", "@Param int courseId,@Param int stuId,@Param String studentId", "@Param stuId", "@Param courseId",
        null, "@Param courseId"};

        List<Rest> restList = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            Rest rest = new Rest();
            rest.setMethod(methods[i]);
            rest.setSuffix(suffixs[i]);
            rest.setDescription(descriptions[i]);
            rest.setParam(params[i]);
            restList.add(rest);
        }

        api.setRestList(restList);

        return api;




    }


}
