package com.example.liangjie06.zuche.module.vip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.JiFen;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.NumberUtils;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liangjie06 on 17/4/22.
 */

public class VipActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setData();
        }
    };

    private Context mContext;
    private User myUser;
    private float curJifen;
    private float allJifen;
    private TextView tv_Leiji;
    private TextView tv_Keyong;
    private ImageView iv_Icon;

    public static void startVipActivity(Context context){
        Intent intent = new Intent(context, VipActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myUser = BmobUser.getCurrentUser(User.class);
        getJifen();
        setContentView(R.layout.activity_vip);
        mContext = this;
        initView();
    }

    private void initView() {
        tv_Leiji = (TextView) findViewById(R.id.leiji);
        tv_Keyong = (TextView) findViewById(R.id.keyong);
        iv_Icon = (ImageView) findViewById(R.id.vip_icon);
    }

    private void setData(){
        allJifen = NumberUtils.float2(allJifen);
        curJifen = NumberUtils.float2(curJifen);
        tv_Leiji.setText(""+ allJifen);
        tv_Keyong.setText(""+curJifen);
        if(allJifen>=50 && allJifen<200){
            iv_Icon.setImageResource(R.drawable.silver_vip);
        }else if (allJifen>= 200&&allJifen<500){
            iv_Icon.setImageResource(R.drawable.gold_vip);
        }else if(allJifen >=500&&allJifen<1000){
            iv_Icon.setImageResource(R.drawable.white_gold_vip);
        }else if (allJifen>=1000) {
            iv_Icon.setImageResource(R.drawable.diamonds_vip);
        }
    }

    private void getJifen(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                BmobQuery<JiFen> jiFenBmobQuery = new BmobQuery<JiFen>();
                jiFenBmobQuery.addWhereEqualTo("userName", myUser.getUsername())
                        .findObjects(new FindListener<JiFen>() {
                            @Override
                            public void done(List<JiFen> list, BmobException e) {
                                if(e == null){
                                    curJifen = list.get(0).getCurJifen();
                                    allJifen = list.get(0).getJiFen();
                                    Message msg = Message.obtain();
                                    msg.what = 0;
                                    mHandler.sendEmptyMessage(msg.what);
                                }else {
                                    Log.e("lj", "获取积分失败"+ e.toString());
                                }
                            }
                        });
            }
        });

    }
}
