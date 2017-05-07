package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liangjie06 on 17/4/21.
 */

public class Order extends BmobObject {

    private String partFrom;
    private String partTo;
    private String timeFrom;
    private String timeTo;
    private Float dayMoney;
    private Float allMoney;
    private Integer day;
    private String carName;
    private String completeTime;
    private Boolean isComplete;
    private Boolean isPay;
    private Boolean isDelay;
    private String userName;
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(Float allMoney) {
        this.allMoney = allMoney;
    }

    public Boolean getDelay() {
        return isDelay;
    }

    public void setDelay(Boolean delay) {
        isDelay = delay;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getPartFrom() {
        return partFrom;
    }

    public void setPartFrom(String partFrom) {
        this.partFrom = partFrom;
    }

    public String getPartTo() {
        return partTo;
    }

    public void setPartTo(String partTo) {
        this.partTo = partTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Float getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(Float dayMoney) {
        this.dayMoney = dayMoney;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

}
