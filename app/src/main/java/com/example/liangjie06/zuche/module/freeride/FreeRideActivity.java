package com.example.liangjie06.zuche.module.freeride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class FreeRideActivity extends Activity {
    public static void startActivity(Context context){
        Intent intent = new Intent(context,FreeRideActivity.class);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
