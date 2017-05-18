package com.example.liangjie06.zuche.module.mainpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.base.BaseFragment;
import com.example.liangjie06.zuche.module.mainpager.order.AllFragment;
import com.example.liangjie06.zuche.module.mainpager.order.CompleteOrderFragment;
import com.example.liangjie06.zuche.module.mainpager.order.DelayOrderFragment;
import com.example.liangjie06.zuche.module.mainpager.order.PayOrderFragment;
import com.example.liangjie06.zuche.module.mainpager.order.UseingOrderFragment;
import com.example.liangjie06.zuche.module.register.LoginActivity;
import com.example.liangjie06.zuche.module.selectcar.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;



/**
 * Created by liangjie06 on 17/5/7.
 */

public class OrderFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private User myUser;
    private View view;
    private ViewPager myviewpager;
    private ArrayList<Fragment> fragments;
    //选项卡中的按钮
    private TextView btn_first;
    private TextView btn_second;
    private TextView btn_third;
    private TextView btn_four;
    private TextView btn_five;
    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的集合
    private int[] widthArgs;
    //所有按钮的集合
    private TextView[] btnArgs;

    private boolean isCreate;

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
        Log.e("lj", "Order  onCreate" + isCreate);
    }

    @Override
    protected View initView() {
        view = View.inflate(mActivity, R.layout.order_pager, null);
        myUser =  BmobUser.getCurrentUser(User.class);
        if (myUser == null){
            isCreate = true;
            LoginActivity.startLoginActivity(mActivity);
        }else {
            Log.e("lj", "dengluchengg"+ myUser.getObjectId() + "name"+myUser.getUsername());

            preView();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCreate){
            preView();
        }
        isCreate = false;
    }

    private void preView() {
        myviewpager = (ViewPager) view.findViewById(R.id.myviewpager);

        btn_first = (TextView) view.findViewById(R.id.btn_first);
        btn_second = (TextView) view.findViewById(R.id.btn_second);
        btn_third = (TextView) view.findViewById(R.id.btn_third);
        btn_four = (TextView) view.findViewById(R.id.btn_four);
        btn_five = (TextView) view.findViewById(R.id.btn_five);
        btnArgs = new TextView[]{btn_first, btn_second, btn_third, btn_four, btn_five};

        cursor = (ImageView) view.findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.RED);
        //通过此方法设置指示器的初始大小和位置
        btn_first.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
                //减去边距*2，以对齐标题栏文字
                lp.width = btn_first.getWidth() - btn_first.getPaddingLeft() * 2;
                cursor.setLayoutParams(lp);
                cursor.setX(btn_first.getPaddingLeft());
            }
        });

        myviewpager.setOnPageChangeListener(this);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new AllFragment());
        fragments.add(new PayOrderFragment());
        fragments.add(new CompleteOrderFragment());
        fragments.add(new DelayOrderFragment());
        fragments.add(new UseingOrderFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);

        resetButtonColor();
        btn_first.setTextColor(Color.RED);
    }

    //重置所有按钮的颜色
    public void resetButtonColor() {
        btn_first.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_second.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_third.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_four.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_five.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_five.setTextColor(Color.BLACK);
        btn_first.setTextColor(Color.BLACK);
        btn_second.setTextColor(Color.BLACK);
        btn_third.setTextColor(Color.BLACK);
        btn_four.setTextColor(Color.BLACK);
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        if (widthArgs == null) {
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth(),
                    btn_third.getWidth(),
                    btn_four.getWidth(),
                    btn_five.getWidth()};
        }
        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为红色
        btnArgs[arg0].setTextColor(Color.RED);
        cursorAnim(arg0);
    }

    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        //每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first:
                myviewpager.setCurrentItem(0);
                cursorAnim(0);

                break;
            case R.id.btn_second:
                myviewpager.setCurrentItem(1);
                cursorAnim(1);

                break;
            case R.id.btn_third:
                myviewpager.setCurrentItem(2);
                cursorAnim(2);

                break;
            case R.id.btn_four:
                myviewpager.setCurrentItem(3);
                cursorAnim(3);

                break;
            case R.id.btn_five:
                myviewpager.setCurrentItem(4);
                cursorAnim(4);

                break;

        }
    }

    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isCreate) {
            Log.e("lj", "Order  Hint");
           initView();
        } else {
            Log.e("lj", "Order   isVisibleToUser:"+isVisibleToUser + "   " + "isCreate:" +isCreate);
        }
    }*/
}
