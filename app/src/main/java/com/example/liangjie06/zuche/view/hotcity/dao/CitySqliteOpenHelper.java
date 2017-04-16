package com.example.liangjie06.zuche.view.hotcity.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liangjie06 on 17/3/13.
 */

public class CitySqliteOpenHelper extends SQLiteOpenHelper {
    public static final String DB_ALL_CITY_NAME = "city";
    public static final int VERSSION = 1;

    public CitySqliteOpenHelper(Context context) {
        super(context, DB_ALL_CITY_NAME, null, VERSSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS recentcity (" +
                "id integer primary key autoincrement," +
                " name varchar(40)," +
                " date INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
