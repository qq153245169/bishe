package com.example.liangjie06.zuche.module.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;


/**
 * Created by liangjie06 on 17/4/16.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText mEtId;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private TextView mTvForget;
    private Context mContext;

    public static void startLoginActivity (Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initView();
    }

    public void initView() {
        mEtId = (EditText) findViewById(R.id.et_number);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mTvForget = (TextView) findViewById(R.id.tv_forget);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_register) {
            RegisterActivity.startRegisterActivity(mContext);
        }
        if (v.getId() == R.id.btn_login) {

        }
        if (v.getId() == R.id.tv_forget) {

        }
    }
}
