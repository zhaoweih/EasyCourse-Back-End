package com.blade.demo.route.model;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

/**
 * @author por
 */
@Table(value = "t_resources")
public class Resoucrs  extends ActiveRecord {

    /**
     * 自动生成的ID，不用管
     */
    private Integer id;

    /**
     * 教师Id
     */
    private String teacher_id;

    /**
     * 上传后的资源地址
     */
    private String res_url;

    /**
     * 资源名字
     */

    private String res_name;

    /**
     * 资源大小
     */
    private Integer res_size;

    /**
     * 归属课程Id
     */
    private Integer class_id;

    /**
     * 上传日期
     */
    private String res_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getRes_url() {
        return res_url;
    }

    public void setRes_url(String res_url) {
        this.res_url = res_url;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public Integer getRes_size() {
        return res_size;
    }

    public void setRes_size(Integer res_size) {
        this.res_size = res_size;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public String getRes_time() {
        return res_time;
    }

    public void setRes_time(String res_time) {
        this.res_time = res_time;
    }
}
