package com.blade.demo.route.model;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

@Table(value = "t_chapter")
public class Chapter extends ActiveRecord {

    /**
     * id
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer course_id;

    /**
     * 单元id
     */
    private Integer unit_id;


    /**
     * 章节标题
     */
    private String title;


    /**
     * 资源列表
     */
    private String res_list;

    /**
     * 测试id
     */
    private Integer test_id;

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public String getRes_list() {
        return res_list;
    }

    public void setRes_list(String res_list) {
        this.res_list = res_list;
    }

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

    public Integer getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        this.unit_id = unit_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
