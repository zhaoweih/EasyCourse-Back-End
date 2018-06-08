package com.blade.demo.route.course;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;


@Table(value = "t_course_select")
public class CourseSelect extends ActiveRecord {
    /**
     * @id
     * @courseId
     * @stuId
     * @studentId
     * @courseName
     * @teacherName
     */

    private Integer id;
    @SerializedName("course_id")
    private Integer courseId;
    @SerializedName("stu_id")
    private Integer stuId;
    @SerializedName("student_id")
    private String studentId;
    @SerializedName("course_name")
    private String courseName;
    @SerializedName("teacher_name")
    private String teacherName;
    private String password;

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

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
}
