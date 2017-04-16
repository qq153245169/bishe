package com.example.liangjie06.zuche.utils;

import android.content.Context;

/**
 * Created by Jack-Liang on 2016/8/25.
 */
public class CacheUtils {

    /**
     * url为key, json为vaule,保存在本地
     *
     * @param url
     * @param json
     */
    public static void setCache(String url, String json, Context ctx) {

        PrefUtils.setString(ctx, url, json);
    }

    /**
     * 获取缓存
     *
     * @param url
     * @param ctx
     * @return
     */
    public static String getCache(String url, Context ctx) {

        return PrefUtils.getString(ctx, url, null);
    }
}
