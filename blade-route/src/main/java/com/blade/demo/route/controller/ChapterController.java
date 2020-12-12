package com.blade.demo.route.controller;


import com.blade.demo.route.model.Chapter;
import com.blade.demo.route.model.Unit;
import com.blade.demo.route.model.chat.Chat;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;

import java.util.List;

/**
 * 章节管理
 *
 * @author zhaoweihao
 * @date 2019/1/11
 */
@Path("api/chapter")
public class ChapterController {

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @PostRoute("add_chapter")
    @JSON
    public RestResponse addChapter(@BodyParam Chapter chapter) {
        try {
            chapter.save();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 查询章节
     * @param unit_id
     * @return
     */
    @GetRoute("get_chapter")
    @JSON
    public RestResponse getChapter(@Param Integer unit_id) {
        try {
            List<Chapter> chapters = new Chapter().where("unit_id", unit_id).findAll();
            return RestResponse.ok(chapters, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 删除章节
     * @param chapter_id
     * @return
     */
    @GetRoute("delete_chapter/:chapter_id")
    @JSON
    public RestResponse deleteChapter(@PathParam Integer chapter_id) {
        try {
            new Chapter().where("id", chapter_id).delete();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }

    /**
     * 更新章节
     * @param chapter_id
     * @return
     */
    @PostRoute("update_chapter/:chapter_id")
    @JSON
    public RestResponse updateChapter(@PathParam Integer chapter_id, @BodyParam Chapter chapter) {
        try {
            chapter.where("id", chapter_id).update();
            return RestResponse.ok(null, 200);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(400, e.getMessage());
        }
    }
}
