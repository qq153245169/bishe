package com.example.liangjie06.zuche.module.youhui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.liangjie06.zuche.R;


/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class DiscountActivity extends Activity {
    public static void startActivity(Context context){
        Intent intent = new Intent(context,DiscountActivity.class);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
    }
}
