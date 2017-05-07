package com.example.liangjie06.zuche.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class BaseActivity extends Activity {
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
