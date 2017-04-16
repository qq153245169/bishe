package com.example.liangjie06.zuche.module.gostore;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.fragment.BaseFragment;
import com.example.liangjie06.zuche.global.TimePickGlobal;
import com.example.liangjie06.zuche.module.selectcar.SelectCarActivity;
import com.example.liangjie06.zuche.module.selectcar.view.PartSelect;
import com.example.liangjie06.zuche.module.selectcar.view.TimeSelect;
import com.example.liangjie06.zuche.module.selectcar.view.TimeSelecterCenter;
import com.example.liangjie06.zuche.view.SwitchButton;
import com.example.liangjie06.zuche.view.datawheel.DateScrollerDialog;
import com.example.liangjie06.zuche.view.datawheel.data.Type;
import com.example.liangjie06.zuche.view.datawheel.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liangjie06 on 17/4/13.
 */

public class GoStoreFragment extends BaseFragment implements View.OnClickListener{

    private SimpleDateFormat sf = new SimpleDateFormat("MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private boolean left = false;


    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            String text = getDateToString(milliseconds);
            if (left) {
                leftDay = getDay(milliseconds);
                Log.e("lj",leftDay + "leftDay");
                String rightTime = getDateToString(milliseconds + TimePickGlobal.ONE_DAY );
                mLeftTime.setGetData(text);
                mRightTime.setGetData(rightTime);
                mCenterTime.setTvCenterDays("1");
                left = false;
            } else {
                rightDay = getDay(milliseconds);
                Log.e("lj",leftDay + "rightDay");
                mRightTime.setGetData(text);
                mCenterTime.setTvCenterDays(rightDay - leftDay + "");
            }

        }
    };

    private PartSelect mPart1;
    private PartSelect mPart2;
    private PartSelect mPart3;
    private PartSelect mPart4;
    private Button mButton;
    private TimeSelect mLeftTime;
    private TimeSelect mRightTime;
    private SwitchButton mReturnSwitch;
    private SwitchButton mGetSwitch;
    private TimeSelecterCenter mCenterTime;
    private int leftDay;
    private int rightDay;
    private String mCityName;
    private boolean firstTime = true;

    @Override
    protected void initData() {
        String text = getDateToString(System.currentTimeMillis());
        leftDay = getDay(System.currentTimeMillis());
        String rightTime = getDateToString(System.currentTimeMillis() + TimePickGlobal.ONE_DAY );
        mLeftTime.setGetData(text);
        mRightTime.setGetData(rightTime);
        mCenterTime.setTvCenterDays("1");
        mPart1.setOnClickListener(this);
        mPart2.setOnClickListener(this);
        mPart3.setOnClickListener(this);
        mPart4.setOnClickListener(this);
        mGetSwitch.setChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void Onchanged(boolean isOpen) {
                if(isOpen){
                    mPart2.setPartTitleColor(R.color.partTitleColor2);
                    mPart2.setPartTitle("送车地址");
                    mPart2.setPartDesc("请选择地址");

                }else {
                    mPart2.setPartTitleColor(R.color.partTitleColor);
                    mPart2.setPartTitle("取车门店");
                    mPart2.setPartDesc("选择门店");
                }

            }
        });
        mReturnSwitch.setChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void Onchanged(boolean isOpen) {
                if(isOpen){
                    mPart4.setPartTitle("取车地址");
                    mPart4.setPartDesc("请选择地址");
                    mPart4.setPartTitleColor(R.color.partTitleColor2);
                }else {
                    mPart4.setPartTitle("还车门店");
                    mPart4.setPartDesc("选择门店");
                    mPart4.setPartTitleColor(R.color.partTitleColor);
                }
            }
        });
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_gostore, null);
        mPart1 = (PartSelect) view.findViewById(R.id.go_strore_part1);
        mPart2 = (PartSelect) view.findViewById(R.id.go_strore_part2);
        mPart3 = (PartSelect) view.findViewById(R.id.go_strore_part3);
        mPart4 = (PartSelect) view.findViewById(R.id.go_strore_part4);
        mGetSwitch = (SwitchButton) view.findViewById(R.id.get_switch);
        mButton = (Button) view.findViewById(R.id.go_strore_to_selctor_car);
        mReturnSwitch = (SwitchButton) view.findViewById(R.id.return_switch);
        mLeftTime = (TimeSelect) view.findViewById(R.id.go_strore_time);
        mRightTime = (TimeSelect) view.findViewById(R.id.put_strore_time);
        mCenterTime = (TimeSelecterCenter) view.findViewById(R.id.time_center);
        mLeftTime.setOnClickListener(this);
        mRightTime.setOnClickListener(this);
        mButton.setOnClickListener(this);
        if (!firstTime) {
            //getTransitiveData();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_strore_time:
                left = true;
                showDate(0);
                break;
            case R.id.put_strore_time:
                showDate(TimePickGlobal.ONE_DAY);
                break;
            case R.id.go_strore_to_selctor_car:
                SelectCarActivity.startActivity(mActivity);
                break;
            case R.id.go_strore_part1:
            case R.id.go_strore_part2:
            case R.id.go_strore_part3:
            case R.id.go_strore_part4:
                firstTime = false;
                break;
        }
    }
    /**
     * 显示日期
     */
    public void showDate(long later) {
        // 出生日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择出生日期")
                .setMinMilliseconds(System.currentTimeMillis() + later)
                .setMaxMilliseconds(System.currentTimeMillis() + TimePickGlobal.TWO_MONTH)
                .setCurMilliseconds(mLastTime + later)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getActivity().getSupportFragmentManager(), "year_month_day");
            }
        }
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    public int getDay(long time) {
        Date d = new Date(time);
        return d.getDate();
    }

    private void getCityName() {
        Intent intent = new Intent();
       // startActivityForResult();
    }
}
