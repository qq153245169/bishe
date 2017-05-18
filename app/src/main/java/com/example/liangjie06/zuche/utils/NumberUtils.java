package com.example.liangjie06.zuche.utils;

import java.math.BigDecimal;

/**
 * Created by liangjie06 on 17/5/18.
 */

public class NumberUtils {

    public static float float2(float f){
        BigDecimal b  =   new BigDecimal(f);
        float   f1   =  b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

}
