package com.example.liangjie06.zuche.module.selectcar;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.module.selectcar.adapter.MyFragmentPagerAdapter;
import com.example.liangjie06.zuche.module.selectcar.fragment.FirstFragment;
import com.example.liangjie06.zuche.module.selectcar.fragment.FiveFragment;
import com.example.liangjie06.zuche.module.selectcar.fragment.FourFragment;
import com.example.liangjie06.zuche.module.selectcar.fragment.SecondFragment;
import com.example.liangjie06.zuche.module.selectcar.fragment.ThridFragment;


/**
 * @author IT_ZJYANG
 *         ViewPager+fragment实现滑动切换子页面
 */
public class SelectActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {

    private ViewPager myviewpager;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    //选项卡中的按钮
    private TextView btn_first;
    private TextView btn_second;
    private TextView btn_third;
    private TextView btn_four;
    private TextView btn_suv;
    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的集合
    private int[] widthArgs;
    //所有按钮的集合
    private TextView[] btnArgs;

    public String getPart;
    public String retPart;
    public long getTime;
    public long retTime;
    public int dayCount;

    public static void startActivity(Context context, String getPart, String retPart,
                                     Long getTime, Long retrunTime, int day) {
        Intent intent = new Intent(context, SelectActivity.class);
        intent.putExtra("getP", getPart);
        intent.putExtra("retP", retPart);
        intent.putExtra("getT", getTime);
        intent.putExtra("retT", retrunTime);
        intent.putExtra("day", day);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_car);
        getPart = getIntent().getStringExtra("getP");
        retPart = getIntent().getStringExtra("retP");
        getTime = getIntent().getLongExtra("getT",0);
        retTime = getIntent().getLongExtra("retT",0);
        dayCount = getIntent().getIntExtra("day", 2);
        initView();
    }

    public void initView() {
        myviewpager = (ViewPager) this.findViewById(R.id.myviewpager);

        btn_first = (TextView) this.findViewById(R.id.btn_first);
        btn_second = (TextView) this.findViewById(R.id.btn_second);
        btn_third = (TextView) this.findViewById(R.id.btn_third);
        btn_four = (TextView) this.findViewById(R.id.btn_four);
        btn_suv = (TextView) findViewById(R.id.btn_suv);
        btnArgs = new TextView[]{btn_first, btn_second, btn_third, btn_four, btn_suv};

        cursor = (ImageView) this.findViewById(R.id.cursor_btn);
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
        btn_suv.setOnClickListener(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThridFragment());
        fragments.add(new FourFragment());
        fragments.add(new FiveFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
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
        btn_suv.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_suv.setTextColor(Color.BLACK);
        btn_first.setTextColor(Color.BLACK);
        btn_second.setTextColor(Color.BLACK);
        btn_third.setTextColor(Color.BLACK);
        btn_four.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View whichbtn) {
        // TODO Auto-generated method stub

        switch (whichbtn.getId()) {
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
            case R.id.btn_suv:
                myviewpager.setCurrentItem(4);
                cursorAnim(4);

                break;
        }
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
                    btn_suv.getWidth()};
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
}
