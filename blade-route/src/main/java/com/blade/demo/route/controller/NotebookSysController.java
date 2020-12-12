package com.blade.demo.route.controller;

import com.blade.demo.route.model.notebook.NoteBookComment;
import com.blade.demo.route.model.notebook.Notebook;
import com.blade.demo.route.model.notebook.NotebookLike;
import com.blade.demo.route.util.StringUtils;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

/**
 * 笔记系统管理
 * @author Zhaoweihao
 */
@Path("api/homework/system")
public class NotebookSysController {

    /**
     * 添加笔记
     *
     * @param
     * @return
     */
    @PostRoute("add_notebook_item")
    @JSON
    public RestResponse addNotebookItem(@BodyParam Notebook notebook) {
        if (notebook == null) {
            return RestResponse.fail(400);
        }
        String content = notebook.getContent();
        String title = notebook.getTitle();
        //添加当前时间
        notebook.setTime(System.currentTimeMillis());
        if (content.isEmpty()) {
            return RestResponse.fail(400, "content字段为空");
        }

        if (title.isEmpty()) {
            return RestResponse.fail(400, "title字段为空");
        }
        try {
            notebook.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(500, "服务器错误");
        }
    }


    /**
     * 查询所有笔记
     * @param user_id
     * @return
     */
    @GetRoute("get_notebook_items")
    @JSON
    public RestResponse getNotebookItems(@Param String user_id) {
        if (user_id.isEmpty()) {
            return RestResponse.fail(400);
        }

        List<Notebook> notebooks = new Notebook().where("user_id", user_id).findAll();

        //查询like数
        NotebookLike notebookLike = new NotebookLike();
        for (Notebook notebook : notebooks) {
            Long num = notebookLike.where("notebook_id", notebook.getId()).count();
            notebook.setLike_num(num);
        }

        if (notebooks.isEmpty()) {
            return RestResponse.fail(400, "没有找到任何笔记");
        }
        return RestResponse.ok(notebooks, 200);
    }

    /**
     * 删除单条笔记
     * @param id
     * @return
     */
    @GetRoute("delete_notobook_item")
    @JSON
    public RestResponse deleteNotebookItem(@Param int id) {
        try {
            new Notebook().where("id", id).delete();

            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(400,e.getMessage());
        }
    }


    /**
     * 更新笔记
     * @param notebook
     * @return
     */
    @PostRoute("update_notebook_item")
    @JSON
    public RestResponse updateNotebookItem(@BodyParam Notebook notebook) {
        try {
//            Notebook notebook1 = new Notebook();
//            notebook1.setContent(notebook.getContent());
//            notebook1.setUser_id(notebook.getUser_id());
//            notebook1.setTitle(notebook.getTitle());
//            notebook1.setResoucrs(notebook.getResoucrs());
//            notebook1.where("id", notebook.getId()).update();
//            notebook.update();
            notebook.where("id", notebook.getId()).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 添加笔记评论
     * @param bookComment
     * @return
     */
    @PostRoute("add_notebook_comment")
    @JSON
    public RestResponse addNotebookComment(@BodyParam NoteBookComment bookComment) {
        String errorMsg = null;
        if (StringUtils.isEmpty(bookComment.getContent())) {
            errorMsg = "内容为空";
        }
        else if (bookComment.getOwner_id() == 0 || bookComment.getOwner_id() == null) {
            errorMsg = "owner_id为空";
        }
        else if (bookComment.getUser_id() == 0 || bookComment.getUser_id() == null) {
            errorMsg = "user_id为空";
        }

        if (errorMsg != null) {
            return RestResponse.fail(errorMsg);
        }
        try {
            bookComment.setTime(System.currentTimeMillis());
            bookComment.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 获取笔记评论
     * @param notebook_id
     * @return
     */
    @GetRoute("get_notebook_comments")
    @JSON
    public RestResponse getNotebookComments(@Param Integer notebook_id) {

        try {
            List<NoteBookComment> noteBookComments = new NoteBookComment().where("notebook_id", notebook_id).findAll();
            return RestResponse.ok(noteBookComments, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }


    /**
     * 添加笔记点赞
     * @param notebookLike
     * @return
     */
    @PostRoute("add_notebook_like")
    @JSON
    public RestResponse addNotebookLike(@BodyParam NotebookLike notebookLike) {
        try {
            notebookLike.setTime(System.currentTimeMillis());
            notebookLike.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 获取共享笔记
     * @param is_shared
     * @return
     */
    @GetRoute("get_share_notebook")
    @JSON
    public RestResponse getShareNotebook(@Param Boolean is_shared) {
        try {
            List<Notebook> notebooks = new Notebook().where("is_shared", is_shared).findAll();

            //查询like数
            NotebookLike notebookLike = new NotebookLike();
            for (Notebook notebook : notebooks) {
                Long num = notebookLike.where("notebook_id", notebook.getId()).count();
                notebook.setLike_num(num);
            }

            if (notebooks.isEmpty()) {
                return RestResponse.fail(400, "没有找到任何笔记");
            }
            return RestResponse.ok(notebooks, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 用名字搜索共享的笔记
     * @param name
     * @return
     */
    @GetRoute("get_share_notebook_by_name")
    @JSON
    public RestResponse getShareNotebookByName(@Param String name) {
        try {
            List<Notebook> notebooks = new Notebook().like("title", "%" + name + "%").and("is_shared", true).findAll();

            //查询like数
            NotebookLike notebookLike = new NotebookLike();
            for (Notebook notebook : notebooks) {
                Long num = notebookLike.where("notebook_id", notebook.getId()).count();
                notebook.setLike_num(num);
            }

            if (notebooks.isEmpty()) {
                return RestResponse.fail(400, "没有找到任何笔记");
            }
            return RestResponse.ok(notebooks, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 用标签搜索共享的笔记
     * @param tag
     * @return
     */
    @GetRoute("get_share_notebook_by_tag")
    @JSON
    public RestResponse getShareNotebookByTag(@Param String tag) {
        try {
            List<Notebook> notebooks = new Notebook().like("tag", "%" + tag + "%").and("is_shared", true).findAll();

            //查询like数
            NotebookLike notebookLike = new NotebookLike();
            for (Notebook notebook : notebooks) {
                Long num = notebookLike.where("notebook_id", notebook.getId()).count();
                notebook.setLike_num(num);
            }

            if (notebooks.isEmpty()) {
                return RestResponse.fail(400, "没有找到任何笔记");
            }
            return RestResponse.ok(notebooks, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }





}
