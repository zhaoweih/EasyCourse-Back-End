package com.blade.demo.route.model.seat;

/**
 * @author biezhi
 * @date 2017/9/28
 */

public class NewSeated {

    private Integer id;
    private String classCode;
    private SeatSel seatSel;

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

    public SeatSel getSeatSel() {
        return seatSel;
    }

    public void setSeatSel(SeatSel seatSel) {
        this.seatSel = seatSel;
    }

}
