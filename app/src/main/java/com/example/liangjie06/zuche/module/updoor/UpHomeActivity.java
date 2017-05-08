package com.example.liangjie06.zuche.module.updoor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.global.TimePickGlobal;
import com.example.liangjie06.zuche.module.selectcar.view.PartSelect;
import com.example.liangjie06.zuche.module.selectcar.view.TimeSelect;
import com.example.liangjie06.zuche.module.selectcar.view.TimeSelecterCenter;
import com.example.liangjie06.zuche.view.SwitchButton;
import com.example.liangjie06.zuche.view.datawheel.DateScrollerDialog;
import com.example.liangjie06.zuche.view.datawheel.data.Type;
import com.example.liangjie06.zuche.view.datawheel.listener.OnDateSetListener;
import com.example.liangjie06.zuche.view.hotcity.CityListActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class UpHomeActivity extends AppCompatActivity implements View.OnClickListener{

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
    private PartSelect mPart3;
    private Button mButton;
    private TimeSelect mLeftTime;
    private TimeSelect mRightTime;
    private SwitchButton mReturnSwitch;
    private SwitchButton mGetSwitch;
    private TimeSelecterCenter mCenterTime;
    private int leftDay;
    private int rightDay;
    private Context mContext;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,UpHomeActivity.class);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_door);
        mContext = this;
        initView();
        initData();
    }

    private void initView(){
        mPart1 = (PartSelect) findViewById(R.id.go_strore_part1);
        mPart3 = (PartSelect) findViewById(R.id.go_strore_part3);
        mGetSwitch = (SwitchButton) findViewById(R.id.get_switch);
        mButton = (Button) findViewById(R.id.go_strore_to_selctor_car);
        mReturnSwitch = (SwitchButton) findViewById(R.id.return_switch);
        mLeftTime = (TimeSelect) findViewById(R.id.go_strore_time);
        mRightTime = (TimeSelect) findViewById(R.id.put_strore_time);
        mCenterTime = (TimeSelecterCenter) findViewById(R.id.time_center);
        mLeftTime.setOnClickListener(this);
        mRightTime.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    private void initData() {
        String text = getDateToString(System.currentTimeMillis());
        leftDay = getDay(System.currentTimeMillis());
        String rightTime = getDateToString(System.currentTimeMillis() + TimePickGlobal.ONE_DAY );
        mLeftTime.setGetData(text);
        mRightTime.setGetData(rightTime);
        mCenterTime.setTvCenterDays("1");
        mPart1.setOnClickListener(this);
        mPart3.setOnClickListener(this);
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
                //SelectActivity.startActivity(mContext);
                break;
            case R.id.go_strore_part1:
                getCityName(1);
                break;

            case R.id.go_strore_part3:
                getCityName(3);
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
                dialog.show(getSupportFragmentManager(), "year_month_day");
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

    private void getCityName(int num) {
        Intent intent = new Intent(mContext, CityListActivity.class);
        intent.putExtra("item", num);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        String cityName = bundle.getString("city");
        switch (resultCode){
            case 1:
                mPart1.setPartDesc(cityName);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
