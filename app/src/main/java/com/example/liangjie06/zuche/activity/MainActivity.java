package com.example.liangjie06.zuche.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.module.mainpager.HomeFragment;
import com.example.liangjie06.zuche.module.mainpager.NearByFragment;
import com.example.liangjie06.zuche.module.mainpager.OrderFragment;
import com.example.liangjie06.zuche.module.mainpager.PersonalFragment;
import com.example.liangjie06.zuche.module.selectcar.adapter.MyFragmentPagerAdapter;
import com.example.liangjie06.zuche.view.NoScrollViewPager;

import java.util.ArrayList;


/**
 * Created by Jack-Liang on 2016/8/22.
 */
public class MainActivity extends FragmentActivity{

    private ArrayList<Fragment> fragments;
    private NoScrollViewPager mViewPager;
    private RadioGroup rgGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_content);

        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_content);
        mViewPager.setPagingEnabled(false);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);

        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new NearByFragment());
        fragments.add(new OrderFragment());
        fragments.add(new PersonalFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);


        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:

                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:

                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:

                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:

                        mViewPager.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            }
        });


    }



}
