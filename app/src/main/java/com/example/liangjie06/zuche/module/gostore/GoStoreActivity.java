package com.example.liangjie06.zuche.module.gostore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.global.TimePickGlobal;
import com.example.liangjie06.zuche.module.selectcar.SelectActivity;
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

import static android.R.attr.data;
import static com.baidu.location.h.g.m;


/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class GoStoreActivity extends AppCompatActivity implements View.OnClickListener{

    private SimpleDateFormat sf = new SimpleDateFormat("MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private boolean left = false;
    private int dayCount = 1;
    private boolean topSwitch = true;
    private boolean bottomSwitch = true;


    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            String text = getDateToString(milliseconds);
            if (left) {
                getTime = milliseconds;
                returnTime = milliseconds + TimePickGlobal.ONE_DAY;
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
                returnTime = milliseconds;
                dayCount = rightDay - leftDay;
                mCenterTime.setTvCenterDays(dayCount + "");
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
    private Context mContext;
    private Long getTime = System.currentTimeMillis();
    private Long returnTime = System.currentTimeMillis() + TimePickGlobal.ONE_DAY;


    public static void startActivity(Context context){
        Intent intent = new Intent(context,GoStoreActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gostore);
        mContext = this;
        initView();
        initData();
    }

    private void initView(){
        mPart1 = (PartSelect) findViewById(R.id.go_strore_part1);
        mPart2 = (PartSelect) findViewById(R.id.go_strore_part2);
        mPart3 = (PartSelect) findViewById(R.id.go_strore_part3);
        mPart4 = (PartSelect) findViewById(R.id.go_strore_part4);
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
                    topSwitch = true;

                }else {
                    mPart2.setPartTitleColor(R.color.partTitleColor);
                    mPart2.setPartTitle("取车门店");
                    mPart2.setPartDesc("选择门店");
                    topSwitch = false;
                }

            }
        });
        mReturnSwitch.setChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void Onchanged(boolean isOpen) {
                if(isOpen){
                    bottomSwitch = true;
                    mPart4.setPartTitle("取车地址");
                    mPart4.setPartDesc("请选择地址");
                    mPart4.setPartTitleColor(R.color.partTitleColor2);
                }else {
                    bottomSwitch = false;
                    mPart4.setPartTitle("还车门店");
                    mPart4.setPartDesc("选择门店");
                    mPart4.setPartTitleColor(R.color.partTitleColor);
                }
            }
        });
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
                String getStr = mPart1.getPartDesc() + mPart2.getHeight();
                String returnStr = mPart3.getPartDesc() + mPart4.getHeight();
                SelectActivity.startActivity(mContext, getStr, returnStr, getTime, returnTime, dayCount);
                break;
            case R.id.go_strore_part1:
                getCityName(1);
                break;
            case R.id.go_strore_part2:
                if (topSwitch){
                    showDialog(mPart2);

                }
                break;
            case R.id.go_strore_part3:
                getCityName(3);
                break;
            case R.id.go_strore_part4:
                if (bottomSwitch){
                    showDialog(mPart4);
                }
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

    private void showDialog(final PartSelect ps){
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_input, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.et_input);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                if (userInput.getText() != null){
                                    ps.setPartDesc(String.valueOf(userInput.getText()));
                                }else {
                                    Toast.makeText(mContext, "请输入正确地址", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

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
                    mPart2.setPartDesc(cityName);
                    break;
                case 3:
                    mPart3.setPartDesc(cityName);
                    break;
                case 4:
                    mPart4.setPartDesc(cityName);
                    break;
                default:
                    break;
            }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
