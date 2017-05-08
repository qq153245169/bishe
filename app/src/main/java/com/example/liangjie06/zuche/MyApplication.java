package com.example.liangjie06.zuche;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.example.liangjie06.zuche.utils.ThreadPool;

import cn.bmob.v3.Bmob;


/**
 * Created by liangjie06 on 17/3/15.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ThreadPool.startup();
        //百度地图
//        SDKInitializer.initialize(mContext);

        Bmob.initialize(mContext, "dc4dece296142e01b61c37d36b108fee");

    }

    public static MyApplication getInstance() {
        return mContext;
    }
}
