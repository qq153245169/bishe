package com.example.liangjie06.zuche.module.selectcar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;


/**
 * Created by liangjie06 on 17//6.
 */

public class TimeSelect extends FrameLayout {

    private TextView tvGetData;
    private TextView tvGetHour;
    private TextView tvGetTitle;


    private Context mContext;
    public TimeSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TimeSelect(Context context) {
        super(context);
        initView(context);
    }

    public TimeSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.time_select, this);
        tvGetTitle = (TextView) view.findViewById(R.id.get_car_title);
        tvGetData = (TextView) view.findViewById(R.id.get_car_time_day);
        tvGetHour = (TextView) view.findViewById(R.id.get_car_time_huors);

    }

    public void setGetTitle(String string) {
        tvGetTitle.setText(string);
    }

    public void setGetData(String string) {
        tvGetData.setText(string);
    }

    public String getGetData(){
        return String.valueOf(tvGetData.getText());
    }


    public void setTvGetHour(String string) {
        tvGetHour.setText(string);
    }

    public String getTvGetHour(){
        return String.valueOf(tvGetHour.getText());
    }
}
