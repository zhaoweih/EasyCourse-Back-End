package com.blade.demo.route.model.friends;


import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

@Table(value = "t_request")
public class Request extends ActiveRecord {

    private Integer id;

    private String from_username;

    private String to_username;

    /**
     * 状态
     * 0 已发送
     * 1 接收
     * 2 拒接
     */
    private Integer status;

    private Integer is_confirmed;

    private Long time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom_username() {
        return from_username;
    }

    public void setFrom_username(String from_username) {
        this.from_username = from_username;
    }

    public String getTo_username() {
        return to_username;
    }

    public void setTo_username(String to_username) {
        this.to_username = to_username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(Integer is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
