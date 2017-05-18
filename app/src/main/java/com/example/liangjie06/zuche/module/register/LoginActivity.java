package com.example.liangjie06.zuche.module.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.admin.AdminActivity;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;



/**
 * Created by liangjie06 on 17/4/16.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
                loding.hide();
                finish();
            }

        }
    };

    private EditText mEtId;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private TextView mTvForget;
    private Context mContext;

    private String name;
    private String pwd;
    private AVLoadingIndicatorView loding;

    public static void startLoginActivity(Context context) {
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
        loding = (AVLoadingIndicatorView) findViewById(R.id.loding);
        loding.hide();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_register) {
            RegisterActivity.startRegisterActivity(mContext);
            finish();
        }
        if (v.getId() == R.id.btn_login) {
            login();
            loding.show();
        }
        if (v.getId() == R.id.tv_forget) {

        }
    }

    private void login() {
        name = String.valueOf(mEtId.getText());
        pwd = String.valueOf(mEtPwd.getText());
        if ("admin".equals(name) && "admin".equals(pwd)){
            AdminActivity.startActivity(mContext);
            finish();
            return;
        }

        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                User.loginByAccount(name, pwd, new LogInListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (user != null) {
                            if (e == null){
                                Log.e("lj", "登陆成功");
                                Message msg = Message.obtain();
                                msg.what = 1;
                                mhandler.sendEmptyMessage(msg.what);
                            }else {
                                Log.e("lj", "登陆shibai" + e.toString());
                                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("lj", "登陆shibai" + e.toString());
                            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            Intent intent = new Intent("action.exit");
            sendBroadcast(intent);

        }
    }
}
