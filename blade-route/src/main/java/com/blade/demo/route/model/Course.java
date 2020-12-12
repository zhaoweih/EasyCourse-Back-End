package com.blade.demo.route.model;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

@Table(value = "t_course")
public class Course extends ActiveRecord{

    private Integer id;
    @SerializedName("tec_id")
    private Integer tecId;
    @SerializedName("teacher_id")
    private String teacherId;
    @SerializedName("course_name")
    private String courseName;
    @SerializedName("course_num")
    private Integer courseNum;
    @SerializedName("teacher_name")
    private String teacherName;
    private String password;
    private String description;

    /**
     * 课程封面
     */
    private String class_image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClass_image() {
        return class_image;
    }

    public void setClass_image(String class_image) {
        this.class_image = class_image;
    }
}
