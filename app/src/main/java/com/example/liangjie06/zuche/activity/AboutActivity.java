package com.example.liangjie06.zuche.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.module.fade.FadeActivity;


/**
 * Created by liangjie06 on 17/4/22.
 */

public class AboutActivity extends BaseActivity {

    public static void startAboutActivity(Context context){
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView = (TextView) findViewById(R.id.tv_version);
        String versionName = null;
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(versionName))
            textView.setText(versionName);
    }
}
