package com.example.liangjie06.zuche.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by Jack-Liang on 2016/8/25.
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }


    public abstract View initView();


    public void initData() {

    }
}
