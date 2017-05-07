package com.example.liangjie06.zuche.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liangjie06.zuche.R;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract View initView();



    /**
     * 公共方法： 从碎片fragment1跳转到碎片fragment2
     *
     * @param fragment1
     *            当前fragment
     * @param fragment2
     *            跳转后的fragment
     *//*
    public void showFragment(Fragment fragment1, Fragment fragment2) {
        Log.e("lj","base");
        // 获取 FragmentTransaction  对象
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        //如果fragment2没有被添加过，就添加它替换当前的fragment1
                Log.e("lj1","第一次添加第一页");
                transaction.add(R.id.fl_main,fragment2)
                        .hide(fragment1)
                        .show(fragment2)
                        //加入返回栈，这样你点击返回键的时候就会回退到fragment1了
                        .addToBackStack("qw")
                        // 提交事务
                        .commitAllowingStateLoss();


    }*/
}
