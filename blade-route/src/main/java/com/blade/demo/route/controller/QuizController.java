package com.blade.demo.route.controller;

import com.blade.demo.route.model.Course;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.model.quiz.Quiz;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

@Path("api/quiz")
public class QuizController {

    /**
     * 增加提问次数
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addQuiz(@BodyParam Quiz quiz) {

        if (quiz == null) {
            return RestResponse.fail(500);
        }

        if ( new Course().where("id", quiz.getCourseId()).find() == null )
            return RestResponse.fail(500, "没有找到该课程id");

        if ( new User().where("teacher_id", quiz.getTeacherId()).find() == null )
            return RestResponse.fail(500, "没有找到该教师编号");

        if ( new User().where("student_id", quiz.getStudentId()).find() == null )
            return RestResponse.fail(500, "没有找到该学生编号");

        Quiz quiz1 = new Quiz().where("student_id", quiz.getStudentId()).find();

        if ( quiz1 == null ) {
            try {
                User user = new User().where("student_id", quiz.getStudentId()).find();
                quiz.setStudentName(user.getName());
                quiz.save();
                return RestResponse.ok(null, 200);
            } catch (Exception e) {
                return RestResponse.fail(500, "捕获存入数据库异常");
            }
        } else  {
            int quizNum = quiz1.getQuizNum();
            int id = quiz1.getId();
            quizNum = quizNum + 1;
            quiz1.setQuizNum(quizNum);
            try {
                quiz1.where("id", id).update();
                return RestResponse.ok(null, 200);
            } catch (Exception e) {
                return RestResponse.fail(500);
            }
        }

    }

    /**
     * 查询提问次数
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryQuiz(@Param Integer courseId) {

        if (courseId == null) {
            return RestResponse.fail(500);
        }

        Course course = new Course().where("id", courseId).find();

        if (course == null) {
            return RestResponse.fail(500, "没有查询到该课程id");
        }

        List<Quiz> quizList = new Quiz().where("course_id", courseId).findAll();

        return RestResponse.ok(quizList, 200);

    }




}
