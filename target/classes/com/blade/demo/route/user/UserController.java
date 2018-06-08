package com.blade.demo.route.user;

import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

/**
 * @author biezhi
 * @date 2017/9/28
 */
@Path("api/user")
public class UserController {
//    StringBuilder sb=new StringBuilder();
//
//    @PostRoute("save")
//    @JSON
//    public RestResponse saveUser(@Param String username, @Param String password){
//        System.out.println("username => " + username);
//        System.out.println("password => " + password);
//        return RestResponse.ok();
//    }
//
//    @PostRoute("save-by-model")
//    @JSON
//    public RestResponse saveUserByModel(@BodyParam User user){
//        System.out.println("username => " + user.getUsername());
//        System.out.println("password => " + user.getPassword());
//        return RestResponse.ok();
//    }
//
//    @PostRoute("test")
//    @JSON
//    public RestResponse register(@Param String test) {
//
//        if(test!=null){
//            return RestResponse.ok(test);
//        }
//        else {
//            return RestResponse.fail("数据不能为空");
//        }
//    }

//    @GetRoute("get")
//    public void get(Response response){
//        User       user  = new User();
//        List<User> users = user.findAll();
//
//        for (int i =0;i<users.size();i++){
//            sb.append(users.get(i).getUsername());
//
//        }
//
//        response.text(sb.toString());
//
//
//    }

    @GetRoute("query")
    @JSON
    public RestResponse getJson(@Param int studentId){
        User user  = new User();
        User find = user.where("id", studentId).find();
        if (find == null) {
            return RestResponse.fail(500, "找不到这个用户");
        } else {
            return RestResponse.ok(find, 200);
        }

    }

    @PostRoute("login")
    @JSON
    public RestResponse login(@BodyParam Login login) {
        if (login.getUsername() == null || login.getPassword() == null) {
            return RestResponse.fail(500,"用户名或者密码不能为空");
        }

        User user = new User();
        System.out.print(login.getUsername());
        if (user.where("username", login.getUsername()).find() == null)
            return RestResponse.fail(500,"没有找到这个用户名");

        User find = user.where("username", login.getUsername()).find();

        if (find.getPassword().equals(login.getPassword())) {
            return RestResponse.ok(find,200);
        }

        System.out.print(find.getPassword());

        return null;

    }

    @PostRoute("register")
    @JSON
    public RestResponse register(@BodyParam User user) {

        /**
         * @id id int
         * @username 用户名
         * @password 密码
         * @studentId 学号
         * @teacherId 教师编号
         * @classId 班级编号
         * @department 学院
         * @education 学历 int
         * @date 入学时间
         * @school 学校
         * @sex 性别 int
         * @name 真实姓名
         */

        User user2 = new User();

        if (user.getUsername() == null || user.getPassword() == null) {
            return RestResponse.fail(500,"用户名或者密码不能为空");
        }

        if (user2.where("username",user.getUsername()).find() != null ) {
            return RestResponse.fail(500,"用户名已存在");
        }

        if (user.getStudentId() == null && user.getTeacherId() == null) {
            return RestResponse.fail(500,"学号或者教师编号不能为空");
        }

        user.save();

        return RestResponse.ok(null,200);

    }

//    @PostRoute("post/delete")
//    @JSON
//    public RestResponse delete(@BodyParam Delete delete){
//        User user = new User();
//
//        user.where("id",delete.getId()).delete();
//
//        return RestResponse.ok();
//    }
//
//    @PostRoute("post/update")
//    @JSON
//    public RestResponse update(@BodyParam Update update) {
//        User user = new User();
//
//        user.setUsername(update.getUsername());
//
//        user.where("id",update.getId()).update();
//
//        return RestResponse.ok();
//    }



}
