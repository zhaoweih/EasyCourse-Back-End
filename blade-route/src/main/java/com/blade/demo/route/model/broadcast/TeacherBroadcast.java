package com.blade.demo.route.model.broadcast;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

@Table(value = "t_broadcast_teacher")
public class TeacherBroadcast extends ActiveRecord {

    private Integer id;
    /**
     * 广播内容
     */
    private String content;
    /**
     * 广播发送者名字
     */
    private String sender_name;
    /**
     * 广播接收者名字
     */
    private String receiver_name;
    /**
     * 广播发送者ID
     */
    private String sender_id;
    /**
     * 广播接收者ID
     */
    private String receiver_id;

    /**
     * 广播类型
     * 1.普通广播
     * 2.紧急广播
     */
    private Integer type;

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

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TeacherBroadcast{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender_name='" + sender_name + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", sender_id='" + sender_id + '\'' +
                ", receiver_id='" + receiver_id + '\'' +
                ", type=" + type +
                '}';
    }
}
