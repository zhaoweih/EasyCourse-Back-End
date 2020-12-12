package com.blade.demo.route.controller;

import com.blade.demo.route.model.leave.Confirm;
import com.blade.demo.route.model.leave.Leave;
import com.blade.demo.route.model.Course;
import com.blade.demo.route.model.user.User;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

@Path("api/leave")
public class LeaveController {

    @PostRoute("submit")
    @JSON
    public RestResponse submitLeave(@BodyParam Leave leave) {

        Course course = new Course().where("id", leave.getCourseId()).find();

        if (course == null) {
            return RestResponse.fail(500, "没有找到该课程");
        }

        User tec = new User().where("teacher_id", course.getTeacherId()).find();

        if (tec == null) {
            return RestResponse.fail(500, "没有找到该教师");
        }

        if (new User().where("student_id", leave.getStudentId()).find() == null || tec == null)
            return RestResponse.fail(500,"没有找到学号或老师编号");
        try {
            leave.setTeacherId(tec.getTeacherId());
            leave.setTecId(tec.getId());
            leave.save();
            return RestResponse.ok(null,200);
        } catch (Exception e) {
            System.out.print(e.toString());
            return RestResponse.fail(500);
        }


    }

    @GetRoute("query")
    @JSON
    public RestResponse getLeave(@Param String studentId, @Param String teacherId) {
        if (studentId != null) {
            Leave leave = new Leave();
            List<Leave> leaveList = leave.where("student_id", studentId).findAll();
            return RestResponse.ok(leaveList, 200);
        } else if (teacherId != null) {
            Leave leave = new Leave();
            List<Leave> leaveList = leave.where("teacher_id", teacherId).findAll();
            return RestResponse.ok(leaveList, 200);
        }

        return RestResponse.fail(500);

    }

    @PostRoute("confirm")
    @JSON
    public RestResponse confirmLeave(@BodyParam Confirm confirm) {
        Leave leave = new Leave();
        Leave leave1 = leave.where("id", confirm.getLeaveId()).find();
        User user = new User();
        User user1 = user.where("id", confirm.getTecId()).find();
        if (leave1 == null) {
            return RestResponse.fail(500, "请假条id不存在");
        } else if (user1 == null) {
            return RestResponse.fail(500, "没有这个用户");
        } else if (user1.getTeacherId() == null) {
            return RestResponse.fail(500, "当前用户并非老师");
        }

        try {
            Leave leave2 = new Leave();
            leave2.setTecAdvise(confirm.getTecAdvise());
            leave2.setStatus(confirm.getStatus());
            leave2.where("id", confirm.getLeaveId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }

}
