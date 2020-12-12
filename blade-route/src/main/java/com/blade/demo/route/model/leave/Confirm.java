package com.blade.demo.route.model.leave;

public class Confirm {
    private Integer leaveId;
    private String tecAdvise;
    private Integer tecId;
    private Integer status;

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getTecAdvise() {
        return tecAdvise;
    }

    public void setTecAdvise(String tecAdvise) {
        this.tecAdvise = tecAdvise;
    }

    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
