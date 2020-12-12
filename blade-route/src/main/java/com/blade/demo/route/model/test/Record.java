package com.blade.demo.route.model.test;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

/**
 * 讨论表
 */
@Table(value = "t_test_record")
public class Record extends ActiveRecord{

    private Integer id;
    @SerializedName("stu_id")
    private Integer stuId;
    @SerializedName("student_id")
    private String studentId;
    @SerializedName("vote_id")
    private Integer voteId;
    private String date;
    private String rec_json;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getRec_json() {
        return rec_json;
    }

    public void setRec_json(String rec_json) {
        this.rec_json = rec_json;
    }
}
