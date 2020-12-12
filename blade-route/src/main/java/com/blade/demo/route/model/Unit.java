package com.blade.demo.route.model;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

/**
 * 单元
 */
@Table(value = "t_unit")
public class Unit extends ActiveRecord {

    /**
     * id
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer course_id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 教师编号
     */
    private String teacher_id;

    /**
     * 单元标题
     */
    private String title;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", user_id=" + user_id +
                ", teacher_id='" + teacher_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
