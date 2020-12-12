package com.blade.demo.route.controller;

import com.blade.demo.route.model.discuss.Discuss;
import com.blade.demo.route.model.Course;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

@Path("api/discuss")
public class DiscussController {

    /**
     * 增加讨论
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addDiscuss(@BodyParam Discuss discuss) {

        if (discuss == null) {
            return RestResponse.fail(500);
        }

        String teacherId = discuss.getTeacherId();
        Integer courseId = discuss.getCourseId();
        String startDate = discuss.getStartDate();
        String content = discuss.getContent();

        if (teacherId.isEmpty() || courseId == null || startDate.isEmpty()) {
            return RestResponse.fail(500, "教师编号、课程编号和开始日期不能为空");
        }

        if (content.isEmpty())
            return RestResponse.fail(500, "内容不能为空");

        try {
            discuss.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500, "捕获存入数据库异常");
        }


    }

    /**
     * 查询讨论
     *
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryDiscuss(@Param Integer courseId) {

        if (courseId == null) {
            return RestResponse.fail(500);
        }

        Course course = new Course().where("id", courseId).find();

        if (course == null) {
            return RestResponse.fail(500, "没有查询到该课程id");
        }

        List<Discuss> discussList = new Discuss().where("course_id", courseId).findAll();

        return RestResponse.ok(discussList, 200);

    }

    /**
     * 更新讨论内容
     * @param discuss
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateDiscuss(@BodyParam Discuss discuss) {

        if (discuss == null) {
            return RestResponse.fail(500);
        }

        String teacherId = discuss.getTeacherId();
        Integer courseId = discuss.getCourseId();
        String startDate = discuss.getStartDate();
        String content = discuss.getContent();

        if (teacherId.isEmpty() || courseId == null || startDate.isEmpty()) {
            return RestResponse.fail(500, "教师编号、课程编号和开始日期不能为空");
        }

        if (content.isEmpty())
            return RestResponse.fail(500, "内容不能为空");

        try {
            discuss.where("id", discuss.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    @PostRoute("delete")
    @JSON
    public RestResponse deleteDiscuss(@BodyParam Discuss discuss) {
        Integer id = discuss.getId();
        if (id == null) {
            return RestResponse.fail(500, "必须提供讨论的id");
        }

        try {
            new Discuss().where("id", discuss.getId()).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }



}
