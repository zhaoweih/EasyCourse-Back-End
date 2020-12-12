package com.blade.demo.route.model.notebook;


import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

/**
 *  笔记点赞
 */
@Table(value = "t_notebook_like")
public class NotebookLike extends ActiveRecord {

    private Integer id;
    private String user_name;
    private Integer user_id;
    private Long time;
    private Integer notebook_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
    }
}
