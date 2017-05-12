package com.example.liangjie06.zuche.module.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.Order;
import com.example.liangjie06.zuche.module.mainpager.order.AllFragment;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.example.liangjie06.zuche.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.baidu.location.h.g.g;
import static com.baidu.location.h.g.t;


/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class AdminActivity extends BaseActivity {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    ll_NoData.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "当前用户没有未完成订单", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    ll_NoData.setVisibility(View.GONE);
                    myAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    ll_NoData.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    public static void startActivity(Context context){
        Intent intent = new Intent(context,AdminActivity.class);
        context.startActivity(intent);

    }

    private EditText et_Sousuo;
    private ListView listView;
    private Button btn_Sousuo;
    private String userName;
    private CarAdapter myAdapter;
    private ImageView btn_Cancle;
    private LinearLayout ll_NoData;
    private List<Order> carList = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
    }
    private void initView(){
        ll_NoData = (LinearLayout) findViewById(R.id.ll_no_data);
        et_Sousuo = (EditText) findViewById(R.id.et_sousuo);
        listView = (ListView) findViewById(R.id.list);
        btn_Sousuo = (Button) findViewById(R.id.btn_sousuo);
        btn_Sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrder();
            }
        });
        btn_Cancle = (ImageView) findViewById(R.id.cancel);
        btn_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carList = null;
                myAdapter.notifyDataSetChanged();
                ll_NoData.setVisibility(View.VISIBLE);
            }
        });

        myAdapter = new CarAdapter();
        listView.setAdapter(myAdapter);
    }

    private void getOrder() {
        userName = String.valueOf(et_Sousuo.getText());
        if (!TextUtils.isEmpty(userName)){
            ThreadPool.runOnPool(new Runnable() {
                @Override
                public void run() {
                    BmobQuery<Order> orderBmobQuery = new BmobQuery<Order>();
                    orderBmobQuery.addWhereEqualTo("userName", userName);
                    orderBmobQuery.addWhereEqualTo("isComplete", false);
                    final Message msg = Message.obtain();
                    orderBmobQuery.findObjects(new FindListener<Order>() {
                        @Override
                        public void done(List<Order> list, BmobException e) {
                            if (e == null){
                                Log.e("lj","查询到订单admin");
                                if(list.size() == 0){
                                    msg.what = 0;
                                    mHandler.sendEmptyMessage(msg.what);
                                }else {
                                    carList = list;
                                    msg.what = 1;
                                    mHandler.sendEmptyMessage(msg.what);
                                }
                            }else {
                                Log.e("lj","没有订单"+e.toString());
                                msg.what = 2;
                                mHandler.sendEmptyMessage(msg.what);
                            }

                        }
                    });
                }
            });
        }else {
            Toast.makeText(mContext, "请输入客户用户名", Toast.LENGTH_SHORT).show();
        }
    }

    class CarAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return carList == null ? 0 : carList.size();
        }

        @Override
        public Object getItem(int position) {
            return carList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHoder viewHoder;
            if (convertView == null) {
                viewHoder = new ViewHoder();

                convertView = View.inflate(mContext, R.layout.admin_order_item, null);

                viewHoder.ivCar = (ImageView) convertView.findViewById(R.id.icon_car);
                viewHoder.tvPart = (TextView) convertView.findViewById(R.id.tv_part);
                viewHoder.tvState = (TextView) convertView.findViewById(R.id.tv_state);
                viewHoder.tvCarName = (TextView) convertView.findViewById(R.id.tv_title);
                viewHoder.tvCarDesc = (TextView) convertView.findViewById(R.id.tv_desc);
                viewHoder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
                viewHoder.tvGetTime = (TextView) convertView.findViewById(R.id.time_get);
                viewHoder.tvReturnTime = (TextView) convertView.findViewById(R.id.time_return);
                viewHoder.tvDay = (TextView) convertView.findViewById(R.id.day);
                viewHoder.btnWancheng = (Button) convertView.findViewById(R.id.btn_wancheng);
                convertView.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) convertView.getTag();
            }
            Glide.with(mContext).load(carList.get(position).getIcon()).placeholder(R.drawable.car_image).into(viewHoder.ivCar);
            viewHoder.tvCarName.setText(carList.get(position).getCarName());
            viewHoder.tvCarDesc.setText(carList.get(position).getXiangShu() + "｜" +
                    carList.get(position).getPaiLiang() + "｜" + carList.get(position).getChengZuo());
            viewHoder.tvPrice.setText(carList.get(position).getAllMoney()+"");
            viewHoder.tvDay.setText(carList.get(position).getDay()+"");
            viewHoder.tvPart.setText(carList.get(position).getOrderId()+"");
            viewHoder.tvGetTime.setText(TimeUtils.getDateToYMD(carList.get(position).getTimeFrom()));
            viewHoder.tvReturnTime.setText(TimeUtils.getDateToYMD(carList.get(position).getTimeTo()));
            if (!carList.get(position).getPay()) {
                if (TimeUtils.getDay(carList.get(position).getTimeFrom()) >= TimeUtils.getDay(System.currentTimeMillis())){
                    viewHoder.tvState.setText("待支付");
                }else {
                    viewHoder.tvState.setText("已过期");
                }

            }else {
                if (TimeUtils.getDay(carList.get(position).getTimeTo()) < TimeUtils.getDay(System.currentTimeMillis())&& !carList.get(position).getComplete()){
                    viewHoder.tvState.setText("已延期");
                }else if (carList.get(position).getComplete()){
                    viewHoder.tvState.setText("已完成");
                }else if(TimeUtils.getDay(carList.get(position).getTimeTo()) >= TimeUtils.getDay(System.currentTimeMillis())&& !carList.get(position).getComplete()){
                    viewHoder.tvState.setText("使用中");
                }
            }
            viewHoder.btnWancheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateOrder(carList.get(position).getObjectId());
                    carList.remove(position);
                    myAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    class ViewHoder {
        public ImageView ivCar;
        private TextView tvPart;
        private TextView tvState;
        public TextView tvCarName;
        public TextView tvCarDesc;
        public TextView tvPrice;
        public TextView tvGetTime;
        private TextView tvReturnTime;
        private TextView tvDay;
        private Button btnWancheng;
    }

    public void updateOrder(String objectId){
        Order order = new Order();
        order.setValue("isComplete", true);
        order.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Toast.makeText(mContext, "订单状态修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "订单状态修改失败", Toast.LENGTH_SHORT).show();
                    Log.e("lj", "订单更新失败"+e.toString());

                }
            }
        });

    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            Intent intent = new Intent("action.exit");
            sendBroadcast(intent);

        }
    }
}
