package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by liangjie06 on 17/4/16.
 */

public class User extends BmobUser{
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(boolean userSex) {
        this.userSex = userSex;
    }

    public String getNameame() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getUserJS() {
        return userJSZ;
    }

    public void setUserJS(String userJS) {
        this.userJSZ = userJS;
    }

    public Integer getJiFen() {
        return jiFen;
    }

    public void setJiFen(Integer jiFen) {
        this.jiFen = jiFen;
    }

    public Integer getCurJifen() {
        return curJifen;
    }

    public void setCurJifen(Integer curJifen) {
        this.curJifen = curJifen;
    }

    private String name;
    private Boolean userSex;
    private String userID;
    private String userJSZ;
    private Integer jiFen = 0;
    private Integer curJifen = 0;

}
