package com.example.liangjie06.zuche.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.bean.Init;
import cn.bmob.v3.listener.FindListener;


/**
 * 通用Activity,用于加载Fragment。
 */
public class PayActivity extends FragmentActivity {

    private TextView id;
    private TextView money;
    private TextView bankCard;
    private Button close;
    private Button pay;
    private LinearLayout llcard;
    private User myUser;
    private float allMoney;
    private ArrayList<Account> bankList;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    break;
                case 1:
                    if (bankList.get(0)!=null){
                        String str = bankList.get(0).getBankCard().substring(bankList.get(0).getBankCard().length()-4,bankList.get(0).getBankCard().length());
                        bankCard.setText(bankList.get(0).getBank()+"("+str+")");
                    }else {
                        bankCard.setText("请添加银行卡");
                    }
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setTheme(R.style.MyTheme_BackDialog);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);*/
        setContentView(R.layout.fragment_pay);
        initView();
        allMoney = getIntent().getFloatExtra("money", 0);
        myUser = BmobUser.getCurrentUser(User.class);
        getInfo();
        initData();
    }

    private void initView(){
        id = (TextView) findViewById(R.id.tv_account);
        money = (TextView) findViewById(R.id.tv_money);
        bankCard = (TextView) findViewById(R.id.bank_card_id);
        close = (Button) findViewById(R.id.btn_close);
        pay = (Button) findViewById(R.id.btn_pay);
        llcard = (LinearLayout) findViewById(R.id.bankcard);
    }

    private void initData(){
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.setResult(0);
                PayActivity.this.finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.setResult(1);
                PayActivity.this.finish();
            }
        });
        String str = myUser.getUsername();
        String temp = str.substring(3,str.length()-4 );
        String newStr = str.replace(temp, "****");
        id.setText(newStr);
        llcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),SelectBankCardActivity.class);
                intent.putExtra("card", bankList);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivityForResult(intent,11);

            }
        });
    }

    private void getInfo(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Account> accountBmobQuery = new BmobQuery<Account>();
                accountBmobQuery.addWhereEqualTo("userName",myUser.getUsername()).findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> list, BmobException e) {
                        if (e == null){
                            bankList = (ArrayList<Account>) list;

                            Message msg= Message.obtain();
                            msg.what = 1;
                            Log.e("lj", "银行卡列表查询成功"+ list.size() );
                            mHandler.sendEmptyMessage(msg.what);
                        }else {
                            Message msg= Message.obtain();
                            msg.what = 0;
                            Log.e("lj", "银行卡列表查询失败"+e.toString());
                            mHandler.sendEmptyMessage(msg.what);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            Log.e("lj", "未选择银行卡，直接返回了");
        }else {
            String str = bankList.get(resultCode).getBankCard().substring(bankList.get(resultCode).getBankCard().length()-4,bankList.get(resultCode).getBankCard().length());
            bankCard.setText(bankList.get(resultCode).getBank()+"("+str+")");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PayActivity.this.setResult(0);
        PayActivity.this.finish();
    }
}
