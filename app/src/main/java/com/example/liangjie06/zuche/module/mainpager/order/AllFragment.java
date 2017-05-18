package com.example.liangjie06.zuche.module.mainpager.order;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.Order;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.module.selectcar.SelectActivity;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.example.liangjie06.zuche.utils.TimeUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class AllFragment extends Fragment {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loding.hide();
                    break;
                case 1:
                    mAdapter.notifyDataSetChanged();
                    loding.hide();
                    Log.e("lj", "zhixinglema ");
                    break;

            }
        }
    };

    private ArrayList<Order> carList;
    private ListView listView;
    private CarAdapter mAdapter;
    private SelectActivity mActivity;
    private AVLoadingIndicatorView loding;
    private User myUser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //mActivity = (SelectActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.layout_first, container, false);
        loding = (AVLoadingIndicatorView) v.findViewById(R.id.loding);

        listView = (ListView) v.findViewById(R.id.list_car);
        myUser = BmobUser.getCurrentUser(User.class);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInfo();
        mAdapter = new CarAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*JiaoYiActivity.startActivity(getActivity(), mActivity.getPart, mActivity.retPart,
                        mActivity.getTime, mActivity.retTime, mActivity.dayCount, carList.get(position));*/
            }
        });

    }

    private void getInfo() {
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Order> accountBmobQuery = new BmobQuery<Order>();
                accountBmobQuery.addWhereEqualTo("userName",myUser.getUsername())
                        .findObjects(new FindListener<Order>() {
                    @Override
                    public void done(List<Order> list, BmobException e) {
                        if (e == null) {
                            carList = (ArrayList<Order>) list;

                            Message msg = Message.obtain();
                            msg.what = 1;
                            Log.e("lj", "订单列表查询成功" + list.size() + "   " + carList.toString());
                            mHandler.sendEmptyMessage(msg.what);
                        } else {
                            Message msg = Message.obtain();
                            msg.what = 0;
                            Log.e("lj", "订单列表查询失败" + e.toString());
                            mHandler.sendEmptyMessage(msg.what);
                        }
                    }
                });
            }
        });
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoder viewHoder;
            if (convertView == null) {
                viewHoder = new ViewHoder();

                convertView = View.inflate(getActivity(), R.layout.order_item, null);

                viewHoder.ivCar = (ImageView) convertView.findViewById(R.id.icon_car);
                viewHoder.tvPart = (TextView) convertView.findViewById(R.id.tv_part);
                viewHoder.tvState = (TextView) convertView.findViewById(R.id.tv_state);
                viewHoder.tvCarName = (TextView) convertView.findViewById(R.id.tv_title);
                viewHoder.tvCarDesc = (TextView) convertView.findViewById(R.id.tv_desc);
                viewHoder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
                viewHoder.tvGetTime = (TextView) convertView.findViewById(R.id.time_get);
                viewHoder.tvReturnTime = (TextView) convertView.findViewById(R.id.time_return);
                viewHoder.tvDay = (TextView) convertView.findViewById(R.id.day);
                convertView.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) convertView.getTag();
            }
            Glide.with(getActivity()).load(carList.get(position).getIcon()).placeholder(R.drawable.car_image).into(viewHoder.ivCar);
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
    }
}
