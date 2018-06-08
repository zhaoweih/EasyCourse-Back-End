package com.blade.demo.route.vote;

import com.blade.demo.route.user.User;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@Path("api/vote/record")
public class RecordController {

    /**
     * 增加投票纪录
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addRec(@BodyParam Record record) {

        /**
         * @id
         * @stuId
         * @studentId
         * @voteId
         * @date
         *
         */

        if (record == null) {
            return RestResponse.fail(500);
        }

        if(new User().where("student_id", record.getStudentId()).find() == null)
            return RestResponse.fail(500, "找不到该学生编号");

        if (new Vote().where("id", record.getVoteId()).find() == null)
            return RestResponse.fail(500, "找不到该投票id");

        try {
            record.save();
            return RestResponse.ok(null, 200);
        } catch(Exception e) {
            return RestResponse.fail(500);

        }

    }

    /**
     * 增加投票纪录
     * @param
     * @return
     */
    @PostRoute("read")
    @JSON
    public RestResponse readRec(@BodyParam String recJson) {

        try {
            NewRec newRec =  new Gson().fromJson(recJson, NewRec.class);

            List<AddRec> addRecs = newRec.getRecJson();

            int voteId = newRec.getVoteId();

            Vote vote = new Vote().where("id", voteId).find();

            List<Select> selects = new Gson().fromJson(vote.getChoiceJson(), new TypeToken<List<Select>>() {
            }.getType());

            for (int i = 0; i < addRecs.size(); i ++) {
                switch (addRecs.get(i).getChoice()) {
                    case 1:
                        int numA = selects.get(i).getNumA();
                        selects.get(i).setNumA(numA + 1);
                        break;
                    case 2:
                        int numB = selects.get(i).getNumB();
                        selects.get(i).setNumB(numB + 1);
                        break;
                    case 3:
                        int numC = selects.get(i).getNumC();
                        selects.get(i).setNumC(numC + 1);
                        break;
                    case 4:
                        int numD = selects.get(i).getNumD();
                        selects.get(i).setNumD((numD + 1));
                        break;
                    default:
                        break;
                }
            }

            vote.setChoiceJson(new Gson().toJson(selects));

            vote.where("id", voteId).update();

            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }



    }

    /**
     * 查询投票纪录
     *
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryRec(@Param Integer voteId) {

        if (voteId == null) {
            return RestResponse.fail(500);
        }

        Vote vote = new Vote().where("id", voteId).find();

        if (vote == null) {
            return RestResponse.fail(500, "没有查询到该投票id");
        }

        List<Record> recordList = new Record().where("void_id", voteId).findAll();

        return RestResponse.ok(recordList, 200);

    }

    /**
     * 更新投票纪录
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateRec(@BodyParam Record record) {
        /**
         * @id
         * @stuId
         * @studentId
         * @voteId
         * @date
         *
         */

        if (record == null) {
            return RestResponse.fail(500);
        }

        if(new User().where("student_id", record.getStudentId()).find() == null)
            return RestResponse.fail(500, "找不到该学生编号");

        if (new Vote().where("id", record.getVoteId()).find() == null)
            return RestResponse.fail(500, "找不到该投票id");

        try {
            record.where("id", record.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    /**
     * 删除投票纪录
     * @param record
     * @return
     */
    @PostRoute("delete")
    @JSON
    public RestResponse deleteRec(@BodyParam Record record) {
        Integer id = record.getId();
        if (id == null) {
            return RestResponse.fail(500, "必须提供投票的id");
        }

        try {
            new Record().where("id", record.getId()).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }



}
