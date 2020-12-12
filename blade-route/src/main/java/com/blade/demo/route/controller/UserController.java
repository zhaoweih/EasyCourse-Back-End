package com.blade.demo.route.controller;

import com.blade.demo.route.model.Auth;
import com.blade.demo.route.model.Reset;
import com.blade.demo.route.model.user.Password;
import com.blade.demo.route.model.user.Update;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.util.*;
import com.blade.demo.route.util.oauth.ApiKey;
import com.blade.demo.route.util.oauth.ExpiredException;
import com.blade.demo.route.util.oauth.OAtuthUtil;
import com.blade.demo.route.model.Login;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import javax.jws.soap.SOAPBinding;
import java.util.List;


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
    public RestResponse getJson(@Param int studentId) {
        User user = new User();
        User find = user.where("id", studentId).find();
        if (find == null) {
            return RestResponse.fail(500, "找不到这个用户");
        } else {
            return RestResponse.ok(find, 200);
        }

    }

    /**
     * 是否是新版本用户
     *
     * @param username
     * @return
     */
    @GetRoute("is_new_user")
    @JSON
    public RestResponse isNewUser(@Param String username) {
        try {
            User user = new User().where("username", username).find();

            if (StringUtils.isEmpty(user.getPassword())) {
                return RestResponse.ok(true, 200);
            }

            return RestResponse.ok(false, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 升级用户账号
     * @param username
     * @return
     */
    @GetRoute("upgrade_account")
    @JSON
    public RestResponse upgradeAccount(@Param String username) {
        try {
            User user = new User().where("username", username).find();

            if (!StringUtils.isEmpty(user.getPassword())) {
                String salt = "6NCkDWrVJy5K9v2w";
                String md5Password = EncryptUtils.encryptMD5ToString(EncryptUtils.encryptMD5ToString(user.getPassword()) + salt);
                user.setMd5_password(md5Password);
                user.setPassword("");
                user.where("username", username).update();
                return RestResponse.ok("升级用户数据成功", 200);
            } else {
                return RestResponse.fail(400, "用户已经是新账户");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

//    /**
//     * 升级所有用户账号
//     * @param
//     * @return
//     */
//    @GetRoute("upgrade_all_account")
//    @JSON
//    public RestResponse upgradeAllAccount() {
//        try {
//            List<User> user = new User().findAll();
//
//            for (User userObject : user) {
//                if (!StringUtils.isEmpty(userObject.getPassword())) {
//                    String salt = "6NCkDWrVJy5K9v2w";
//                    String md5Password = EncryptUtils.encryptMD5ToString(EncryptUtils.encryptMD5ToString(userObject.getPassword()) + salt);
//                    userObject.setMd5_password(md5Password);
//                    userObject.setPassword("");
//                    userObject.where("username", userObject.getUsername()).update();
////                    return RestResponse.ok("升级用户数据成功", 200);
//                } else {
////                    return RestResponse.fail(400, "用户已经是新账户");
//                }
//            }
//
//            return RestResponse.ok("升级用户数据成功", 200);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return RestResponse.fail(400, e.getMessage());
//        }
//    }


    /**
     * 登录
     * 测试账号：zhaowei0
     * 测试密码:abbccc
     *
     * @param login
     * @return
     */
    @PostRoute("login")
    @JSON
    public RestResponse login(@BodyParam Login login) {
        if (login == null) return RestResponse.fail(ApiKey.BAD_REQUEST);
        if (login.getUsername() == null) return RestResponse.fail(ApiKey.BAD_REQUEST, "用户名不能为空");
        if (login.getPassword() == null) return RestResponse.fail(ApiKey.BAD_REQUEST, "密码不能为空");
        User loginUser = new User().where("username", login.getUsername()).find();
        if (loginUser == null)
            return RestResponse.fail(ApiKey.UNAUTHORIZED, "用户名不存在");
        String token;
        String expiredDate;
        boolean isPasswordMatch;
        if (StringUtils.isEmpty(loginUser.getPassword())) {
            //密码是md5加密的
            isPasswordMatch = StringUtils.equals(loginUser.getMd5_password(), login.getPassword());
        } else {
            isPasswordMatch = StringUtils.equals(loginUser.getPassword(), login.getPassword());
        }
        if (isPasswordMatch) {
            loginUser.setPassword("******");//返回前清除密码
            loginUser.setMd5_password("******");
            try {
                token = OAtuthUtil.createJWT(new Gson().toJson(loginUser));
                expiredDate = OAtuthUtil.parseJWT(token).getExpiration().toString();
            } catch (Exception e) {
                e.printStackTrace();
                return RestResponse.fail(ApiKey.SERVER_ERROR, "Token加密异常");
            }
            return RestResponse.ok(new Auth(token, loginUser, expiredDate), 200);
        } else {
            return RestResponse.fail(ApiKey.UNAUTHORIZED, "密码错误");
        }
    }

    @GetRoute("tokenparse")
    @JSON
    public RestResponse parseToken(@Param String token) {
        Claims claims = null;
        try {
            claims = OAtuthUtil.parseJWT(token);
            System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());
            return RestResponse.ok(claims.getSubject(), 200);
        } catch (ExpiredException e) {
            e.printStackTrace();
            return RestResponse.fail(500, e.getMessage());
        }
    }

    @PostRoute("register")
    @JSON
    public RestResponse register(@BodyParam User user) {
        System.out.println(GsonUtils.toJson(user));
        if (user == null) return RestResponse.fail(ApiKey.BAD_REQUEST);
        if (user.getUsername() == null) return RestResponse.fail(ApiKey.BAD_REQUEST, "用户名不能为空");
        boolean isPasswordEmpty = StringUtils.isEmpty(user.getPassword()) && StringUtils.isEmpty(user.getMd5_password());
        if (isPasswordEmpty) {
            return RestResponse.fail(ApiKey.BAD_REQUEST, "密码不能为空");
        }
//        if (user.getPassword() == null) return RestResponse.fail(ApiKey.BAD_REQUEST, "密码不能为空");
        boolean isUsername = ZWHUtil.validateUserName(user.getUsername());
        if (!StringUtils.isEmpty(user.getPassword())) {
            if (!ZWHUtil.validatePassword(user.getPassword())) {
                return RestResponse.fail(ApiKey.BAD_REQUEST, "密码不符合要求");
            }
        }
//        boolean isPassword = ZWHUtil.validatePassword(user.getPassword());
        if (!isUsername) return RestResponse.fail(ApiKey.BAD_REQUEST, "用户名不符合要求");
//        if (!isPassword) return RestResponse.fail(ApiKey.BAD_REQUEST, "密码不符合要求");
        if (StringUtils.isEmpty(user.getStudentId()) && StringUtils.isEmpty(user.getTeacherId())) {
            return RestResponse.fail(ApiKey.BAD_REQUEST, "请填写教师编号或学号");
        }
        boolean isStudentId = user.getStudentId() != null && user.getStudentId().length() > 10;
        boolean isTeacherId = user.getTeacherId() != null && user.getTeacherId().length() > 10;
        if (isStudentId) return RestResponse.fail(ApiKey.BAD_REQUEST, "学号不能超过10位");
        if (isTeacherId) return RestResponse.fail(ApiKey.BAD_REQUEST, "教师编号不能超过10位");
        User registerUser = new User().where("username", user.getUsername()).find();
        if (registerUser != null)
            return RestResponse.fail(ApiKey.CONFLICT, "用户名已存在");
        try {
            user.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(ApiKey.SERVER_ERROR, e.getMessage());
        }

    }

    /*
    修改个人信息
     */
    @PostRoute("modify")
    @JSON
    public RestResponse modifyUser(@BodyParam Update update) {
        try {
            User updateUser = new User();
            updateUser.setStudentId(update.getStudentId());
            updateUser.setTeacherId(update.getTeacherId());
            updateUser.setClassId(update.getClassId());
            updateUser.setDepartment(update.getDepartment());
            updateUser.setEducation(update.getEducation());
            updateUser.setDate(update.getDate());
            updateUser.setSchool(update.getSchool());
            updateUser.setSex(update.getSex());
            updateUser.setName(update.getName());
            updateUser.setPhone(update.getPhone());
            updateUser.setDescrition(update.getDescrition());
            updateUser.where("id", update.getId()).update();
            return RestResponse.ok(updateUser, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
//        return RestResponse.ok(null, 200);

    }

    /**
     * 修改头像
     *
     * @param id
     * @param avatarUrl
     * @return
     */
    @GetRoute("modifyAvatar")
    @JSON
    public RestResponse modifyAvatar(@Param Integer id, @Param String avatarUrl) {
        System.out.println("id === " + id + "avatar === " + avatarUrl);
        try {
            User user = new User().where("id", id).find();
            if (user == null) {
                return RestResponse.fail(400, "用户不存在");
            }

            user.setAvatar(avatarUrl);
            user.where("id", id).update();

            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(500, e.getMessage());
        }
    }


    /**
     * 获取用户头像
     *
     * @param id
     * @return
     */
    @GetRoute("getAvatar")
    @JSON
    public RestResponse getAvatar(@Param Integer id) {
        System.out.println("id = " + id);
        try {
            User user = new User().where("id", id).find();
            if (user == null) {
                return RestResponse.fail("用户不存在");

            }

            return RestResponse.ok(user.getAvatar(), 200);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 修改账户密码
     *
     * @param password
     * @return
     */
    @PostRoute("modifyUserPwd")
    @JSON
    public RestResponse modifyUserPwd(@BodyParam Password password) {
        try {
            User oldUser = new User().where("id", password.getId()).find();
            //md5密码
            if (StringUtils.isEmpty(oldUser.getPassword())) {
                if (StringUtils.equals(oldUser.getMd5_password(), password.getOldPassword())) {
                    //密码验证通过
                    User newUser = new User();
                    newUser.setMd5_password(password.getNewPassword());
                    newUser.where("id", password.getId()).update();
                    return RestResponse.ok("修改密码成功", 200);
                } else {
                    return RestResponse.fail(ApiKey.BAD_REQUEST, "旧密码不正确");
                }
            }
            //旧的明文密码
            if (oldUser.getPassword().equals(password.getOldPassword())) {//密码验证通过
                User newUser = new User();
//                newUser.setMd5_password(password.getNewPassword());
                newUser.setPassword(password.getNewPassword());
                newUser.where("id", password.getId()).update();
                return RestResponse.ok(null, 200);
            } else {
                return RestResponse.fail(ApiKey.BAD_REQUEST, "旧密码不正确");
            }
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    /**
     * 重置密码
     *
     * @param reset
     * @return
     */
    @PostRoute("reset_password")
    @JSON
    public RestResponse resetPassword(@BodyParam Reset reset) {
        try {
            User user = new User().where("username", reset.getUsername()).find();
            boolean hasUser = user != null;

            if (!hasUser) {
                return RestResponse.fail(400, "没有该用户");
            }

            boolean matchAnswer = user.getAnswer().equals(reset.getAnswer());

            if (!matchAnswer) {
                return RestResponse.fail(400, "答案不正确");
            }

            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(reset.getNew_password());
            } else {
                user.setMd5_password(reset.getNew_password());
            }

            user.where("username", reset.getUsername()).update();

            return RestResponse.ok(null, 200);


        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 获取用户验证问题
     *
     * @param username
     * @return
     */
    @GetRoute("get_user_question")
    @JSON
    public RestResponse getUserQuestion(@Param String username) {
        try {
            User user = new User().where("username", username).find();
            boolean hasUser = user != null;

            if (!hasUser) {
                return RestResponse.fail(400, "没有该用户");
            }

            return RestResponse.ok(user.getQuestion(), 200);


        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 通过id查询用户
     *
     * @param id
     * @param token
     * @return
     */
    @GetRoute("get_user_by_id")
    @JSON
    public RestResponse getUserByName(@Param int id, @Param String token) {
        try {

            OAtuthUtil.parseJWT(token);

            User user = new User().where("id", id).find();

            if (user == null) {
                return RestResponse.fail(400, "没有该用户");
            }

            return RestResponse.ok(user, 200);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 通过名字查询用户
     *
     * @param username
     * @param token
     * @return
     */
    @GetRoute("get_user_by_username")
    @JSON
    public RestResponse getUserByName(@Param String username, @Param String token) {
        try {

            OAtuthUtil.parseJWT(token);

            User user = new User().where("username", username).find();

            if (user == null) {
                return RestResponse.fail(400, "没有该用户");
            }

            return RestResponse.ok(user, 200);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
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
