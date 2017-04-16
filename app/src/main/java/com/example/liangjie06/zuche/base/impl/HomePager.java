package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.module.freeride.FreeRideActivity;
import com.example.liangjie06.zuche.module.gostore.GoStoreActivity;
import com.example.liangjie06.zuche.module.navi.NaviActivity;
import com.example.liangjie06.zuche.module.serach.SearchActivity;
import com.example.liangjie06.zuche.module.updoor.UpHomeActivity;
import com.example.liangjie06.zuche.module.youhui.DiscountActivity;
import com.example.liangjie06.zuche.view.BannerLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class HomePager extends BasePager implements View.OnClickListener{
    private BannerLayout mVPager;
    private List<BannerLayout.BannerEntity> urls = new ArrayList<>();

    public HomePager(Activity activity) {
        super(activity);

    }


    @Override
    public void initData() {
        View view = View.inflate(mActivity, R.layout.page_main, null);
        flContent.addView(view);

        view.findViewById(R.id.layout1).setOnClickListener(this);
        view.findViewById(R.id.layout2).setOnClickListener(this);
        view.findViewById(R.id.layout3).setOnClickListener(this);
        view.findViewById(R.id.layout4).setOnClickListener(this);
        view.findViewById(R.id.layout5).setOnClickListener(this);
        view.findViewById(R.id.layout6).setOnClickListener(this);
        mVPager = (BannerLayout) view.findViewById(R.id.vp_lunbo);
        update();

        mVPager.setOnPagerClickListener(new BannerLayout.OnPagerClickListener() {
            @Override
            public void onClick(BannerLayout.BannerEntity entity) {

                //TODO
                Log.e("lj","hahahahhaahhaha");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout1:
                UpHomeActivity.startActivity(mActivity);
                break;
            case R.id.layout2:
                GoStoreActivity.startActivity(mActivity);
                break;
            case R.id.layout3:
                DiscountActivity.startActivity(mActivity);
                break;
            case R.id.layout4:
                FreeRideActivity.startActivity(mActivity);
                break;
            case R.id.layout5:
                SearchActivity.startActivity(mActivity);
                break;
            case R.id.layout6:
                NaviActivity.startActivity(mActivity);
                break;
            default:
                break;
        }
    }


    public void update() {
        urls.clear();
            urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
            urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
            urls.add(new Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
            urls.add(new Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
            urls.add(new Entity("http://img3.imgtn.bdimg.com/it/u=2968209827,470106340&fm=21&gp=0.jpg"));
            urls.add(new Entity("http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg"));


        long t = System.currentTimeMillis();
        mVPager.setDatas(urls);
        Log.w("---", System.currentTimeMillis() - t + "");
    }


    private class Entity implements BannerLayout.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }
}
