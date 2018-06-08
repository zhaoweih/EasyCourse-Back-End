package com.blade.demo.route.seat;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

/**
 * @author biezhi
 * @date 2017/9/28
 */
@Table(value = "t_seat_record")
public class SeatedRec extends ActiveRecord {

    private Integer id;
    @SerializedName("student_id")
    private String studentId;
    @SerializedName("stu_id")
    private Integer stuId;
    @SerializedName("class_code")
    private String classCode;
    @SerializedName("class_column")
    private Integer classColumn;
    @SerializedName("class_row")
    private Integer classRow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getClassColumn() {
        return classColumn;
    }

    public void setClassColumn(Integer classColumn) {
        this.classColumn = classColumn;
    }

    public Integer getClassRow() {
        return classRow;
    }

    public void setClassRow(Integer classRow) {
        this.classRow = classRow;
    }
}
