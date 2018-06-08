package com.blade.demo.route.quiz;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

/**
 * 讨论表
 */
@Table(value = "t_quiz")
public class Quiz extends ActiveRecord{

    /**
     * @id
     * @courseId
     * @teacherId
     * @tecId int
     * @quizNum
     * @studentId
     * @stuId
     * @studentName
     */

    private Integer id;
    @SerializedName("course_id")
    private Integer courseId;
    @SerializedName("teacher_id")
    private String teacherId;
    @SerializedName("tec_id")
    private Integer tecId;
    @SerializedName("quiz_num")
    private Integer quizNum;
    @SerializedName("student_id")
    private String studentId;
    @SerializedName("stu_id")
    private Integer stuId;
    @SerializedName("student_name")
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public Integer getQuizNum() {
        return quizNum;
    }

    public void setQuizNum(Integer quizNum) {
        this.quizNum = quizNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}
