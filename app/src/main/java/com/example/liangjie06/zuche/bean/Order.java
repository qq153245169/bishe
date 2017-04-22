package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liangjie06 on 17/4/21.
 */

public class Order extends BmobObject {
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private String objectId;
    private String partFrom;
    private String partTo;
    private String timeFrom;
    private String timeTo;
    private String dayMoney;
    private String day;
    private String carName;
    private String completeTime;
    private boolean isComplete;

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

    public String getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(String dayMoney) {
        this.dayMoney = dayMoney;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }


}
