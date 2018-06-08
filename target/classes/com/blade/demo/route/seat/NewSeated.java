package com.blade.demo.route.seat;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.google.gson.annotations.SerializedName;

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
