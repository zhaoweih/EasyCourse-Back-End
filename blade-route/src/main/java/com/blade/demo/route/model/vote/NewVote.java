package com.blade.demo.route.model.vote;

import java.util.List;

public class NewVote {
    private Integer id;
    private Integer tecId;
    private String teacherId;
    private String title;
    private Integer choiceNum;
    private String startDate;
    private String endDate;
    private String imgUrl;
    private Integer choiceMode;
    private Integer choiceMax;
    private List<Select> selectList;
    private Integer courseId;


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

    public List<Select> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<Select> selectList) {
        this.selectList = selectList;
    }

    public Integer getCourseId() {

        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
