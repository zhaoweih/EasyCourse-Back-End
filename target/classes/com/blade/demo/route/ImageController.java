package com.blade.demo.route;


import com.blade.mvc.Const;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.MultipartParam;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.multipart.FileItem;
import com.blade.mvc.ui.RestResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("api/img")
public class ImageController {
    @PostRoute("upload")
    @JSON
    public RestResponse doUpload(@MultipartParam FileItem fileItem) {
        if (null != fileItem) {
            byte[] data = fileItem.getData();
            // Save the temporary file to the specified path
            try {
                System.out.print("测试点1");
                String fileName = fileItem.getFileName();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String fileNameFinal = System.currentTimeMillis() + suffix;
                String path = Const.CLASSPATH + "/upload/" + fileNameFinal;
                Files.write(Paths.get(path), data);
                System.out.print(path);
                return RestResponse.ok("/upload/" + fileNameFinal,200);
            } catch (IOException e) {
                System.out.print("测试点2");
                e.printStackTrace();
                return RestResponse.fail(e.getMessage());
            }
        }
        System.out.print("测试点3");
        return RestResponse.ok();
    }
}
