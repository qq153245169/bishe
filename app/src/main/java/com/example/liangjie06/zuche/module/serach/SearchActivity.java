package com.example.liangjie06.zuche.module.serach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.liangjie06.zuche.R;


/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class SearchActivity extends Activity {
    public static void startActivity(Context context){
        Intent intent = new Intent(context,SearchActivity.class);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
