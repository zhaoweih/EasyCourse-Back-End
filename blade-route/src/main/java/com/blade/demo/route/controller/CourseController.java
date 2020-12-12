package com.blade.demo.route.controller;

import com.blade.demo.route.model.course.CourseSelect;
import com.blade.demo.route.model.Course;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.util.oauth.ExpiredException;
import com.blade.demo.route.util.oauth.OAtuthUtil;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.Collections;
import java.util.List;

@Path("api/course")
public class CourseController {

    /**
     * 老师提交课程
     *
     * @param course
     * @return
     */
    @PostRoute("submit")
    @JSON
    public RestResponse submitCourse(@BodyParam Course course) {
        try {
            course.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(500, e.getMessage());
        }

    }

    /**
     * 删除课程
     *
     * @param course_id
     * @return
     */
    @GetRoute("delete")
    @JSON
    public RestResponse deleteCourse(@Param Integer course_id) {
        try {
            Course course = new Course();
            course.where("id", course_id).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(500, e.getMessage());
        }
    }

    /**
     * 更新课程信息
     *
     * @param course
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateCourse(@BodyParam Course course) {
        try {
            Course updateCourse = new Course();
            updateCourse.setCourseNum(course.getCourseNum());
            updateCourse.setCourseName(course.getCourseName());
            updateCourse.setDescription(course.getDescription());
            updateCourse.setPassword(course.getPassword());
            updateCourse.setTeacherId(course.getTeacherId());
            updateCourse.setTeacherName(course.getTeacherName());
            updateCourse.where("id", course.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(500, e.getMessage());
        }
    }


    /**
     * 根据教师编号和课程名称查找课程
     *
     * @param teacherId
     * @param courseName
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse getCourse(@Param String teacherId, @Param String courseName) {

        if (teacherId == null && courseName == null) {
            return RestResponse.fail(500, "请至少提供教师编号或者课程名称");
        }

        if (teacherId != null) {
            Course course = new Course();
            List<Course> courseList = course.where("teacher_id", teacherId).findAll();
            if (courseList.isEmpty()) {
                return RestResponse.fail(500, "没有找到该教师编号的课程");
            }

            return RestResponse.ok(courseList, 200);

        }

        if (courseName != null) {
            Course course = new Course();
            List<Course> courseList = course.where("course_name", courseName).findAll();
            if (courseList.isEmpty()) {
                return RestResponse.fail(500, "没有找到该课程名字的课程");
            }

            return RestResponse.ok(courseList, 200);
        }

        return RestResponse.fail(500);

    }

    /**
     * 学生选课
     *
     * @param courseSelect
     * @return
     */
    @PostRoute("select")
    @JSON
    public RestResponse selectCourse(@BodyParam CourseSelect courseSelect) {

        Course course = new Course();
        Course course1 = course.where("id", courseSelect.getCourseId()).find();
        if (course1 == null) {
            return RestResponse.fail(500, "没有找到该课程id");
        }
        if (!(course1.getPassword().equals(courseSelect.getPassword()))) {
            return RestResponse.fail(500, "选课密码不正确");
        }
        User user = new User();
        if (user.where("id", courseSelect.getStuId()).find() == null) {
            return RestResponse.fail(500, "没有找到该学生id");
        }

        try {
            Course course2 = new Course();
            int courseNum = course1.getCourseNum();
            course2.setCourseNum(courseNum + 1);
            course2.where("id", courseSelect.getCourseId()).update();
            // 清除选课密码
            courseSelect.setPassword(null);
            courseSelect.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    @GetRoute("querySelectByStuId")
    @JSON
    public RestResponse querySelectByStuId(@Param Integer stuId) {
        CourseSelect courseSelect = new CourseSelect();
        List<CourseSelect> courseSelectList = courseSelect.where("stu_id", stuId).findAll();
        Course course = new Course();
        //将课程信息填充进去
        for (CourseSelect select : courseSelectList) {
            Course course1 = course.where("id", select.getCourseId()).find();
            select.setCourse(course1);
        }
        if (courseSelectList.isEmpty()) {
            return RestResponse.fail(500, "没有找到该学生选课纪录");
        }

        return RestResponse.ok(courseSelectList, 200);
    }

    @GetRoute("querySelectByCourseId")
    @JSON
    public RestResponse querySelectByCourseId(@Param Integer courseId) {
        CourseSelect courseSelect = new CourseSelect();
        List<CourseSelect> courseSelectList = courseSelect.where("course_id", courseId).findAll();
        if (courseSelectList.isEmpty()) {
            return RestResponse.fail(500, "没有找到该课程的选课纪录");
        }
        return RestResponse.ok(courseSelectList, 200);
    }

    @GetRoute("get_random_course")
    @JSON
    public RestResponse getRandomCourse(@Param String token) {
        try {
            //验证token正确性
            OAtuthUtil.parseJWT(token);

            List<Course> courses = new Course().findAll();
            Collections.shuffle(courses);

            return RestResponse.ok(courses, 200);

        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return RestResponse.fail(401, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }


}
