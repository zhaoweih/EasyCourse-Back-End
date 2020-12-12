1. 修改用户信息 方式：POST

`api/user/modify`

一定要有id字段，代表修改的用户id

可修改的字段有

```java

private String studentId;

private String teacherId;

private String classId;

private String department;

private Integer education;

private String date;

private String school;

private Integer sex;

private String name;

private String phone;

private String descrition;
```

请求示例：例如修改学校（school字段）

```json

{

​            "id": 26,

​            "school": "韩山师范学院B区C栋"

​        }

```

请求返回：
```json
{

​    "payload": null,

​    "success": true,

​    "msg": null,

​    "code": 200,

​    "timestamp": 1541947153

}
```




1. 修改用户密码 方法：post

`api/user/modifyUserPwd`

请求示例：

说明：oldPassword旧密码，newPassword新密码
```json
{

​            "id": 26,

​            "oldPassword":"abbccc",

​            "newPassword":"abc123456"

​        }
```


请求返回：
```json
{

​    "payload": null,

​    "success": true,

​    "msg": null,

​    "code": 200,

​    "timestamp": 1541948163

}
```


1. 上传文件 方法：post

`api/stuffs/upload`

返回上传后的文件地址



4.提交资源 post
`api/resources/submit`
```java
/**
     * 自动生成的ID，不用管
     */
    private Integer id;

    /**
     * 教师Id
     */
    private String teacher_id;

    /**
     * 上传后的资源地址
     */
    private String res_url;

    /**
     * 资源名字
     */

    private String res_name;

    /**
     * 资源大小
     */
    private Integer res_size;

    /**
     * 归属课程Id
     */
    private Integer class_id;

    /**
     * 上传日期
     */
    private String res_time;

```

返回json

查询资源
get方法
`query`
@Param Integer class_id

删除资源
post方法
`delete`
@Param Integer class_id

上传文件示例代码\n
okhttp3\n
```java
//    OkHttpClient client = new OkHttpClient();
//    private void uploadFile(String url, File file) {
//        // 创建一个RequestBody，文件的类型是image/png
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//        MultipartBody multipartBody = new MultipartBody.Builder()
//                // 设置type为"multipart/form-data"，不然无法上传参数
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "xxx.jpeg", requestBody)
////                .addFormDataPart("comment", "上传一个图片哈哈哈哈")
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(multipartBody)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("失败");
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("上传返回：\n" + response.body().string());
//            }
//        });
//    }
```

示例：api/resources/getOneResources?resourcesId=1/br
请求：请求方法get、请求参数resourcesId、参数类型int/br
描述:根据id获取单个资源 具体场景：二维码扫描面对面分享资源/br
返回值：/br
```json
{
    "payload": {
        "id": 3,
        "teacher_id": "20151910",
        "res_url": "http://zhaoweihao.com",
        "res_name": "博客",
        "res_size": 50,
        "class_id": 2016,
        "res_time": "2018-09"
    },
    "success": true,
    "msg": null,
    "code": 200,
    "timestamp": 1545138513
}
```

/br

删除/api

```json
{
    "prefix": "http://zhaoweihao.com:9001/api/",
    "restList": [
        {
            "suffix": "user/login",
            "method": "post",
            "description": "登录功能",
            "param": null
        },
        {
            "suffix": "user/register",
            "method": "post",
            "description": "注册功能",
            "param": null
        },
        {
            "suffix": "seat/get",
            "method": "get",
            "description": "获取点名座位表数据",
            "param": null
        },
        {
            "suffix": "seat/post",
            "method": "post",
            "description": "提交点名座位表数据",
            "param": null
        },
        {
            "suffix": "leave/query",
            "method": "get",
            "description": "查询请假条",
            "param": "@Param studentId,@Param teacherId"
        },
        {
            "suffix": "leave/submit",
            "method": "post",
            "description": "提交请假条",
            "param": null
        },
        {
            "suffix": "leave/confirm",
            "method": "post",
            "description": "确认请假条状态",
            "param": null
        },
        {
            "suffix": "course/submit",
            "method": "post",
            "description": "老师提交课程",
            "param": "@Param int tecId,@Param String teacherId,@Param String courseName"
        },
        {
            "suffix": "course/query",
            "method": "get",
            "description": "查询课程信息",
            "param": "@Param courseName,@Param teacherId"
        },
        {
            "suffix": "course/select",
            "method": "post",
            "description": "学生选课",
            "param": "@Param int courseId,@Param int stuId,@Param String studentId"
        },
        {
            "suffix": "course/querySelectByStuId",
            "method": "get",
            "description": "查看学生选课纪录",
            "param": "@Param stuId"
        },
        {
            "suffix": "course/querySelectByCourseId",
            "method": "get",
            "description": "查看课程被选纪录",
            "param": "@Param courseId"
        },
        {
            "suffix": "noti/sendCourseNoti",
            "method": "post",
            "description": "老师发送通知",
            "param": null
        },
        {
            "suffix": "noti/queryCourseNotiByCourseId",
            "method": "get",
            "description": "查询课程通知",
            "param": "@Param courseId"
        }
    ]
}
```

Log:
对整体文件位置进行更改 181218 09:28




