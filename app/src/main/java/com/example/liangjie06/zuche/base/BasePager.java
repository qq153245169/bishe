package com.example.liangjie06.zuche.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.MainActivity;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class BasePager {

    public Activity mActivity;

    public TextView tvTitle;
    public FrameLayout flContent;
    public RelativeLayout Header;

    public View mRootView;

    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    private View initView() {
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        //tvTitle = (TextView) view.findViewById(R.id.tv_title);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);

        return view;
    }

    public void initData(){

    }
}
