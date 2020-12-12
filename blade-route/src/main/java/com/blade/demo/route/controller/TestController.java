package com.blade.demo.route.controller;

import com.blade.demo.route.model.test.NewTest;
import com.blade.demo.route.model.test.Select;
import com.blade.demo.route.model.test.Test;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.util.GsonUtils;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.google.gson.reflect.TypeToken;

import java.util.Iterator;
import java.util.List;

@Path("api/test")
public class TestController {

    /**
     * 增加测试
     *
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addTest(@BodyParam String testJson) {
        System.out.println(testJson);
        Test test = GsonUtils.fromJson(testJson, Test.class);
//        boolean hasTecId = new User().where("teacher_id", test.getTeacherId()).find() != null;
//        if (!hasTecId) {
//            return RestResponse.fail(500, "找不到该教师编号");
//        }

        try {
            NewTest newTest = new NewTest();
            newTest.setChoiceNum(test.getChoiceNum());
            newTest.setCourseId(test.getCourseId());
            newTest.setTeacherId(test.getTeacherId());
//            newTest.setChoiceJson(new Gson().toJson(test.getSelectList()));
            newTest.setChoiceJson(GsonUtils.toJson(test.getSelectList()));
            newTest.setChapter_id(test.getChapter_id());
            newTest.setTitle(test.getTitle());
            newTest.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());

        }

    }


    /**
     * 查询测试
     *
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryTest(@Param Integer chapter_id) {

        if (chapter_id == null) {
            return RestResponse.fail(500);
        }

//        Course course = new Course().where("id", courseId).find();
//
//        if (course == null) {
//            return RestResponse.fail(500, "没有查询到该课程id");
//        }

        List<NewTest> newTests = new NewTest().where("chapter_id", chapter_id).findAll();

//        Gson gson = new Gson();

        Iterator<NewTest> newTestIterator = newTests.iterator();
        while (newTestIterator.hasNext()) {
            NewTest newTest = newTestIterator.next();
            String json = newTest.getChoiceJson();
            newTest.setSelectList((List<Select>) GsonUtils.getGson().fromJson(json, new TypeToken<List<Select>>() {
            }.getType()));
            newTest.setChoiceJson(null);
        }

        return RestResponse.ok(newTests, 200);

    }

    /**
     * 更新测试
     *
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateTest(@BodyParam NewTest newTest) {

        if (newTest == null) {
            return RestResponse.fail(500);
        }

//        if (new User().where("teacher_id", vote.getTeacherId()).find() == null)
//            return RestResponse.fail(500, "找不到该教师编号");

        try {
            newTest.where("id", newTest.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    /**
     * 删除测试
     * @param newTest
     * @return
     */
    @PostRoute("delete")
    @JSON
    public RestResponse deleteTest(@BodyParam NewTest newTest) {
        Integer id = newTest.getId();
        if (id == null) {
            return RestResponse.fail(500, "必须提供id");
        }

        try {
            new NewTest().where("id", newTest.getId()).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }


}
