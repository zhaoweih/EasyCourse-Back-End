package com.blade.demo.route.api;

import java.util.List;

public class Api {
    /**
     * @prefix 前缀
     * @suffix 后缀
     * @method 请求方法
     * @description 说明
     * @example 例子
     */
    private String prefix;
    private List<Rest> restList;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Rest> getRestList() {
        return restList;
    }

    public void setRestList(List<Rest> restList) {
        this.restList = restList;
    }



}
