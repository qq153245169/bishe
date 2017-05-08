package com.example.liangjie06.zuche.module.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import static android.R.attr.paddingTop;


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

    private String name;
    private String pwd;

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
        mBtnLogin.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mTvForget = (TextView) findViewById(R.id.tv_forget);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_register) {
            RegisterActivity.startRegisterActivity(mContext);
            finish();
        }
        if (v.getId() == R.id.btn_login) {
            login();
            finish();
        }
        if (v.getId() == R.id.tv_forget) {

        }
    }

    private void login(){
        name = String.valueOf(mEtId.getText());
        pwd = String.valueOf(mEtPwd.getText());
        User.loginByAccount(name, pwd, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user!=null){
                    Log.e("lj","登陆成功");
                    loginSeccess();
                }else {
                    Log.e("lj","登陆shibai"+e.toString());
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginSeccess(){
        LoginActivity.this.setResult(2);
        LoginActivity.this.finish();
    }
}
