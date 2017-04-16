package com.example.liangjie06.zuche.view.hotcity.bean;

/**
 * Created by liangjie06 on 17/4/13.
 */

public class City {

    private String name;
    private String pinyin;

    public City(String name, String pinyin) {
        super();
        this.name = name;
        this.pinyin = pinyin;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
