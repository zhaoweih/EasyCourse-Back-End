package com.blade.demo.route;

import com.blade.Blade;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.jdbc.Base;

@Bean
public class LoadConfig implements BeanProcessor {

    @Override
    public void processor(Blade blade) {
        Base.open("jdbc:mysql://127.0.0.1:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "");
    }
}
