package com.example.liangjie06.zuche.fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.base.impl.HomePager;
import com.example.liangjie06.zuche.base.impl.NearByPager;
import com.example.liangjie06.zuche.base.impl.OrderPager;
import com.example.liangjie06.zuche.base.impl.PersonalPager;
import com.example.liangjie06.zuche.view.NoScrollViewPager;

import java.util.ArrayList;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class ContentFragment extends BaseFragment {

    private NoScrollViewPager mViewPager;
    private RadioGroup rgGroup;

    private ArrayList<BasePager> mPagers;


    @Override
    protected void initData() {
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NearByPager(mActivity));
        mPagers.add(new OrderPager(mActivity));
        mPagers.add(new PersonalPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());
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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagers.get(position);
                pager.initData();
                if (position == 0 || position == mPagers.size() - 1) {


                } else {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPagers.get(0).initData();

    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        mViewPager.setPagingEnabled(false);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }




    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);
            View view = pager.mRootView;

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


}
