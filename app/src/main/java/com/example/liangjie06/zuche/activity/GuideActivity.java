package com.example.liangjie06.zuche.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by Jack-Liang on 2016/8/22.
 */
public class GuideActivity extends Activity {
    private ViewPager mViewPager;
    private LinearLayout ll_container;
    private ImageView iv_red_point;
    private Button btn_start;
    private ArrayList<ImageView> mImageViewList;

    int[] mImageIds = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

    private int mPointDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initUI();
        initData();
    }

    private void initData() {
        mViewPager.setAdapter(new MyAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point
                        .getLayoutParams();
                params.leftMargin = leftMargin;


                iv_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() - 1) {
                    btn_start.setVisibility(View.VISIBLE);
                } else {
                    btn_start.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_red_point.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
                mPointDis = ll_container.getChildAt(1).getLeft()
                        - ll_container.getChildAt(0).getLeft();

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
        btn_start = (Button) findViewById(R.id.btn_start);

        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);

            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);


            if(i>0){
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);

            ll_container.addView(point);
        }
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }
}
