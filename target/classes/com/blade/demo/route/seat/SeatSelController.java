package com.blade.demo.route.seat;

import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

/**
 * @author biezhi
 * @date 2017/9/28
 */
@Path("api/seat")
public class SeatSelController {

//    @GetRoute("get")
//    @JSON
//    public SeatSel readData(@Param int id){
//        // 读取座位
//            Seated seated = new Seated();
//            Seated seated1 = seated.where("id", id).find();
//            if (seated1 == null) {
//                return null;
//            }
//            SeatSel seatSel= new Gson().fromJson(seated1.getClassJson(), SeatSel.class);
//            return seatSel;
//
//    }

    @PostRoute("create")
    @JSON
    public RestResponse create(@BodyParam String seated) {
        NewSeated seated1 = new Gson().fromJson(seated, NewSeated.class);
        try {
            Seated seated2 = new Seated();
            seated2.setClassCode(seated1.getClassCode());
            seated2.setClassJson(new Gson().toJson(seated1.getSeatSel()));
            seated2.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }

    @GetRoute("enter")
    @JSON
    public RestResponse enter(@Param String classCode) {
        Seated seated = new Seated().where("class_code", classCode).find();

        if ( seated == null ) {
            return RestResponse.fail(500, "密令输入错误");
        }

        return RestResponse.ok(new Gson().fromJson(seated.getClassJson(), SeatSel.class), 200);
    }

    @PostRoute("update")
    @JSON
    public RestResponse update(@BodyParam String seated) {
        NewSeated seated1 = new Gson().fromJson(seated, NewSeated.class);
        Seated seated2 = new Seated().where("class_code", seated1.getClassCode()).find();
        if (seated2 == null) {
            return RestResponse.fail(500);
        }
        try {
            seated2.setClassJson(new Gson().toJson(seated1.getSeatSel()));
            seated2.where("class_code", seated1.getClassCode()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }

    @PostRoute("record/add")
    @JSON
    public RestResponse addRec(@BodyParam SeatedRec seatedRec) {
        try {
            seatedRec.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }

    @GetRoute("record/query")
    @JSON
    public  RestResponse getRec(@Param String classCode) {
        Seated seated = new Seated().where("class_code", classCode).find();

        if (seated == null)
            return RestResponse.fail(500, "classcode不存在");

        try {
            List<SeatedRec> seatedRecs = new SeatedRec().where("class_code", classCode).findAll();
            if (seatedRecs.isEmpty())
                return RestResponse.ok();
            return RestResponse.ok(seatedRecs, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }

//    @PostRoute("post")
//    @JSON
//    public RestResponse writeData(@BodyParam String seatSelJson) {
//
////        try {
////            Seated seated = new Seated();
////            seated.setClassCode("20777");
////            seated.setClassJson(json);
////            seated.save();
////            return RestResponse.ok(null,200);
////        } catch (Exception e) {
////            return RestResponse.fail(500);
////        }
//        Seated seated = new Seated();
//        Seated seated1 = seated.where("class_code", "20777").find();
//        if ( seated1 != null ) {
//            seated1.setClassJson(seatSelJson);
//            seated1.where("class_code", "20777").update();
//            return RestResponse.ok(null, 200);
//        } else {
//            return RestResponse.fail(500);
//        }
//
//    }




}
