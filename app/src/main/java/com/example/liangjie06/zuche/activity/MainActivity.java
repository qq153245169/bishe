package com.example.liangjie06.zuche.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.fragment.ContentFragment;

/**
 * Created by Jack-Liang on 2016/8/22.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        try {
            initFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //初始化fragment
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//开始事务
        transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);

        transaction.commit();// 提交事务
    }


    //
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragment = (ContentFragment) fm
                .findFragmentByTag(TAG_CONTENT);//
        return fragment;
    }
}
