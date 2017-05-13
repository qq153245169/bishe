package com.example.liangjie06.zuche.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by liangjie06 on 17/4/22.
 */

public class BaseActivity extends AppCompatActivity {
    public Context mContext;

    private static final String EXITACTION = "action.exit";

    private ExitReceiver exitReceiver = new ExitReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        IntentFilter filter = new IntentFilter();
        filter.addAction(EXITACTION);
        registerReceiver(exitReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
    }

    class ExitReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.finish();
        }

    }
}
