package com.blade.demo.route;

import com.blade.Blade;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.jdbc.Base;

/**
 * 配置文件
 */
@Bean
public class LoadConfig implements BeanProcessor {

    @Override
    public void processor(Blade blade) {
        //线上环境
//        Base.open("jdbc:mysql://127.0.0.1:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "asd6906160");
        //本地环境
//        Base.open("jdbc:mysql://127.0.0.1:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "asd6313113");
        //连接服务器数据库(本地调试)
        Base.open("jdbc:mysql://139.199.87.26:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","asd6906160");

    }

}
