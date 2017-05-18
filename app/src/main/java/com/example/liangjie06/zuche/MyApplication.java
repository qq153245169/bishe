package com.example.liangjie06.zuche;

import android.app.Application;
import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.example.liangjie06.zuche.view.hotcity.adapter.CityListAdapter;

import cn.bmob.v3.Bmob;


/**
 * Created by liangjie06 on 17/3/15.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ThreadPool.startup();
        initLocation();

        Bmob.initialize(mContext, "dc4dece296142e01b61c37d36b108fee");

    }

    public static MyApplication getInstance() {
        return mContext;
    }

    public void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        // 设置定位参数
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
