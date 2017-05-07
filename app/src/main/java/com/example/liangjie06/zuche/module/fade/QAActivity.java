package com.example.liangjie06.zuche.module.fade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class QAActivity extends BaseActivity {

    public static void startQAActivity(Context context){
        Intent intent = new Intent(context, QAActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
    }
}
