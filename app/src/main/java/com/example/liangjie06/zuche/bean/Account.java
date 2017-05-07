package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liangjie06 on 17/4/21.
 */

public class Account extends BmobObject {
    private String userName;
    private String bankCard;
    private String bank;
    private String name;
    private String id;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id1) {
        this.id = id1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
