package com.blade.demo.route.vote;

import com.blade.demo.route.course.Course;
import com.blade.demo.route.user.User;
import com.blade.demo.route.discuss.Discuss;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("api/vote")
public class VoteController {

    /**
     * 增加投票
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addVote(@BodyParam String voteJson) {

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

        NewVote newVote = new Gson().fromJson(voteJson, NewVote.class);

        if(new User().where("teacher_id", newVote.getTeacherId()).find() == null)
            return RestResponse.fail(500, "找不到该教师编号");

        try {
            Vote vote = new Vote();
            vote.setChoiceNum(newVote.getChoiceNum());
            vote.setCourseId(newVote.getCourseId());
            vote.setTeacherId(newVote.getTeacherId());
            vote.setChoiceJson(new Gson().toJson(newVote.getSelectList()));
            vote.setTitle(newVote.getTitle());
            vote.save();
            return RestResponse.ok(null, 200);
        } catch(Exception e) {
            return RestResponse.fail(500);

        }

    }


    /**
     * 查询投票
     *
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryVote(@Param Integer courseId) {

        if (courseId == null) {
            return RestResponse.fail(500);
        }

        Course course = new Course().where("id", courseId).find();

        if (course == null) {
            return RestResponse.fail(500, "没有查询到该课程id");
        }

        List<Vote> voteList = new Vote().where("course_id", courseId).findAll();

        Gson gson = new Gson();

        Iterator<Vote> voteIterator = voteList.iterator();
        while(voteIterator.hasNext()) {
            Vote vote = voteIterator.next();
            String json = vote.getChoiceJson();
            vote.setSelectList(gson.fromJson(json, new TypeToken<List<Select>>() {
            }.getType()));
            vote.setChoiceJson(null);
        }

        return RestResponse.ok(voteList, 200);

    }

    /**
     * 更新投票
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateVote(@BodyParam Vote vote) {
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
         *
         */

        if (vote == null) {
            return RestResponse.fail(500);
        }

        if(new User().where("teacher_id", vote.getTeacherId()).find() == null)
            return RestResponse.fail(500, "找不到该教师编号");

        try {
            vote.where("id", vote.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    @PostRoute("delete")
    @JSON
    public RestResponse deleteVote(@BodyParam Vote vote) {
        Integer id = vote.getId();
        if (id == null) {
            return RestResponse.fail(500, "必须提供投票的id");
        }

        try {
            new Discuss().where("id", vote.getId()).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }



}
