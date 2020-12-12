package com.blade.demo.route.test;

import com.blade.demo.route.model.*;
import com.blade.demo.route.model.activity.Activity;
import com.blade.demo.route.model.broadcast.TeacherBroadcast;
import com.blade.demo.route.model.chat.Chat;
import com.blade.demo.route.model.notebook.NoteBookComment;
import com.blade.demo.route.model.notebook.Notebook;
import com.blade.demo.route.model.notebook.NotebookLike;
import com.blade.demo.route.model.user.Password;
import com.blade.demo.route.model.user.User;
import com.blade.demo.route.model.vote.Select;
import com.blade.demo.route.test.model.Login;
import com.blade.demo.route.util.EncryptUtils;
import com.blade.demo.route.util.GsonUtil;
import com.blade.demo.route.util.GsonUtils;
import com.google.gson.Gson;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.util.Arrays;

/**
 * 测试API类
 */
public class TestAPi {
    public static void main(String[] args) {
//        HttpUtil.sendGetRequest("api/course/querySelectByCourseId?courseId=10");
//        HttpUtil.sendGetRequest("api/resources/query?class_id=2015");

//        Notebook notebook = new Notebook();
//        notebook.setTitle("测试笔记");
//        notebook.setContent("测试笔记内容");
//        notebook.setResoucrs("www.google.com");
//        notebook.setTag("草稿");
//        notebook.setUser_id(10);
//        notebook.setIs_shared(true);
//
//        HttpUtil.sendPostRequest("api/homework/system/add_notebook_item", new Gson().toJson(notebook));


//        TeacherBroadcast broadcast = new TeacherBroadcast();
//        broadcast.setContent("测试教师广播内容");
//        broadcast.setSender_name("赵先生");
//        broadcast.setReceiver_name("陈小姐");
//        broadcast.setSender_id("20151910");
//        broadcast.setReceiver_id("20171728");
//        broadcast.setType(1);
//
//        HttpUtil.sendPostRequest("api/broadcast/system/add_teacher_broadcast", new Gson().toJson(broadcast));

//        HttpUtil.sendGetRequest("api/broadcast/system/get_teacher_broadcast?receiver_id=20171728");

//        Resoucrs resoucrs = new Resoucrs();
//        resoucrs.setTeacher_id("20151910");
//        resoucrs.setRes_url("www.google.com");
//        resoucrs.setRes_name("google");
//        resoucrs.setRes_size(14);
//        resoucrs.setClass_id(14);
//        resoucrs.setRes_time("2019-01-07");
//
//        HttpUtil.sendPostRequest("api/resources/submit", new Gson().toJson(resoucrs));


//        HttpUtil.sendGetRequest("api/resources/delete?id=4");

//        HttpUtil.sendGetRequest("api/course/delete?course_id=7");

//        Submit course = new Submit();
////        course.setId(8);
//        course.setTecId(26);
//        course.setTeacherId("20151120");
////        course.setCourseNum(20);
//        course.setCourseName("大学语文加数学");
//        course.setTeacherName("赵超人");
//        course.setDescription("数学语文混合学科");
//        course.setClass_image("https://google.com");
//        course.setPassword("abc123456");
////
//        HttpUtil.sendPostRequest("api/course/submit", GsonUtils.toJson(course));

//        Notebook notebook = new Notebook();
//        notebook.setId(2);
//        notebook.setContent("修改后的内容_内容");
//
//        HttpUtil.sendPostRequest("api/homework/system/update_notebook_item", new Gson().toJson(notebook));


//        Login login = new Login();
//        login.setUsername("tea");
//        login.setPassword("tea");
//        HttpUtil.sendPostRequest("api/user/login", new Gson().toJson(login));
//
//        Chat chat = new Chat();
//        chat.setSender_id(16);
//        chat.setClass_id(13);
//        chat.setCourse_id(15);
//        chat.setReceiver_id(10);
//        chat.setMsg_content("测试消息" + System.currentTimeMillis());
//
//        HttpUtil.sendPostRequest("api/chat/send", new Gson().toJson(chat));

//        HttpUtil.sendGetRequest("api/chat/get_personal_msgs?sender_id=10&receiver_id=13");

//        HttpUtil.sendGetRequest("api/chat/get_group_msgs?course_id=14");

//        Unit unit = new Unit();
//        unit.setCourse_id(13);
//        unit.setTeacher_id("20151910");
//        unit.setUser_id(13);
//        unit.setTitle("孔夫子理论测试");
//
//
//        HttpUtil.sendPostRequest("api/unit/add_unit", new Gson().toJson(unit));

//        HttpUtil.sendGetRequest("api/unit/get_unit?course_id=13");

//          HttpUtil.sendPostRequest("api/unit/update_unit/1", new Gson().toJson(unit));

//        HttpUtil.sendGetRequest("api/unit/delete_unit/1");

//        Password password = new Password();
//
//        password.setId(37);
//        password.setOldPassword("abbccc");
//        password.setNewPassword("abc123456");
//
//        HttpUtil.sendPostRequest("api/user/modifyUserPwd", new Gson().toJson(password));


//        HttpUtil.sendGetRequest("api/user/modifyAvatar?id=88&avatarUrl=https://google.com");

//        HttpUtil.sendGetRequest("api/user/getAvatar?id=37");


//        NoteBookComment noteBookComment = new NoteBookComment();
//        noteBookComment.setContent("这个笔记不错");
//        noteBookComment.setOwner_id(14);
//        noteBookComment.setUser_id(13);
//        noteBookComment.setNotebook_id(5);
//        noteBookComment.setUser_name("小镇");
//        noteBookComment.setUser_avatar("https://google.com");
//
//        HttpUtil.sendPostRequest("api/homework/system/add_notebook_comment", GsonUtils.toJson(noteBookComment));

//        HttpUtil.sendGetRequest("api/homework/system/get_notebook_comments?notebook_id=5");

//        NotebookLike notebookLike = new NotebookLike();
//        notebookLike.setNotebook_id(5);
//        notebookLike.setUser_id(13);
//        notebookLike.setUser_name("小新");
//
//        HttpUtil.sendPostRequest("api/homework/system/add_notebook_like", GsonUtils.toJson(notebookLike));

//        HttpUtil.sendGetRequest("api/course/querySelectByStuId?stuId=27");

//        Activity activity = new Activity();
//        activity.setStart_time(System.currentTimeMillis());
//        activity.setEnd_time(System.currentTimeMillis() + 24*60*60*1000L);
//        activity.setContent("测试活动");
//        activity.setTitle("测试活动标题");
//        activity.setImg_url("http://upload-images.jianshu.io/upload_images/5796527-2829fb39b6b86721.png");
//
//        activity.setType(1);
//        activity.setTags("课余活动");
//
//        HttpUtil.sendPostRequest("api/activity/post_activity", GsonUtils.toJson(activity));

//        HttpUtil.sendGetRequest("api/activity/get_acitivties");

//        HttpUtil.sendGetRequest("api/activity/delete_activity?activity_id=2");

//        User user = new User();
//        user.setUsername("zhaoweiha8");
//        user.setPassword("abc123");
//
//
//        HttpUtil.sendPostRequest("api/user/login", GsonUtil.GsonString(user));

//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NDkwOTM3ODEsInN1YiI6IntcImlkXCI6MzEsXCJ1c2VybmFtZVwiOlwiemhhb3dlaWhhOFwiLFwicGFzc3dvcmRcIjpcIioqKioqKlwiLFwic3R1ZGVudF9pZFwiOlwiMjAxNTE5MTA1MlwiLFwiY2xhc3NfaWRcIjpcIjIwMTUxOTEzXCIsXCJkZXBhcnRtZW50XCI6XCLlpJbor63ns7tcIixcImVkdWNhdGlvblwiOjEsXCJkYXRlXCI6XCIyMDE1XCIsXCJzY2hvb2xcIjpcIuaxleWktOWkp-WtplwiLFwic2V4XCI6MCxcIm5hbWVcIjpcIuavm-eBv-WNjlwiLFwid2hlcmVWYWx1ZXNcIjpbXSxcInNhdmVPclVwZGF0ZVByb3BlcnRpZXNcIjpbXX0iLCJpc3MiOiJaV0giLCJleHAiOjE1NTAzODk3ODF9.7-dvpwWqAXLhhVp0PXMJZOdLRaV1SyzyFwE6MK_AiMo";
//        Feedback feedback = new Feedback();
//
//        feedback.setContent("这个app有点小问题");
//
//        feedback.setUser_id(13);
//
//        HttpUtil.sendPostRequest("api/feedback/post?token=" + token, GsonUtils.toJson(feedback));


//        Chapter chapter = new Chapter();
//        chapter.setCourse_id(12);
//        chapter.setTitle("川普麦当劳理论");
//        chapter.setUnit_id(12);
//        chapter.setRes_list(GsonUtils.toJson(new int[]{1, 2, 3, 4,5}));
//        chapter.setTest_id(26);
//
//        HttpUtil.sendPostRequest("api/chapter/add_chapter", GsonUtils.toJson(chapter));

//        HttpUtil.sendGetRequest("api/chapter/get_chapter?unit_id=12");
//                HttpUtil.sendGetRequest("api/chapter/delete_chapter/3");

//                Chapter chapter = new Chapter();
//        chapter.setRes_list(GsonUtils.toJson(new int[]{1, 2, 3, 4}));
//
//                HttpUtil.sendPostRequest("api/chapter/update_chapter/2", GsonUtils.toJson(chapter));

//        HttpUtil.sendGetRequest("api/test/query?chapter_id=4");

//        HttpUtil.sendGetRequest("api/homework/system/get_share_notebook?is_shared=true");

//        HttpUtil.sendGetRequest("api/homework/system/get_share_notebook_by_name?name=测");

//        HttpUtil.sendGetRequest("api/homework/system/get_share_notebook_by_tag?tag=草稿");

//        Reset reset = new Reset();
//        reset.setUsername("zhaowei0");
//        reset.setAnswer("黑猫");
//        reset.setNew_password("abc12300");
//
//        HttpUtil.sendPostRequest("api/user/reset_password", GsonUtils.toJson(reset));

//        HttpUtil.sendGetRequest("api/user/get_user_question?username=zhaowei0");
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NTE4MDEyNjIsInN1YiI6IntcImlkXCI6MjgsXCJ1c2VybmFtZVwiOlwidGVhXCIsXCJwYXNzd29yZFwiOlwiKioqKioqXCIsXCJ0ZWFjaGVyX2lkXCI6XCIyMDE1MTkxMDAwXCIsXCJjbGFzc19pZFwiOlwiMDAwMDAwXCIsXCJkZXBhcnRtZW50XCI6XCLmlZnogrLlrabns7tcIixcImVkdWNhdGlvblwiOjEsXCJkYXRlXCI6XCIwMDAwMDBcIixcInNjaG9vbFwiOlwi6Z-p5bGx5biI6IyD5a2m6ZmiXCIsXCJzZXhcIjowLFwibmFtZVwiOlwiRGF2aWRcIixcInBob25lXCI6XCIxMjM0NTY3ODkxMFwiLFwiZGVzY3JpdGlvblwiOlwi5rWL6K-VIOepuuagvFwiLFwiYXZhdGFyXCI6XCIxNTUxMDU5NzA3Nzk3LmpwZ1wiLFwid2hlcmVWYWx1ZXNcIjpbXSxcInNhdmVPclVwZGF0ZVByb3BlcnRpZXNcIjpbXX0iLCJpc3MiOiJaV0giLCJleHAiOjE1NTMwOTcyNjJ9.22dkkLaJgMKx5MbwN-W4aTHHXl0Rn4ayAMvOUSyGfsE";
//        HttpUtil.sendGetRequest("api/friends/send_friend_request?from=tea&to=TXK&token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NTE4MDEyNjIsInN1YiI6IntcImlkXCI6MjgsXCJ1c2VybmFtZVwiOlwidGVhXCIsXCJwYXNzd29yZFwiOlwiKioqKioqXCIsXCJ0ZWFjaGVyX2lkXCI6XCIyMDE1MTkxMDAwXCIsXCJjbGFzc19pZFwiOlwiMDAwMDAwXCIsXCJkZXBhcnRtZW50XCI6XCLmlZnogrLlrabns7tcIixcImVkdWNhdGlvblwiOjEsXCJkYXRlXCI6XCIwMDAwMDBcIixcInNjaG9vbFwiOlwi6Z-p5bGx5biI6IyD5a2m6ZmiXCIsXCJzZXhcIjowLFwibmFtZVwiOlwiRGF2aWRcIixcInBob25lXCI6XCIxMjM0NTY3ODkxMFwiLFwiZGVzY3JpdGlvblwiOlwi5rWL6K-VIOepuuagvFwiLFwiYXZhdGFyXCI6XCIxNTUxMDU5NzA3Nzk3LmpwZ1wiLFwid2hlcmVWYWx1ZXNcIjpbXSxcInNhdmVPclVwZGF0ZVByb3BlcnRpZXNcIjpbXX0iLCJpc3MiOiJaV0giLCJleHAiOjE1NTMwOTcyNjJ9.22dkkLaJgMKx5MbwN-W4aTHHXl0Rn4ayAMvOUSyGfsE");

//        HttpUtil.sendGetRequest("api/friends/set_request_status/4?status=1&token=" + token);
//        HttpUtil.sendGetRequest("api/friends/get_all_friends?username=tea&token=" + token);

        //http://139.199.87.26:9001/api/friends/get_all_to_requests?username=TXK&token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NDk4NTUxMjUsInN1YiI6IntcImlkXCI6MjcsXCJ1c2VybmFtZVwiOlwiVFhLXCIsXCJwYXNzd29yZFwiOlwiKioqKioqXCIsXCJzdHVkZW50X2lkXCI6XCIyMDE1MTkxMDM2XCIsXCJjbGFzc19pZFwiOlwiMjAxNTE5MTFcIixcImRlcGFydG1lbnRcIjpcIuaVmeiCsuWtpuezu1wiLFwiZWR1Y2F0aW9uXCI6MSxcImRhdGVcIjpcIjIwMTVcIixcInNjaG9vbFwiOlwi6Z-p5bGx5biI6IyD5a2m6ZmiXCIsXCJzZXhcIjowLFwibmFtZVwiOlwi6LCt5paw5aWOXCIsXCJwaG9uZVwiOlwiMTUzNjMzODYwMDlcIixcImRlc2NyaXRpb25cIjpcIuWlvee6oue6oueBq-eBq-aBjeaBjeaDmuaDmlwiLFwiYXZhdGFyXCI6XCIxNTQ5MDIzNTA2MTg3LmpwZ1wiLFwid2hlcmVWYWx1ZXNcIjpbXSxcInNhdmVPclVwZGF0ZVByb3BlcnRpZXNcIjpbXX0iLCJpc3MiOiJaV0giLCJleHAiOjE1NTExNTExMjV9.gyZXmoP0Rr2-M7iwi98qjyz1gZZdAMdmXFbYUSNIjus

//        HttpUtil.sendGetRequest("api/friends/get_all_to_requests?username=TXK&token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NDk4NTUxMjUsInN1YiI6IntcImlkXCI6MjcsXCJ1c2VybmFtZVwiOlwiVFhLXCIsXCJwYXNzd29yZFwiOlwiKioqKioqXCIsXCJzdHVkZW50X2lkXCI6XCIyMDE1MTkxMDM2XCIsXCJjbGFzc19pZFwiOlwiMjAxNTE5MTFcIixcImRlcGFydG1lbnRcIjpcIuaVmeiCsuWtpuezu1wiLFwiZWR1Y2F0aW9uXCI6MSxcImRhdGVcIjpcIjIwMTVcIixcInNjaG9vbFwiOlwi6Z-p5bGx5biI6IyD5a2m6ZmiXCIsXCJzZXhcIjowLFwibmFtZVwiOlwi6LCt5paw5aWOXCIsXCJwaG9uZVwiOlwiMTUzNjMzODYwMDlcIixcImRlc2NyaXRpb25cIjpcIuWlvee6oue6oueBq-eBq-aBjeaBjeaDmuaDmlwiLFwiYXZhdGFyXCI6XCIxNTQ5MDIzNTA2MTg3LmpwZ1wiLFwid2hlcmVWYWx1ZXNcIjpbXSxcInNhdmVPclVwZGF0ZVByb3BlcnRpZXNcIjpbXX0iLCJpc3MiOiJaV0giLCJleHAiOjE1NTExNTExMjV9.gyZXmoP0Rr2-M7iwi98qjyz1gZZdAMdmXFbYUSNIjus");

//                HttpUtil.sendGetRequest("api/user/get_user_by_username?username=tea&token=" + token);

//        Login user = new Login();
//        user.setUsername("zhaochaoren11");
//        user.setMd5_password(EncryptUtils.encryptMD5ToString("abc123456"));
//        user.setStudentId("2015191056");
//        user.setClassId("20151913");
//        user.setDepartment("外语系");
//        user.setEducation(1);
//        user.setDate("2017");
//        user.setSchool("社会大学");
//        user.setSex(0);
//        user.setName("赵超人");
//
//        HttpUtil.sendPostRequest("api/user/register", GsonUtils.toJson(user));

//        //普通密码
//        String plainPassword = "abc123456";
//
//        //md5加密
//        String md5PlainPassword = EncryptUtils.encryptMD5ToString(plainPassword);
//
//        //md5密码后加盐
//        String salt = "6NCkDWrVJy5K9v2w";
//        String saltPassword = md5PlainPassword + salt;
//
//        //加盐后的密码再MD5加密(最终密码)
//        String md5SaltPassword = EncryptUtils.encryptMD5ToString(saltPassword);
//
//        System.out.println(md5SaltPassword);
        

        //将最终密码上传到服务器


//        User user = new User();
//        user.setUsername("zhaochaoren11");
//        user.setPassword(EncryptUtils.encryptMD5ToString("abc123456"));
//
//
//        HttpUtil.sendPostRequest("api/user/login", GsonUtil.GsonString(user));

//        HttpUtil.sendGetRequest("api/user/is_new_user?username=TXK");
        HttpUtil.sendGetRequest("api/user/upgrade_all_account");


    }
}
