package com.blade.demo.route.controller;


import com.blade.demo.route.model.Feedback;
import com.blade.demo.route.util.oauth.OAtuthUtil;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

/**
 * 反馈
 * @author zhaoweihao
 * @date 2019/2/2
 */
@Path("api/feedback")
public class FeedbackController {

    @PostRoute("post")
    @JSON
    public RestResponse postFeedback(@Param String token,  @BodyParam Feedback feedback) {
        try {
            OAtuthUtil.parseJWT(token);
            feedback.setTime(System.currentTimeMillis());
            feedback.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }
}
