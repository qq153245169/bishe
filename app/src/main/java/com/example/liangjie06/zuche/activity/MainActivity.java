package com.example.liangjie06.zuche.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.module.mainpager.HomeFragment;
import com.example.liangjie06.zuche.module.mainpager.NearByFragment;
import com.example.liangjie06.zuche.module.mainpager.OrderFragment;
import com.example.liangjie06.zuche.module.mainpager.PersonalFragment;
import com.example.liangjie06.zuche.module.selectcar.adapter.MyHomePagerAdapter;
import com.example.liangjie06.zuche.view.NoCacheViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by Jack-Liang on 2016/8/22.
 */
public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> fragments;
    private NoCacheViewPager mViewPager;
    private RadioGroup rgGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_content);

        mViewPager = (NoCacheViewPager) findViewById(R.id.vp_content);
        //mViewPager.setPagingEnabled(false);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);

        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new PersonalFragment());
        getFragmentManager();
        MyHomePagerAdapter adapter = new MyHomePagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);


        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:

                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_smart:

                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_gov:

                        mViewPager.setCurrentItem(2, false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
