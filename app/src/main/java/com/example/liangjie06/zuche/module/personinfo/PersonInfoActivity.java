package com.example.liangjie06.zuche.module.personinfo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.register.LoginActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by liangjie06 on 17/4/21.
 */

public class PersonInfoActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout llExit;
    private LinearLayout llChangePwd;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private Context mContext;
    private User myUser;

    public static void startPersonInfoAcrivity(Context context){
        Intent intent = new Intent(context, PersonInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        myUser = BmobUser.getCurrentUser(User.class);
        mContext = this;
        initView();
        initData();
    }

    private void initView(){
        llExit = (LinearLayout) findViewById(R.id.exit);
        llExit.setOnClickListener(this);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvPhone = (TextView) findViewById(R.id.tv_phone);

        llChangePwd = (LinearLayout) findViewById(R.id.pwd);
        llChangePwd.setOnClickListener(this);
    }
    private void initData() {
        tvName.setText(myUser.getNameame());
        tvPhone.setText(myUser.getUsername());
        if(!myUser.getUserSex()){
            tvSex.setText("男");
        }else {
            tvSex.setText("女");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit:
                BmobUser.logOut();
                LoginActivity.startLoginActivity(mContext);
                finish();
                break;
            case R.id.pwd:
                ChangePwdActivity.startChangePwdActivity(mContext);
                break;
        }
    }
}
