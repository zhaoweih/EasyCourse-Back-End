package com.blade.demo.route.vote;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 讨论表
 */
@Table(value = "t_vote")
public class Vote extends ActiveRecord{

    /**
     * @id
     * @tecId
     * @teacherId
     * @title
     * @choiceNum
     * @startDate
     * @endDate
     * @imgUrl
     * @choiceMode
     * @choiceMax
     * @choiceJson
     * @courseId
     */

    private Integer id;
    @SerializedName("tec_id")
    private Integer tecId;
    @SerializedName("teacher_id")
    private String teacherId;
    private String title;
    @SerializedName("choice_num")
    private Integer choiceNum;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    private String imgUrl;
    @SerializedName("choice_mode")
    private Integer choiceMode;
    @SerializedName("choice_max")
    private Integer choiceMax;
    @SerializedName("choice_json")
    private String choiceJson;
    @SerializedName("course_id")
    private Integer courseId;

    private List<Select> selectList;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(Integer choiceNum) {
        this.choiceNum = choiceNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getChoiceMode() {
        return choiceMode;
    }

    public void setChoiceMode(Integer choiceMode) {
        this.choiceMode = choiceMode;
    }

    public Integer getChoiceMax() {
        return choiceMax;
    }

    public void setChoiceMax(Integer choiceMax) {
        this.choiceMax = choiceMax;
    }

    public String getChoiceJson() {
        return choiceJson;
    }

    public void setChoiceJson(String choiceJson) {
        this.choiceJson = choiceJson;
    }

    public List<Select> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<Select> selectList) {
        this.selectList = selectList;
    }
}
