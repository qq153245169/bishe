package com.example.liangjie06.zuche.module.mainpager;

import android.util.Log;
import android.view.View;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.fragment.BaseFragment;
import com.example.liangjie06.zuche.module.register.LoginActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by liangjie06 on 17/5/7.
 */

public class OrderFragment extends BaseFragment {

    private User myUser;
    private View view;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        myUser =  BmobUser.getCurrentUser(User.class);
        if (myUser == null){
            LoginActivity.startLoginActivity(mActivity);
        }else {
            Log.e("lj", "dengluchengg"+ myUser.getObjectId() + "name"+myUser.getUsername());
            view = View.inflate(mActivity, R.layout.order_pager, null);
        }
        return view;
    }
}
