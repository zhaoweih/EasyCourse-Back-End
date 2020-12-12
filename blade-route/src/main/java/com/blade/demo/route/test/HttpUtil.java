package com.blade.demo.route.test;

import okhttp3.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;

/**
 * 网络测试类
 * @author zhaoweihao
 */
public class HttpUtil {

    /**
     * 切换服务器测试
     */
    static final boolean isGz = false;

    /**
     * 更换host url 广州服务器
     */
    private static String gz_prefix = "http://139.199.87.26:9001/";

    /**
     * 本地服务器
     */
    private static String prefix = "http://127.0.0.1:9001/";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static {
        if (isGz) {
            prefix = gz_prefix;
        }
    }

    /**
     * 测试普通网络请求
     * @param address
     */
    public static void sendNormalRequest(String address) {
        System.out.println("reqeust url === " + address);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response json === " + response.body().string());
                System.exit(0);

            }
        });
    }

    /**
     * 测试get请求
     * @param address
     */
    public static void sendGetRequest(String address) {
        address = prefix + address;
        System.out.println("reqeust url === " + address);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response json === " + response.body().string());
                System.exit(0);

            }
        });
    }

    /**
     * 测试post请求
     * @param address
     * @param json
     */
    public static void sendPostRequest(String address, String json) {
        address = prefix + address;
        System.out.println("reqeust url === " + address);
        System.out.println("request json === " + json);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response json === " + response.body().string());
                System.exit(0);
            }
        });
    }

    /**
     * 测试post文件请求
     * @param address
     * @param file
     */
    public static void sendPostRequestWithFile(String address, File file) {
        address = prefix + address;
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
        MultipartBody mb = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_JPEG, file)).build();
        Request request = new Request.Builder()
                .url(address)
                .post(mb)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response json === " + response.body().string());
                System.exit(0);
            }
        });
    }

}