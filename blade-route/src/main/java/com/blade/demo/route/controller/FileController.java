package com.blade.demo.route.controller;


import com.blade.mvc.Const;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.MultipartParam;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.multipart.FileItem;
import com.blade.mvc.ui.RestResponse;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 上传文件控制器
 * @author Zhaoweihao
 */
@Path("api/stuffs")
public class FileController {
    /**
     * 注意：上传文件字段名字一定要是file
     * @param fileItem
     * @return
     */
    @PostRoute("upload")
    @JSON
    public RestResponse doUpload(@MultipartParam FileItem fileItem) {
        System.out.println(fileItem.toString());
        if (null != fileItem) {
            byte[] data = fileItem.getData();
            try {
                //文件名字
                String fileName = fileItem.getFileName();
                //文件后缀
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                //文件最终名字
                String fileNameFinal = System.currentTimeMillis() + suffix;
                //文件存储路径
                String path = Const.CLASSPATH + "/upload/" + fileNameFinal;
                File file = new File(path);
                //创建文件夹
                file.getParentFile().mkdirs();
                if (!file.exists()) {
                    //如果文件不存在则创建新文件
                    file.createNewFile();
                }
                //将文件写入
                Files.write(Paths.get(path), data);
                //返回存储路径
                return RestResponse.ok("/upload/" + fileNameFinal,200);
            } catch (IOException e) {
                e.printStackTrace();
                return RestResponse.fail(e.getMessage());
            }
        }
        return RestResponse.ok(null, 200);
    }

    /**
     * 示例上传代码
     * okhttp3
     */

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
}
