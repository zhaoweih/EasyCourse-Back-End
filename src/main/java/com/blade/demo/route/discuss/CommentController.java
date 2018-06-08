package com.blade.demo.route.discuss;

import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

@Path("api/discuss/comment")
public class CommentController {

    /**
     * 增加评论
     * @param
     * @return
     */
    @PostRoute("add")
    @JSON
    public RestResponse addComment(@BodyParam Comment comment) {

        /**
         * @discussId
         * @content
         * @studentId
         * @stuId
         * @date
         */

        if (comment == null) {
            return RestResponse.fail(500);
        }

        Integer discussId = comment.getDiscussId();
        String content = comment.getContent();
        String studentId = comment.getStudentId();
        String date = comment.getDate();

        if (discussId == null || studentId.isEmpty() || date.isEmpty()) {
            return RestResponse.fail(500, "讨论id、学号和日期不能为空");
        }

        if (content.isEmpty())
            return RestResponse.fail(500, "内容不能为空");

        try {
            comment.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500, "捕获存入数据库异常");
        }


    }

    /**
     * 查询讨论
     *
     * @return
     */
    @GetRoute("query")
    @JSON
    public RestResponse queryComment(@Param Integer discussId) {

        if (discussId == null) {
            return RestResponse.fail(500);
        }

        Discuss discuss = new Discuss().where("id", discussId).find();

        if (discuss == null) {
            return RestResponse.fail(500, "没有查询到该讨论id");
        }

        List<Comment> commentList = new Comment().where("discuss_id", discussId).findAll();

        return RestResponse.ok(commentList, 200);

    }

    /**
     * 更新评论
     * @param
     * @return
     */
    @PostRoute("update")
    @JSON
    public RestResponse updateComment(@BodyParam Comment comment) {
        /**
         * @discussId
         * @content
         * @studentId
         * @stuId
         * @date
         */

        if (comment == null) {
            return RestResponse.fail(500);
        }

        Integer discussId = comment.getDiscussId();
        String content = comment.getContent();
        String studentId = comment.getStudentId();
        String date = comment.getDate();

        if (discussId == null || studentId.isEmpty() || date.isEmpty()) {
            return RestResponse.fail(500, "讨论id、学号和日期不能为空");
        }

        if (content.isEmpty())
            return RestResponse.fail(500, "内容不能为空");


        try {
            comment.where("id", comment.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }

    }

    @PostRoute("delete")
    @JSON
    public RestResponse deleteComment(@BodyParam Comment comment) {
        Integer id = comment.getId();
        if (id == null) {
            return RestResponse.fail(500, "必须提供评论的id");
        }

        try {
            new Comment().where("id", comment.getId()).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500);
        }
    }



}
