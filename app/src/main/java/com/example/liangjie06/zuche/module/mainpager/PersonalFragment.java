package com.example.liangjie06.zuche.module.mainpager;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.AboutActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.fragment.BaseFragment;
import com.example.liangjie06.zuche.module.bankaccount.BankAccountActivity;
import com.example.liangjie06.zuche.module.fade.NewUserActivity;
import com.example.liangjie06.zuche.module.fade.QAActivity;
import com.example.liangjie06.zuche.module.personinfo.PersonInfoActivity;
import com.example.liangjie06.zuche.module.register.LoginActivity;
import com.example.liangjie06.zuche.module.vip.VipActivity;
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
                    calculateJF();
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
    private User myUser;
    private View view;

    @Override
    protected void initData() {
        getInfo();

    }

    @Override
    protected View initView() {

        myUser =  BmobUser.getCurrentUser(User.class);
        if (myUser == null){
            Intent intent = new Intent(mActivity, LoginActivity.class);
            mActivity.startActivityForResult(intent, 10);

        }else {
            Log.e("lj", "dengluchengg"+ myUser.getObjectId() + "name"+myUser.getUsername());
            preView();
        }

        return view;
    }

    private void preView(){
        view = View.inflate(mActivity, R.layout.personal_pager, null);

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
                break;
        }
    }

    private void getInfo(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
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
                                jiFen = myUser.getJiFen();
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

    private void calculateJF(){
        if(!TextUtils.isEmpty(backCardId)){
            String str = backCardId.substring(4, backCardId.length() - 4);
            String newStr = backCardId.replace(str, " **** ");
            tvBankCardID.setText(newStr);
        }
        tvJifen.setText(jiFen+"");
        if(jiFen>=50 && jiFen<200){
            tvHuiYuanDJ.setText("银卡会员");
            tvHuiYuan.setText("银卡会员");
            tvHY.setText("98");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_silver);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_silver);
        }else if (jiFen>= 200&&jiFen<500){
            tvHuiYuanDJ.setText("黄金会员");
            tvHuiYuan.setText("黄金会员");
            tvHY.setText("95");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card);
        }else if(jiFen >=500&&jiFen<1000){
            tvHuiYuanDJ.setText("铂金会员");
            tvHuiYuan.setText("铂金会员");
            tvHY.setText("92");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_normal);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_normal);
        }else if (jiFen>=1000) {
            tvHuiYuanDJ.setText("钻石会员");
            tvHuiYuan.setText("钻石会员");
            tvHY.setText("88");
            imgHYDJIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
            imgHYIcon.setImageResource(R.drawable.icon_privilege_card_diamond);
        }
    }
}
