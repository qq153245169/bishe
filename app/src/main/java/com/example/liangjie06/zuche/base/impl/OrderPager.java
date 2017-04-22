package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.register.LoginActivity;

import cn.bmob.v3.BmobUser;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class OrderPager extends BasePager {

    private User myUser;

    public OrderPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        myUser =  BmobUser.getCurrentUser(User.class);
        if (myUser == null){
            LoginActivity.startLoginActivity(mActivity);
        }else {
            Log.e("lj", "dengluchengg"+ myUser.getObjectId() + "name"+myUser.getUsername());
            View view = View.inflate(mActivity, R.layout.order_pager, null);
            flContent.addView(view);
        }



    }
}
