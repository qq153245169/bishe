package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liangjie06 on 17/4/16.
 */

public class User extends BmobObject{
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSF() {
        return userSFZ;
    }

    public void setUserSF(String userSF) {
        this.userSFZ = userSF;
    }

    public String getUserJS() {
        return userJSZ;
    }

    public void setUserJS(String userJS) {
        this.userJSZ = userJS;
    }

    public String userID;
    public String passWord;
    public String userName;
    public String userSex;
    public String userSFZ;
    public String userJSZ;

    /**
     *
     * @param id   id
     * @param pwd   ped
     * @param name   name
     * @param sex     sex
     * @param sfz    sfz
     * @param jsz    jsz
     */

   /* public User(String id, String pwd, String name, String sex, String sfz, String jsz){
        userID = id;
        passWord = pwd;
        userName = name;
        userSex = sex;
        userSFZ = sfz;
        userJSZ = jsz;
    }*/
}
