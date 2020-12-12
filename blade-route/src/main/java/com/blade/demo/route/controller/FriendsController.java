package com.blade.demo.route.controller;

import com.blade.demo.route.model.friends.Friends;
import com.blade.demo.route.model.friends.Request;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.util.GsonUtils;
import com.blade.demo.route.util.oauth.ExpiredException;
import com.blade.demo.route.util.oauth.OAtuthUtil;
import com.blade.jdbc.page.Page;
import com.blade.jdbc.page.PageRow;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.List;

/**
 * 好友
 *
 * @author zhaowiehao
 * @date 2019/3/5
 */
@Path("api/friends")
public class FriendsController {

    /**
     * 发送好友邀请
     *
     * @param from
     * @param to
     * @return
     */
    @GetRoute("send_friend_request")
    @JSON
    public RestResponse sendFriendRequest(@Param String from, @Param String to, @Param String token) {
        try {
            //验证token正确性
            OAtuthUtil.parseJWT(token);

            User user = new User().where("username", from).find();
            if (user == null) {
                return RestResponse.fail(400, "用户不存在");
            }
            User user1 = new User().where("username", to).find();
            if (user1 == null) {
                return RestResponse.fail(400, "用户不存在");
            }

            Request request = new Request();
            request.setFrom_username(from);
            request.setTo_username(to);
            request.setTime(System.currentTimeMillis());
            request.setStatus(0);
            request.setIs_confirmed(0);

            System.out.println(GsonUtils.toJson(request));
            request.save();


            return RestResponse.ok("发送邀请成功", 200);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 设置状态
     *
     * @param request_id
     * @param status
     * @param token
     * @return
     */
    @GetRoute("set_request_status/:request_id")
    @JSON
    public RestResponse setRequestState(@PathParam Integer request_id, @Param Integer status, @Param String token) {
        try {
            System.out.println("status === " + status + "request_id === " + request_id);
            OAtuthUtil.parseJWT(token);

            Request request = new Request().where("id", request_id).find();

            if (request == null) {
                return RestResponse.fail(400, "request_id不存在");
            }

            if (status == 1) {
                Friends friends = new Friends();
                friends.setFrom_username(request.getFrom_username());
                friends.setTo_username(request.getTo_username());
                friends.setTime(System.currentTimeMillis());
                friends.save();
            }

            request.setStatus(status);
            request.setIs_confirmed(1);
            request.where("id", request_id).update();

            return RestResponse.ok(null, 200);

        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }


    /**
     * 获取所有给我的请求
     *
     * @param username
     * @param token
     * @return
     */
    @GetRoute("get_all_to_requests")
    @JSON
    public RestResponse getAllToRequests(@Param String username, @Param String token) {
        try {
            OAtuthUtil.parseJWT(token);

            List<Request> requests = new Request().where("to_username", username).findAll();

            return RestResponse.ok(requests, 200);


        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 获取所有我发出的请求
     *
     * @param username
     * @param token
     * @return
     */
    @GetRoute("get_all_from_requests")
    @JSON
    public RestResponse getAllFromRequests(@Param String username, @Param String token) {
        try {
            OAtuthUtil.parseJWT(token);

            List<Request> requests = new Request().where("from_username", username).findAll();

            return RestResponse.ok(requests, 200);


        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 获取所有朋友
     *
     * @param username
     * @param token
     * @return
     */
    @GetRoute("get_all_friends")
    @JSON
    public RestResponse getAllFriends(@Param String username, @Param String token) {
        try {
            OAtuthUtil.parseJWT(token);

            List<Friends> friends = new Friends().where("from_username", username).or("to_username", username).findAll();

            return RestResponse.ok(friends, 200);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }


}
