package com.example.liangjie06.zuche.module.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.PrefUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



/**
 * Created by liangjie06 on 17/4/16.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{

    private User mUser;
    private EditText mEtPhoneNum;
    private EditText mEtPwd;
    private EditText mEtPwdAgin;
    private RadioGroup rgGroup;
    private EditText mEtSFZ;
    private EditText mEtJSZ;
    private EditText mEtName;
    private String phoneNum;
    private String pwd;
    private String pwdAgin;
    private boolean isMan ;
    private boolean isSetSex;
    private String name;
    private String id;
    private String jsz;
    private Context mContext;
    private Button mBtnRegister;

    public static void startRegisterActivity (Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        mUser = new User();
        mEtPhoneNum = (EditText) findViewById(R.id.et_number);
        mEtPwd = (EditText) findViewById(R.id.et_psd);
        mEtPwdAgin = (EditText) findViewById(R.id.et_psd_agin);
        mEtName = (EditText) findViewById(R.id.et_name);
        rgGroup = (RadioGroup) findViewById(R.id.rg_sex);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_man){
                    isMan = true;
                    isSetSex = true;
                }else {
                    isMan = false;
                    isSetSex = true;
                }
            }
        });
        mEtSFZ = (EditText) findViewById(R.id.et_sfz);
        mEtJSZ = (EditText) findViewById(R.id.et_jsz);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }
    private void initData(){


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_register){
            Register();
        }
    }

    private void Register(){
        phoneNum = String.valueOf(mEtPhoneNum.getText());
        Log.e("lj",phoneNum);
        pwd = String.valueOf(mEtPwd.getText());
        pwdAgin = String.valueOf(mEtPwdAgin.getText());
        name = String.valueOf(mEtName.getText());
        id = String.valueOf(mEtSFZ.getText());
        jsz = String.valueOf(mEtJSZ.getText());

        if (!TextUtils.isEmpty(phoneNum)){
            if(phoneNum.length() != 11){
                Toast.makeText(getApplicationContext(), "请输入正确手机号码", Toast.LENGTH_SHORT);
                return;
            }else {
                mUser.setUsername(phoneNum);
            }
        }else {
            return;
        }
        if (!TextUtils.isEmpty(pwd)&&pwd.length()>=6 ){
            if (pwd.equals(pwdAgin)) {
                mUser.setPassword(pwd);
            }else {
                Toast.makeText(getApplicationContext(), "请确认密码一致", Toast.LENGTH_SHORT);
                return;
            }
        } else {
            Toast.makeText(getApplicationContext(), "请输入不少于6位密码", Toast.LENGTH_SHORT);
            return;
        }
        if (!TextUtils.isEmpty(name)){
                mUser.setName(name);
        }else {
            return;
        }
        if (!TextUtils.isEmpty(id)){
            mUser.setUserID(id);
        }else {
            return;
        }

        if (!TextUtils.isEmpty(jsz)){
            mUser.setUserJS(jsz);
        }else {
            return;
        }
        if (!isSetSex){
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT);
            return;
        }
        Log.e("lj","数据填充"+phoneNum +"   "+pwd +"   "+ name +"   "+isMan +"   "+id +"   "+jsz);

        mUser.signUp(new SaveListener<User>() {
            @Override
            public void done(User o, BmobException e) {
                if (e ==null){
                    PrefUtils.setString(mContext, "objectId", o.getObjectId());
                }else {
                    Log.e("lj","chengg"+e.toString());
                }

            }
        });

    }
}
