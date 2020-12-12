package com.blade.demo.route.model.notebook;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

import java.util.List;

/**
 * 笔记
 * @author Zhaoweihao
 */
@Table(value = "t_notebook")
public class Notebook extends ActiveRecord {

    private Integer id;

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 笔记文件
     * json保存
     */
    private String resoucrs;

    /**
     * 笔记作者
     */
    private Integer user_id;

    /**
     * 标签
     */
    private String tag;

    /**
     * 时间
     */
    private Long time;

    /**
     * 是否分享
     */
    private Boolean is_shared;

    public Boolean getIs_shared() {
        return is_shared;
    }

    public void setIs_shared(Boolean is_shared) {
        this.is_shared = is_shared;
    }

    private Long like_num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResoucrs() {
        return resoucrs;
    }

    public void setResoucrs(String resoucrs) {
        this.resoucrs = resoucrs;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getLike_num() {
        return like_num;
    }

    public void setLike_num(Long like_num) {
        this.like_num = like_num;
    }
}
