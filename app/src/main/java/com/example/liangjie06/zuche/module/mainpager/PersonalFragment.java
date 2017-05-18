package com.example.liangjie06.zuche.module.mainpager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.AboutActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.JiFen;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.base.BaseFragment;
import com.example.liangjie06.zuche.module.bankaccount.BankAccountActivity;
import com.example.liangjie06.zuche.module.fade.NewUserActivity;
import com.example.liangjie06.zuche.module.fade.QAActivity;
import com.example.liangjie06.zuche.module.personinfo.PersonInfoActivity;
import com.example.liangjie06.zuche.module.register.LoginActivity;
import com.example.liangjie06.zuche.module.vip.VipActivity;
import com.example.liangjie06.zuche.utils.NumberUtils;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liangjie06 on 17/5/7.
 */

public class PersonalFragment extends BaseFragment implements View.OnClickListener{


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Log.e("lj", "what   ==0");
                    if(!TextUtils.isEmpty(backCardId)){
                        String str = backCardId.substring(4, backCardId.length() - 4);
                        String newStr = backCardId.replace(str, " **** ");
                        tvBankCardID.setText(newStr);
                    }
                    break;
                case 1:
                    Log.e("lj", "what   ==1");
                    calculateJF();
                    break;
            }
        }
    };

    private TextView tv_Person;
    private LinearLayout llBankCard;
    private TextView tvBankCardID;
    private LinearLayout llJifen;
    private TextView tvJifen;
    private LinearLayout llHuiYuan;
    private TextView tvHuiYuanDJ;
    private TextView tvHY;
    private LinearLayout llNewUser;
    private LinearLayout llWenti;
    private LinearLayout llFade;
    private LinearLayout llAbout;
    private LinearLayout llPhone;
    private String backCardId;
    private float curJiFen;
    private float allJiFen;
    private ImageView imgHYDJIcon;
    private ImageView imgHYIcon;
    private TextView tvHuiYuan;
    private User myUser;
    private View view;
    private boolean isCreate;
    private LinearLayout ll_kefu;
    private TextView tvPhone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        Log.e("lj","getinfo   initData");
        //getInfo();

    }

    @Override
    protected View initView() {
        Log.e("lj", "initView");
        view = View.inflate(mActivity, R.layout.personal_pager, null);
        myUser =  BmobUser.getCurrentUser(User.class);
        if (myUser == null){
            isCreate = true;
            Log.e("lj", "Person  onCreate" + isCreate);
            preView();
            LoginActivity.startLoginActivity(mActivity);
        }else {
            Log.e("lj", "dengluchengg"+ myUser.getObjectId() + "name"+myUser.getUsername());
            preView();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCreate) {
            Log.e("lj", "getInfo    OnResume");
            getInfo();
        }
        isCreate = false;
    }

    private void preView(){

        tv_Person = (TextView) view.findViewById(R.id.person_info);
        tv_Person.setOnClickListener(this);
        llBankCard = (LinearLayout) view.findViewById(R.id.ll_yinhangka);
        llBankCard.setOnClickListener(this);
        tvBankCardID = (TextView) view.findViewById(R.id.tv_yinhangka);
        llJifen = (LinearLayout) view.findViewById(R.id.ll_jifen);
        llJifen.setOnClickListener(this);
        tvJifen = (TextView) view.findViewById(R.id.tv_jifen);
        llHuiYuan = (LinearLayout) view.findViewById(R.id.ll_tequan);
        llHuiYuan.setOnClickListener(this);
        tvHuiYuanDJ = (TextView) view.findViewById(R.id.tv_huiyuandengji);
        tvHY = (TextView) view.findViewById(R.id.tv_zhekou);
        llNewUser = (LinearLayout) view.findViewById(R.id.ll_newuser);
        llNewUser.setOnClickListener(this);
        llWenti = (LinearLayout) view.findViewById(R.id.ll_wenti);
        llWenti.setOnClickListener(this);
        llFade = (LinearLayout) view.findViewById(R.id.ll_fankui);
        llFade.setOnClickListener(this);
        llAbout = (LinearLayout) view.findViewById(R.id.about);
        llAbout.setOnClickListener(this);
        llPhone = (LinearLayout) view.findViewById(R.id.kefu);
        llPhone.setOnClickListener(this);
        tvHuiYuan = (TextView) view.findViewById(R.id.huiyuan);
        imgHYDJIcon = (ImageView) view.findViewById(R.id.hydj_icon);
        imgHYIcon = (ImageView) view.findViewById(R.id.topIcon);
        ll_kefu = (LinearLayout) view.findViewById(R.id.kefu);
        ll_kefu.setOnClickListener(this);
        tvPhone = (TextView) view.findViewById(R.id.phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person_info:
                PersonInfoActivity.startPersonInfoAcrivity(mActivity);
                break;
            case R.id.ll_yinhangka:
                BankAccountActivity.startBankAccountActivity(mActivity);
                break;
            case R.id.ll_jifen:
                VipActivity.startVipActivity(mActivity);
                break;
            case R.id.ll_tequan:
                VipActivity.startVipActivity(mActivity);
                break;
            case R.id.ll_newuser:
                NewUserActivity.startNewUserActivity(mActivity);
                break;
            case R.id.ll_wenti:
                QAActivity.startQAActivity(mActivity);
                break;
            case R.id.ll_fankui:
                break;
            case R.id.about:
                AboutActivity.startAboutActivity(mActivity);
                break;
            case R.id.kefu:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = (String) tvPhone.getText();
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }

    private void getInfo(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {

                final Message msg = Message.obtain();
                BmobQuery<JiFen> jiFenBmobQuery = new BmobQuery<JiFen>();
                if (myUser == null){
                    myUser =  BmobUser.getCurrentUser(User.class);
                    if (myUser == null){
                        Log.e("lj", "user    null");
                        return;
                    }

                }
                jiFenBmobQuery.addWhereEqualTo("userName", myUser.getUsername())
                        .findObjects(new FindListener<JiFen>() {
                            @Override
                            public void done(List<JiFen> list, BmobException e) {
                                if (e == null) {
                                    if (list.size() > 0) {
                                        curJiFen = list.get(0).getCurJifen();
                                        allJiFen = list.get(0).getJiFen();
                                        msg.what = 1;
                                        Log.e("lj","send        1");
                                        mHandler.sendEmptyMessage(msg.what);
                                    }else {
                                        Log.e("lj","积分没有查到");
                                    }
                                }else {
                                    Log.e("lj","chaxunshibai   积分没有查到");
                                }
                            }
                        });

                BmobQuery<Account> accountBmobQuery = new BmobQuery<Account>();
                accountBmobQuery.addWhereEqualTo("userName",myUser.getUsername());
                accountBmobQuery.setLimit(3);
                accountBmobQuery.findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> list, BmobException e) {
                        if (e == null){
                            if (list.size()>0){
                                Account account = list.get(0);
                                backCardId = account.getBankCard();
                                Message msg= Message.obtain();
                                msg.what = 0;
                                Log.e("lj",backCardId);
                                Log.e("lj","send        0");
                                mHandler.sendEmptyMessage(msg.what);
                            }else {
                                Log.e("lj", "查询到account是空的"+list.size());

                            }

                        }else {
                            Message msg= Message.obtain();
                            msg.what = 1;
                            Log.e("lj", "查询account失败"+ "personpager" +e.toString());
                            mHandler.sendEmptyMessage(msg.what);
                        }
                    }
                });
            }
        });
    }

    private void calculateJF(){
        curJiFen = NumberUtils.float2(curJiFen);
        tvJifen.setText(curJiFen+"");
        if(allJiFen>=50 && allJiFen<200){
            tvHuiYuanDJ.setText("银卡会员");
            tvHuiYuan.setText("银卡会员");
            tvHY.setText("9.8");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_silver);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_silver);
        }else if (allJiFen>= 200&&allJiFen<500){
            tvHuiYuanDJ.setText("黄金会员");
            tvHuiYuan.setText("黄金会员");
            tvHY.setText("9.5");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card);
        }else if(allJiFen >=500&&allJiFen<1000){
            tvHuiYuanDJ.setText("铂金会员");
            tvHuiYuan.setText("铂金会员");
            tvHY.setText("9.2");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_normal);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_normal);
        }else if (allJiFen>=1000) {
            tvHuiYuanDJ.setText("钻石会员");
            tvHuiYuan.setText("钻石会员");
            tvHY.setText("8.8");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
        }
    }

    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            Log.e("lj", "PersonHint");

            initView();
            initData();
        }else {
            Log.e("lj", "Person   isVisibleToUser:"+isVisibleToUser + "   " + "isCreate:" +isCreate);
        }
    }*/
}
