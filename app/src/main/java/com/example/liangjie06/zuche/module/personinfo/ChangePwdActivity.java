package com.example.liangjie06.zuche.module.personinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class ChangePwdActivity extends Activity {

    private Context mContext;

    private EditText etPwd;
    private EditText etPwd2;
    private EditText etPwd3;

    private String pwd1;
    private String pwd2;
    private String pwd3;

    public static void startChangePwdActivity(Context context) {
        Intent intent = new Intent(context, ChangePwdActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        mContext = this;
        initView();
    }

    private void initView(){
        etPwd = (EditText) findViewById(R.id.et_pwd);
        etPwd2 = (EditText) findViewById(R.id.et_pwd2);
        etPwd3 = (EditText) findViewById(R.id.et_pwd3);
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                if (!TextUtils.isEmpty(pwd2)&&pwd2.equals(pwd3)){
                    BmobUser.updateCurrentUserPassword(pwd1, pwd2, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(mContext, "密码修改成功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                finish();
            }
        });
    }

    private void initData(){
        pwd1 = String.valueOf(etPwd.getText());
        pwd2 = String.valueOf(etPwd2.getText());
        pwd3 = String.valueOf(etPwd3.getText());
    }
}
