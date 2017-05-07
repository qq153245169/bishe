package com.example.liangjie06.zuche.module.selectcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.PayActivity;
import com.example.liangjie06.zuche.activity.SelectBankCardActivity;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.Car;
import com.example.liangjie06.zuche.bean.Order;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.ThreadPool;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by liangjie06 on 17/4/23.
 */

public class JiaoYiActivity extends BaseActivity {

    public String getPart;
    public String retPart;
    public String getTime;
    public String retTime;
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
    public static void startActivity(Context context, String getPart, String retPart,
                                     String getTime, String retrunTime, int day, Car car) {
        Intent intent = new Intent(context, JiaoYiActivity.class);
        intent.putExtra("getP", getPart);
        intent.putExtra("retP", retPart);
        intent.putExtra("getT", getTime);
        intent.putExtra("retT", retrunTime);
        intent.putExtra("day", day);
        intent.putExtra("car", car);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jy);
        myUser = BmobUser.getCurrentUser(User.class);

        getPart = getIntent().getStringExtra("getP");
        retPart = getIntent().getStringExtra("retP");
        getTime = getIntent().getStringExtra("getT");
        retTime = getIntent().getStringExtra("retT");
        dayCount = getIntent().getIntExtra("day", 2);
        car = (Car) getIntent().getSerializableExtra("car");

        initView();
        initData();
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

                Intent intent = new Intent(getApplication(),PayActivity.class);
                intent.putExtra("money", car.getPrice() * dayCount);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivityForResult(intent,10);
            }
        });
    }

    private void initData(){
        tvGetT.setText(getTime);
        tvGetp.setText(getPart);
        tvRetT.setText(retTime);
        tvRetP.setText(retPart);
        tvDayCount.setText(dayCount+"");
        tvMoney.setText(car.getPrice() * dayCount+"");
        Glide.with(mContext).load(car.getIcon().getUrl()).placeholder(R.drawable.car_image).into(ivCar);
        //tvJifen.setText(myUser.getJiFen());
        tvCarName.setText(car.getCarName());
        tvCarDesc.setText(car.getXiangShu() + "｜" +
                car.getPaiLiang() + "｜" + car.getChengZuo());
        tvPrice.setText(car.getPrice()+"");
    }

    private void updateInfo(final boolean isComplete, final boolean isPay){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                Order order = new Order();
                order.setUserName(myUser.getUsername());
                order.setOrderId(System.currentTimeMillis());
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            Log.e("lj","提交支付了");
            updateInfo(false, true);
        }else {
            updateInfo(false,false);
            Log.e("lj","订单未支付，直接推出了");
        }
    }


}
