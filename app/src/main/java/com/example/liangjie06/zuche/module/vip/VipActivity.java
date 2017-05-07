package com.example.liangjie06.zuche.module.vip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.liangjie06.zuche.R;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class VipActivity extends Activity {

    private Context mContext;
    public static void startVipActivity(Context context){
        Intent intent = new Intent(context, VipActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        mContext = this;
    }
}
