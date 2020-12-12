package com.blade.demo.route.util.oauth;

public class ApiKey {
    public static final String PASSWORD = "asd6313113";//token密钥
    public static final String CONTENT = "ZWH";//token签名
    public static final String JWT_ID = "jwt";//JWT ID
    public static final String ISSUERS = "ZWH";//签发者

    /**
     * 状态码
     */
    //成功
    public static final int REQUEST_OK = 200;//请求正常处理完毕

    //客户端错误
    public static final int BAD_REQUEST = 400;//表示请求报文存在语法错误或参数错误，服务器不理解
    public static final int UNAUTHORIZED = 401;//表示发送的请求需要有HTTP认证信息或者是认证失败了
    public static final int FORBIDDEN = 403;//表示对请求资源的访问被服务器拒绝了，比如没有权限
    public static final int CONFLICT = 409;//请求冲突

    //服务端错误
    public static final int SERVER_ERROR = 500;

    public static final long EXPRIRE_DAYS = 15L;
}
