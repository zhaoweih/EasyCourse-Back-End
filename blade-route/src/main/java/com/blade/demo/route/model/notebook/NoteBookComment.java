package com.blade.demo.route.model.notebook;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

/**
 *  笔记评论
 */
@Table(value = "t_notebook_comment")
public class NoteBookComment extends ActiveRecord {

    private Integer id;
    private String content;
    private Integer user_id;
    private Integer owner_id;
    private Long time;
    private String user_avatar;
    private String user_name;

    private Integer notebook_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
    }
}
