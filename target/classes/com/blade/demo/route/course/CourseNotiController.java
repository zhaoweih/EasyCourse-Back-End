package com.blade.demo.route.course;

import com.blade.demo.route.user.User;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

@Path("api/noti")
public class CourseNotiController {

    /**
     * 老师发送通知
     * @param courseNoti
     * @return
     */
    @PostRoute("sendCourseNoti")
    @JSON
    public RestResponse sendCourseNoti(@BodyParam CourseNoti courseNoti) {

        /**
         * @id
         * @courseId
         * @content
         * @date
         * @tecId
         */

        Course course = new Course();
        if(course.where("id", courseNoti.getCourseId()).find() == null) {
            return RestResponse.fail(500, "没有找到该课程id");
        }
        User user = new User();
        if (user.where("id", courseNoti.getTecId()).find() == null) {
            return RestResponse.fail(500, "没有找到该用户id");
        }

        try {
            courseNoti.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    @GetRoute("queryCourseNotiByCourseId")
    @JSON
    public RestResponse queryCourseNotiByCourseId(@Param Integer courseId) {


            CourseNoti courseNoti = new CourseNoti();
            List<CourseNoti> courseNotiList = courseNoti.where("course_id", courseId).findAll();
            if (courseNotiList.isEmpty()){
                return RestResponse.fail(500, "该课程的通知为空");
            }

            return RestResponse.ok(courseNotiList, 200);

    }



}
