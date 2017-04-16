package com.example.liangjie06.zuche.module.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by liangjie06 on 17/4/16.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{

    private User mUser;
    private EditText mEtPhoneNum;
    private EditText mEtYZM;
    private EditText mEtPwd;
    private EditText mEtPwdAgin;
    private EditText mEtSex;
    private EditText mEtSFZ;
    private EditText mEtJSZ;
    private EditText mEtName;
    private String phoneNum;
    private String yzm;
    private String pwd;
    private String pwdAgin;
    private String sex;
    private String name;
    private String sfz;
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
        mEtPhoneNum = (EditText) findViewById(R.id.et_number);
        mEtYZM = (EditText) findViewById(R.id.et_radom_num);
        mEtPwd = (EditText) findViewById(R.id.et_psd);
        mEtPwdAgin = (EditText) findViewById(R.id.et_psd_agin);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtSex = (EditText) findViewById(R.id.et_sex);
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
        yzm = String.valueOf(mEtYZM.getText());
        pwd = String.valueOf(mEtPwd.getText());
        pwdAgin = String.valueOf(mEtPwdAgin.getText());
        name = String.valueOf(mEtName.getText());
        sex = String.valueOf(mEtSex.getText());
        sfz = String.valueOf(mEtSFZ.getText());
        jsz = String.valueOf(mEtJSZ.getText());

        mUser = new User();
        mUser.setUserID(phoneNum);
        mUser.setPassWord(pwd);
        mUser.setUserName(name);
        mUser.setUserSex(sex);
        mUser.setUserSF(sfz);
        mUser.setUserJS(jsz);
        mUser.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Log.e("lj",phoneNum);
                    Toast.makeText(mContext,"添加数据成功，返回objectId为：" + s,Toast.LENGTH_SHORT );
                }else{
                    Toast.makeText(mContext,"创建数据失败："+ e.getMessage(),Toast.LENGTH_SHORT );
                }
            }
        });
    }
}
