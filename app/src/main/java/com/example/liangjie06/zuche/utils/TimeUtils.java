package com.example.liangjie06.zuche.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liangjie06 on 17/5/8.
 */

public class TimeUtils {
    private static SimpleDateFormat sf = new SimpleDateFormat("MM-dd", Locale.ENGLISH);
    private static SimpleDateFormat sf2 = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);


    public static String getDateToMD(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    public static String getDateToYMD(long time) {
        Date d = new Date(time);
        return sf2.format(d);
    }

    public static int getDay(long time) {
        Date d = new Date(time);
        return d.getDate();
    }
}
