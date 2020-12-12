package com.blade.demo.route.controller;

import com.blade.demo.route.model.broadcast.TeacherBroadcast;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

/**
 * 广播系统管理
 *
 * @author Zhaoweihao
 * 2018-12-20
 */
@Path("api/broadcast/system")
public class BroadcastSysController {

    /**
     * 添加教师广播
     *
     * @param
     * @return
     */
    @PostRoute("add_teacher_broadcast")
    @JSON
    public RestResponse addTeacherBroadcast(@BodyParam TeacherBroadcast teacherBroadcast) {
        if (teacherBroadcast == null) {
            return RestResponse.fail(400);
        }
        String content = teacherBroadcast.getContent();
        String senderName = teacherBroadcast.getSender_name();
        if (content.isEmpty()) {
            return RestResponse.fail(400, "content字段为空");
        }

        if (senderName.isEmpty()) {
            return RestResponse.fail(400, "sendname字段为空");
        }
        try {
            teacherBroadcast.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500, "捕获存入数据库异常");
        }
    }

    /**
     * 查询所有接受广播
     * @param receiver_id
     * @return
     */
    @GetRoute("get_teacher_broadcast")
    @JSON
    public RestResponse getTeacherBroads(@Param String receiver_id) {
        if (receiver_id.isEmpty()) {
            return RestResponse.fail(400);
        }

        List<TeacherBroadcast> teacherBroadcasts = new TeacherBroadcast().where("receiver_id", receiver_id).findAll();

        if (teacherBroadcasts.isEmpty()) {
            return RestResponse.fail(400, "没有找到任何广播");
        }
        return RestResponse.ok(teacherBroadcasts, 200);
    }
}
