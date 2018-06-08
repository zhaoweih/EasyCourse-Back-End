package com.blade.demo.route.seat;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author biezhi
 * @date 2017/9/28
 */
@Table(value = "t_seat_select")
public class Seated extends ActiveRecord {

    private Integer id;
    @SerializedName("class_code")
    private String classCode;
    @SerializedName("class_json")
    private String classJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassJson() {
        return classJson;
    }

    public void setClassJson(String classJson) {
        this.classJson = classJson;
    }
}
