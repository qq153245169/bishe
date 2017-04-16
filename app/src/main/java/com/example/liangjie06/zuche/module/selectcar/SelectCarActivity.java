package com.example.liangjie06.zuche.module.selectcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import com.example.liangjie06.zuche.R;

import java.util.ArrayList;



/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class SelectCarActivity extends Activity {
    private ListView mCarList;
    public static void startActivity(Context context){
        Intent intent = new Intent(context,SelectCarActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        mCarList = (ListView) findViewById(R.id.lv_car);
        CarAdapter mAdapter = new CarAdapter(this,new ArrayList());
        mCarList.setAdapter(mAdapter);

    }
}
