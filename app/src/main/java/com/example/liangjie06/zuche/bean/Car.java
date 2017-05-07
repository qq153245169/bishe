package com.example.liangjie06.zuche.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by liangjie06 on 17/4/23.
 */

public class Car extends BmobObject {

    private String carName;
    private String xiangShu;
    private String paiLiang;
    private String chengZuo;
    private Boolean hot;
    private Boolean ice;
    private Boolean xin;
    private Boolean te;
    private Float price;
    private Integer leiXing;
    private BmobFile icon;
    private Integer type;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getXiangShu() {
        return xiangShu;
    }

    public void setXiangShu(String xiangShu) {
        this.xiangShu = xiangShu;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getLeiXing() {
        return leiXing;
    }

    public void setLeiXing(Integer leiXing) {
        this.leiXing = leiXing;
    }

    public Boolean getTe() {
        return te;
    }

    public void setTe(Boolean te) {
        this.te = te;
    }

    public Boolean getXin() {
        return xin;
    }

    public void setXin(Boolean xin) {
        this.xin = xin;
    }

    public Boolean getIce() {
        return ice;
    }

    public void setIce(Boolean ice) {
        this.ice = ice;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public String getChengZuo() {
        return chengZuo;
    }

    public void setChengZuo(String chengZuo) {
        this.chengZuo = chengZuo;
    }

    public String getPaiLiang() {
        return paiLiang;
    }

    public void setPaiLiang(String paiLiang) {
        this.paiLiang = paiLiang;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
