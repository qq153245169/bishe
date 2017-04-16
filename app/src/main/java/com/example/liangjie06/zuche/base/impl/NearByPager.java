package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.liangjie06.zuche.base.BasePager;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class NearByPager extends BasePager {
    public NearByPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);


    }
}
