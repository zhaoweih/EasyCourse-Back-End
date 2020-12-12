package com.blade.demo.route.util.oauth;

public class ExpiredException extends Exception {
    //无参构造方法
    public ExpiredException(){

        super();
    }

    //有参的构造方法
    public ExpiredException(String message){
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public ExpiredException(String message, Throwable cause){

        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public ExpiredException(Throwable cause) {

        super(cause);
    }
}
