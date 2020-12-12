package com.blade.demo.route.controller;

import com.blade.demo.route.model.activity.Activity;
import com.blade.demo.route.model.chat.Chat;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 活动
 *
 * @author zhaowiehao
 * @date 2019/2/2
 */

@Path("api/activity")
public class AcitivtyController {


    @PostRoute("post_activity")
    @JSON
    public RestResponse postActivity (@BodyParam Activity activity) {
        try {
            activity.setTime(System.currentTimeMillis());
            activity.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(400, e.getMessage());
        }
    }


    @GetRoute("get_acitivties")
    @JSON
    public RestResponse getActivities() {
        try {
            List<Activity> activities = new Activity().findAll();
            return RestResponse.ok(activities, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    @GetRoute("delete_activity")
    @JSON
    public RestResponse deleteActivity(@Param Integer user_id, @Param Integer activity_id) {
        try {
            new Activity().where("id", activity_id).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }
}
