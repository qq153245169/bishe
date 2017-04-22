package com.example.liangjie06.zuche.base.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BasePager;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.bankaccount.BankAccountActivity;
import com.example.liangjie06.zuche.module.personinfo.PersonInfoActivity;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.example.liangjie06.zuche.R.id.ll;


/**
 * Created by Jack-Liang on 2016/8/23.
 */
public class PersonalPager extends BasePager implements View.OnClickListener{

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if(!TextUtils.isEmpty(backCardId)){
                        String str = backCardId.substring(4, backCardId.length() - 4);
                        String newStr = backCardId.replace(str, "****");
                        tvBankCardID.setText(newStr);
                    }
                    tvJifen.setText(jiFen+"");
                    tvYuE.setText(yuE+"");
                    if(jiFen>=50 && jiFen<200){
                        tvHuiYuanDJ.setText("银卡会员");
                        tvHuiYuan.setText("银卡会员");
                        imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_silver);
                        imgHYIcon.setImageResource(R.drawable.icon_privilege_card_silver);
                    }else if (jiFen>= 200&&jiFen<500){
                        tvHuiYuanDJ.setText("黄金会员");
                        tvHuiYuan.setText("黄金会员");
                        imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card);
                        imgHYIcon.setImageResource(R.drawable.icon_privilege_card);
                    }else if(jiFen >=500&&jiFen<1000){
                        tvHuiYuanDJ.setText("铂金会员");
                        tvHuiYuan.setText("铂金会员");
                        imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_normal);
                        imgHYIcon.setImageResource(R.drawable.icon_privilege_card_normal);
                    }else if (jiFen>=1000) {
                        tvHuiYuanDJ.setText("钻石会员");
                        tvHuiYuan.setText("钻石会员");
                        imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
                        imgHYIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
                    }
                    break;
                case 1:
                    break;
            }
        }
    };

    private TextView tv_Person;
    private LinearLayout llBankCard;
    private TextView tvBankCardID;
    private LinearLayout llJifen;
    private TextView tvJifen;
    private LinearLayout llYuE;
    private TextView tvYuE;
    private LinearLayout llHuiYuan;
    private TextView tvHuiYuanDJ;
    private TextView tvHY;
    private LinearLayout llNewUser;
    private LinearLayout llWenti;
    private LinearLayout llFade;
    private LinearLayout llAbout;
    private LinearLayout llPhone;
    private String backCardId;
    private int jiFen;
    private float yuE;
    private ImageView imgHYDJIcon;
    private ImageView imgHYIcon;
    private TextView tvHuiYuan;

    //private Account account;
    public PersonalPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        View view = View.inflate(mActivity, R.layout.personal_pager, null);
        flContent.addView(view);

        tv_Person = (TextView) view.findViewById(R.id.person_info);
        tv_Person.setOnClickListener(this);
        llBankCard = (LinearLayout) view.findViewById(R.id.ll_yinhangka);
        llBankCard.setOnClickListener(this);
        tvBankCardID = (TextView) view.findViewById(R.id.tv_yinhangka);
        llJifen = (LinearLayout) view.findViewById(R.id.ll_jifen);
        llJifen.setOnClickListener(this);
        tvJifen = (TextView) view.findViewById(R.id.tv_jifen);
        llYuE = (LinearLayout) view.findViewById(R.id.ll_yue);
        llYuE.setOnClickListener(this);
        tvYuE = (TextView) view.findViewById(R.id.tv_yue);
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
        getInfo();
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

                break;
            case R.id.ll_yue:
                break;
            case R.id.ll_tequan:
                break;
            case R.id.ll_newuser:
                break;
            case R.id.ll_wenti:
                break;
            case R.id.ll_fankui:
                break;
            case R.id.about:
                break;
            case R.id.kefu:
                break;
        }
    }

    private void getInfo(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                User user = BmobUser.getCurrentUser(User.class);
                BmobQuery<Account> accountBmobQuery = new BmobQuery<Account>();
                accountBmobQuery.addWhereEqualTo("userName",user.getUsername());
                accountBmobQuery.setLimit(3);
                accountBmobQuery.findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> list, BmobException e) {
                        if (e == null){
                            if (list.size()>0){
                                Account account = list.get(0);
                                backCardId = account.getBankCard();
                                jiFen = account.getJifen();
                                yuE = account.getYue();
                                Message msg= Message.obtain();
                                msg.what = 0;
                                Log.e("lj",backCardId +"   "+
                                        jiFen + "   "+yuE);
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
}
