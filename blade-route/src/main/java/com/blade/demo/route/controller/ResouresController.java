package com.blade.demo.route.controller;


import com.blade.demo.route.model.Resoucrs;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

/**
 * 资源类
 */
@Path("api/resources")
public class ResouresController {

    /**
     * 提交资源
     *
     * @param resoucrs
     * @return
     */
    @PostRoute("submit")
    @JSON
    public RestResponse sbResourecs(@BodyParam Resoucrs resoucrs) {

        try {
            resoucrs.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500, "添加资源失败");
        }
    }


    /**
     * 查询资源
     *
     * @param class_id 课程Id
     * @return 资源列表
     */
    @GetRoute("query")
    @JSON
    public RestResponse qRes(@Param Integer class_id) {
        if (class_id == null) {
            return RestResponse.fail(400, "class_id为空");
        }
        try {
            List<Resoucrs> resoucrsList = new Resoucrs().where("class_id", class_id).findAll();
            if (resoucrsList != null && !resoucrsList.isEmpty()) {
                return RestResponse.ok(resoucrsList, 200);
            }
        } catch (Exception e) {
            return RestResponse.fail(400, e.getMessage());
        }
        return RestResponse.fail(400);
    }

    /**
     * 查询单个课程
     * 具体情景：扫描二维码分享资源
     *
     * @param resourcesId 课程id
     * @return 资源实体
     */
    @GetRoute("getOneResources")
    @JSON
    public RestResponse getOneResources(@Param Integer resourcesId) {
        if (resourcesId == null) {
            return RestResponse.fail(400, "id为空");
        }
        try {
            Resoucrs resource = new Resoucrs().where("id", resourcesId).find();
            return RestResponse.ok(resource, 200);
        } catch (Exception e) {
            return RestResponse.fail(400, e.getMessage());
        }
    }


    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @GetRoute("delete")
    @JSON
    public RestResponse dRes(@Param Integer id) {
        if (id == null) {
            return RestResponse.fail(400, "id为空");
        }

        try {
            new Resoucrs().where("id", id).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {

            return RestResponse.fail(400, e.getMessage());
        }
    }


}
