package com.example.liangjie06.zuche.module.selectcar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.PayActivity;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.Car;
import com.example.liangjie06.zuche.bean.JiFen;
import com.example.liangjie06.zuche.bean.Order;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.register.LoginActivity;
import com.example.liangjie06.zuche.module.selectcar.view.PartSelect;
import com.example.liangjie06.zuche.utils.NumberUtils;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.example.liangjie06.zuche.utils.TimeUtils;

import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by liangjie06 on 17/4/23.
 */

public class JiaoYiActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                tvJifen.setText(NumberUtils.float2(curJiFen/10)+"");
                Log.e("lj","woshijifen"+curJiFen);
            }else {
                tvJifen.setText(0+"");
            }
        }
    };

    public String getPart;
    public String retPart;
    public long getTime;
    public long retTime;
    public int dayCount;

    public ImageView ivCar;
    public TextView tvCarName;
    public TextView tvCarDesc;
    public ImageView ivXin;
    public ImageView ivRe;
    public ImageView ivte;
    public TextView tvPrice;
    private TextView tvGetT;
    private TextView tvGetp;
    private TextView tvRetT;
    private TextView tvRetP;
    private TextView tvDayCount;
    private TextView tvMoney;
    private TextView tvJifen;
    private CheckBox checkBox;
    private Button btnCommit;
    private User myUser;
    private Account account;
    private Car car;
    private float curJiFen;
    private float money;
    private boolean hasChecked;
    private String objectId;
    private float allJifen;
    private String mFrom;
    private Order mOrder;

    public static void startActivity(Context context, String getPart, String retPart,
                                     Long getTime, Long retrunTime, int day, Car car, String from) {
        Intent intent = new Intent(context, JiaoYiActivity.class);
        intent.putExtra("getP", getPart);
        intent.putExtra("retP", retPart);
        intent.putExtra("getT", getTime);
        intent.putExtra("retT", retrunTime);
        intent.putExtra("day", day);
        intent.putExtra("car", car);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }
    public static void startActivityFromOrder(Context context, String getPart, String retPart,
                                     Long getTime, Long retrunTime, int day, Order car, String from) {
        Intent intent = new Intent(context, JiaoYiActivity.class);
        intent.putExtra("getP", getPart);
        intent.putExtra("retP", retPart);
        intent.putExtra("getT", getTime);
        intent.putExtra("retT", retrunTime);
        intent.putExtra("day", day);
        intent.putExtra("car", car);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jy);
        myUser = BmobUser.getCurrentUser(User.class);

        getPart = getIntent().getStringExtra("getP");
        retPart = getIntent().getStringExtra("retP");
        getTime = getIntent().getLongExtra("getT",0);
        retTime = getIntent().getLongExtra("retT",0);
        dayCount = getIntent().getIntExtra("day", 2);
        mFrom = getIntent().getStringExtra("from");
        if (mFrom.equals("dingdan")){
            mOrder = (Order) getIntent().getSerializableExtra("car");

        }else {
            car = (Car) getIntent().getSerializableExtra("car");

        }
        getJiFen();

        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myUser = BmobUser.getCurrentUser(User.class);

    }

    private void initView(){
        ivCar = (ImageView) findViewById(R.id.icon_car);
        tvCarName = (TextView) findViewById(R.id.car_name);
        tvCarDesc = (TextView) findViewById(R.id.car_decs);
        ivXin = (ImageView) findViewById(R.id.car_xin);
        ivRe = (ImageView) findViewById(R.id.car_re);
        ivte = (ImageView) findViewById(R.id.car_te);
        tvPrice = (TextView) findViewById(R.id.car_price);
        tvGetT = (TextView) findViewById(R.id.gettime);
        tvGetp = (TextView) findViewById(R.id.tv_getp);
        tvRetT = (TextView) findViewById(R.id.rettime);
        tvRetP = (TextView) findViewById(R.id.tv_retp);
        tvDayCount = (TextView) findViewById(R.id.tv_day_count);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvJifen = (TextView) findViewById(R.id.tv_jifen);
        checkBox = (CheckBox) findViewById(R.id.cb);
        btnCommit = (Button) findViewById(R.id.btn_commit);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myUser == null){
                    LoginActivity.startLoginActivity(mContext);
                }else {
                    Intent intent = new Intent(getApplication(),PayActivity.class);
                    intent.putExtra("money", money);
                    Log.e("lj","allmoney jioayi "+money);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivityForResult(intent,10);
                }
            }
        });
    }

    private void initData(){
        tvGetT.setText(TimeUtils.getDateToYMD(getTime));
        tvGetp.setText(getPart);
        tvRetT.setText(TimeUtils.getDateToYMD(retTime));
        tvRetP.setText(retPart);
        tvDayCount.setText(dayCount+"");
        if (mFrom.equals("dingdan")){
            money = mOrder.getAllMoney();
            Glide.with(mContext).load(mOrder.getIcon()).placeholder(R.drawable.car_image).into(ivCar);
            //tvJifen.setText(myUser.getJiFen());
            tvCarName.setText(mOrder.getCarName());
            tvCarDesc.setText(mOrder.getXiangShu() + "｜" +
                    mOrder.getPaiLiang() + "｜" + mOrder.getChengZuo());
            tvPrice.setText(mOrder.getDayMoney()+"");
        }else {
            money = car.getPrice() * dayCount;
            Glide.with(mContext).load(car.getIcon().getUrl()).placeholder(R.drawable.car_image).into(ivCar);
            //tvJifen.setText(myUser.getJiFen());
            tvCarName.setText(car.getCarName());
            tvCarDesc.setText(car.getXiangShu() + "｜" +
                    car.getPaiLiang() + "｜" + car.getChengZuo());
            tvPrice.setText(car.getPrice()+"");
        }
        tvMoney.setText(money +"");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hasChecked = isChecked;
                    money = NumberUtils.float2(money - curJiFen /10);
                    tvMoney.setText(money +"");
                }else {
                    money = NumberUtils.float2(money + curJiFen /10);
                    tvMoney.setText(money +"");
                }
                Log.e("lj","isChecked" +isChecked);

            }
        });

    }

    private void updateInfo(final boolean isComplete, final boolean isPay, final boolean isChecked){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                if (isPay) {
                    JiFen jiFen = new JiFen();
                    allJifen = NumberUtils.float2(allJifen + money /100);
                    jiFen.setJiFen( allJifen);
                    curJiFen = NumberUtils.float2(curJiFen + money /100);
                    jiFen.setCurJifen(curJiFen);
                    if(isChecked){
                        float jifen = NumberUtils.float2(money / 100);
                        jiFen.setCurJifen(jifen);

                    }
                    jiFen.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                Log.e("lj", "积分更新成功");
                            }else {
                                Log.e("lj", "积分更新shibai");

                            }
                        }
                    });
                }

                Order order = new Order();
                if (mFrom.equals("dingdan")){
                    order.setValue("isPay",true);
                    order.update(mOrder.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                }else {
                    order.setUserName(myUser.getUsername());
                    order.setOrderId(System.currentTimeMillis() * 3);
                    order.setCarName(car.getCarName());
                    order.setDay(dayCount);
                    order.setPartFrom(getPart);
                    order.setPartTo(retPart);
                    order.setTimeFrom(getTime);
                    order.setTimeTo(retTime);
                    order.setDayMoney(car.getPrice());
                    order.setAllMoney(car.getPrice() * dayCount);
                    order.setComplete(isComplete);
                    order.setPay(isPay);
                    order.setDelay(false);
                    order.setChengZuo(car.getChengZuo());
                    order.setPaiLiang(car.getPaiLiang());
                    order.setXiangShu(car.getXiangShu());
                    order.setIcon(car.getIcon().getUrl());
                    order.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null){
                                Log.e("lj","订单提交成功");
                            }else {
                                Log.e("lj","订单提交失败"+e.toString());
                            }
                        }
                    });
                }

            }
        });
    }

    private void getJiFen(){
        final Message msg = Message.obtain();
        if (myUser != null){
            BmobQuery<JiFen> jiFenBmobQuery = new BmobQuery<JiFen>();
            jiFenBmobQuery.addWhereEqualTo("userName", myUser.getUsername())
                    .findObjects(new FindListener<JiFen>() {
                        @Override
                        public void done(List<JiFen> list, BmobException e) {
                            if (e == null) {
                                if (list.size() > 0) {
                                    curJiFen = list.get(0).getCurJifen();
                                    curJiFen = NumberUtils.float2(curJiFen);
                                    allJifen = list.get(0).getJiFen();
                                    allJifen = NumberUtils.float2(allJifen);
                                    objectId = list.get(0).getObjectId();
                                    msg.what = 1;
                                    mHandler.sendEmptyMessage(msg.what);
                                    Log.e("lj","积分查到"+list.get(0).getCurJifen() +"    "+list.get(0).getJiFen());
                                }else {
                                    Log.e("lj","积分没有查到");
                                }
                            }else {
                                Log.e("lj","chaxunshibai   积分没有查到");
                            }
                        }
                    });
        }else {
            msg.what = 2;
            mHandler.sendEmptyMessage(msg.what);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            Log.e("lj","提交支付了");
            updateInfo(false, true, hasChecked);
            finish();
        }else {
            updateInfo(false,false, hasChecked);
            Log.e("lj","订单未支付，直接推出了");
            finish();
        }
    }


}
