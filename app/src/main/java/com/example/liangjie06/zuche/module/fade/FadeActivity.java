package com.example.liangjie06.zuche.module.fade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.liangjie06.zuche.base.BaseActivity;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class FadeActivity extends BaseActivity {

    private Context mContext;

    public static void startChongZhi(Context context){
        Intent intent = new Intent(context, FadeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
