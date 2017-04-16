package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;

import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.module.register.LoginActivity;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class OrderPager extends BasePager {
    public OrderPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        LoginActivity.startLoginActivity(mActivity);


    }
}
