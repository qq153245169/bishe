/*
package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.liangjie06.zuche.activity.MainActivity;
import com.example.liangjie06.zuche.base.BaseMenuDetailPager;
import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.global.GlobalConstans;
import com.example.liangjie06.zuche.utils.CacheUtils;
import com.google.gson.Gson;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;

import java.util.ArrayList;

*/
/**
 * Created by Jack-Liang on 2016/8/23.
 *//*

public class NewsCenterPager extends BasePager {

    private NewsMenu mNewsMenu;

    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;


    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("智慧");

        btnMenu.setVisibility(View.VISIBLE);
        String cache = CacheUtils.getCache(GlobalConstans.CATEGORY_URL,mActivity);
        if(!TextUtils.isEmpty(cache)){
            processData(cache);
        }
        getDataFromServer();



    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstans.CATEGORY_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                //请求成功， 获取服务器的返回结果
                String result = responseInfo.result;

                //解析数据
                processData(result);
                CacheUtils.setCache(GlobalConstans.CATEGORY_URL,result,mActivity);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                //请求失败
                e.printStackTrace();
                Toast.makeText(mActivity,s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processData(String json) {
        Gson gson = new Gson();
        mNewsMenu = gson.fromJson(json, NewsMenu.class);

        MainActivity mainUI = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
        leftMenuFragment.setMenuData(mNewsMenu.data);

        //初始化4个菜单详情页
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,mNewsMenu.data.get(0).children));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity,btnPhoto));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
        //将新闻中心详情页换为显示页
        setCurrentDetailPager(0);

    }

    public void setCurrentDetailPager(int position) {

        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);//当前应该显示的页面
        View view =  pager.mRootView;//当前页面的布局

        flContent.removeAllViews();
        flContent.addView(view);

        pager.initData();

        NewsMenu mNewsData ;
        tvTitle.setText(mNewsMenu.data.get(position).title);
        if (pager instanceof PhotosMenuDetailPager) {
            btnPhoto.setVisibility(View.VISIBLE);
        } else {

            btnPhoto.setVisibility(View.GONE);
        }
    }
}
*/
