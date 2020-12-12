package com.blade.demo.route.model.activity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

import java.net.InetSocketAddress;

/**
 * 活动
 * @author zhaoweihao
 * @date 2019/2/2
 */

@Table(value = "t_activity")
public class Activity extends ActiveRecord {

    private Integer id;

    private Long start_time;

    private Long end_time;

    private String title;

    private String img_url;

    /**
     * 类型
     * 1 学校发出的 2老师发出的
     */
    private Integer type;

    /**
     * 标签
     */
    private String tags;

    /**
     * 生成的时间
     */
    private Long time;

    /**
     * 假如是老师发送的需要添加
     */
    private Integer sender_id;

    /**
     * 如果有内容页是Url
     */
    private String content_url;

    /**
     * 内容
     */
    private String content;

    /**
     * 浏览数
     */
    private Integer views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
