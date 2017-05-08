package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liangjie06 on 17/5/7.
 */

public class JiFen extends BmobObject {

    private Float jiFen = 0.0f;
    private Float curJifen = 0.0f;
    private String userName;

    public Float getJiFen() {
        return jiFen;
    }

    public void setJiFen(Float jiFen) {
        this.jiFen = jiFen;
    }

    public Float getCurJifen() {
        return curJifen;
    }

    public void setCurJifen(Float curJifen) {
        this.curJifen = curJifen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
