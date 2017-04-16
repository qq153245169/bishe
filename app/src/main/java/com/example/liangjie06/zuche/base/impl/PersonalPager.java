package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.view.View;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BasePager;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class PersonalPager extends BasePager {
    public PersonalPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        View view = View.inflate(mActivity, R.layout.activity_login,null);
        Header.setVisibility(View.GONE);


        flContent.addView(view);



    }
}
