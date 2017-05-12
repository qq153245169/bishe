package com.example.liangjie06.zuche.module.bankaccount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.ThreadPool;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by liangjie06 on 17/4/21.
 */

public class AddBankCardActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };

    private final static String TAG = "AddBankCardActivity";
    private EditText etOpenBank;
    private EditText etBankCardId;
    private EditText etPersonName;
    private EditText etPersonId;
    private EditText etPhone;

    private String openBankName;
    private String bankCardId;
    private String personName;
    private String personId;
    private String phone;
    private Context mContext;
    private Account account;

    public static void startAddBankCardActivity(Context context) {

        Intent intent = new Intent(context,AddBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);
        mContext = this;
        initView();
    }

    private void initView() {
        etOpenBank = (EditText) findViewById(R.id.et_kaihuhang);
        etBankCardId = (EditText) findViewById(R.id.et_yhkh);
        etPersonName = (EditText) findViewById(R.id.et_ckrname);
        etPersonId = (EditText) findViewById(R.id.et_sfz);
        etPhone = (EditText) findViewById(R.id.et_phone);

        //添加信息的按钮事件
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        User user = BmobUser.getCurrentUser(User.class);
        account = new Account();
        account.setUserName(user.getUsername());
        openBankName = String.valueOf(etOpenBank.getText());
        bankCardId = String.valueOf(etBankCardId.getText());
        personName = String.valueOf(etPersonName.getText());
        personId = String.valueOf(etPersonId.getText());
        phone = String.valueOf(etPhone.getText());

        if(!TextUtils.isEmpty(openBankName)){
            account.setBank(openBankName);
        }else {
            return;
        }
        if (!TextUtils.isEmpty(bankCardId)){
            if (bankCardId.length()==16 && "招商银行".equals(openBankName) ||bankCardId.length() == 19){
                account.setBankCard(bankCardId);
            }else {
                Toast.makeText(mContext, "请输入正确格式的银行卡号", Toast.LENGTH_SHORT).show();
                return;
            }
        }else {
            return;
        }
        if (!TextUtils.isEmpty(personName)){
            account.setName(personName);
        }else {
            return;
        }
        if (!TextUtils.isEmpty(personId)){
            account.setId(personId);
        }else {
            return;
        }
        if (!TextUtils.isEmpty(phone)){
            account.setPhone(phone);
        }else {
            return;
        }

        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {

                account.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e == null){
                            Log.e(TAG, "数据存储成功");
                            Message msg = Message.obtain();
                            msg.what = 0;
                            mHandler.sendEmptyMessage(msg.what);
                        }else {
                            Toast.makeText(mContext, "银行卡添加失败", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "数据存储失败"+ e.toString());
                        }
                    }
                });



            }
        });

    }
}
