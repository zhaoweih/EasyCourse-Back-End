package com.blade.demo.route.controller;


import com.blade.demo.route.model.Unit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

/**
 * 单元管理
 *
 * @author zhaoweihao
 * @date 2019/1/11
 */
@Path("api/unit")
public class UnitController {

    /**
     * 添加单元
     * @param unit
     * @return
     */
    @PostRoute("add_unit")
    @JSON
    public RestResponse addUnit(@BodyParam Unit unit) {
        try {
            unit.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 查询单元
     * @param course_id
     * @return
     */
    @GetRoute("get_unit")
    @JSON
    public RestResponse getUnit(@Param Integer course_id) {
        try {
            List<Unit> units = new Unit().where("course_id", course_id).findAll();
            return RestResponse.ok(units, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 删除单元
     * @param unit_id
     * @return
     */
    @GetRoute("delete_unit/:unit_id")
    @JSON
    public RestResponse deleteUnit(@PathParam Integer unit_id) {
        try {
            new Unit().where("id", unit_id).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 更新单元
     * @param unit
     * @return
     */
    @PostRoute("update_unit/:unit_id")
    @JSON
    public RestResponse updateUnit(@PathParam Integer unit_id, @BodyParam Unit unit) {
        try {
            System.out.println("unit_id === " + unit_id);
            unit.where("id", unit_id).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }
}
