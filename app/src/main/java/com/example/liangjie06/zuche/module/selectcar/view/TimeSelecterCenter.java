package com.example.liangjie06.zuche.module.selectcar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;


/**
 * Created by liangjie06 on 17/4/12.
 */

public class TimeSelecterCenter extends FrameLayout {

    private TextView tvCenterDays;
    private Context mContext;
    public TimeSelecterCenter(Context context) {
        super(context);
        initView(context);
    }

    public TimeSelecterCenter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TimeSelecterCenter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.time_selecter_center, this);
        tvCenterDays = (TextView) view.findViewById(R.id.center_days);
    }

    public void setTvCenterDays(String string) {
        tvCenterDays.setText(string);
    }

    public String getTvCenterDays(){
        return String.valueOf(tvCenterDays.getText());
    }
}
