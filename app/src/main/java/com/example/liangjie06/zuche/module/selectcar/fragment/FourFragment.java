package com.example.liangjie06.zuche.module.selectcar.fragment;


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
import com.example.liangjie06.zuche.bean.Car;
import com.example.liangjie06.zuche.module.selectcar.JiaoYiActivity;
import com.example.liangjie06.zuche.module.selectcar.SelectActivity;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class FourFragment extends Fragment {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    mAdapter.notifyDataSetChanged();
                    Log.e("lj", "zhixinglema ");
                    break;

            }
        }
    };

    private ArrayList<Car> carList;
    private ListView listView;
    private CarAdapter mAdapter;
    private SelectActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (SelectActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.layout_four, container, false);
        listView = (ListView) v.findViewById(R.id.list_car);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInfo();
        mAdapter = new FourFragment.CarAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JiaoYiActivity.startActivity(getActivity(), mActivity.getPart, mActivity.retPart,
                        mActivity.getTime, mActivity.retTime, mActivity.dayCount, carList.get(position),"ff");
            }
        });

    }

    private void getInfo() {
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Car> accountBmobQuery = new BmobQuery<Car>();
                accountBmobQuery.addWhereEqualTo("leiXing", "3")
                        .findObjects(new FindListener<Car>() {
                            @Override
                            public void done(List<Car> list, BmobException e) {
                                if (e == null) {
                                    carList = (ArrayList<Car>) list;

                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    Log.e("lj", "车辆列表查询成功" + list.size() + "   " + carList.toString());
                                    mHandler.sendEmptyMessage(msg.what);
                                } else {
                                    Message msg = Message.obtain();
                                    msg.what = 0;
                                    Log.e("lj", "车辆列表查询失败" + e.toString());
                                    mHandler.sendEmptyMessage(msg.what);
                                }
                            }
                        });
            }
        });
    }

    class CarAdapter extends BaseAdapter {
        @Override
        public int getItemViewType(int position) {
            if ("0".equals(carList.get(position).getType())) {
                return 0;
            } else if ("1".equals(carList.get(position).getType())) {
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

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
                if (getItemViewType(position) == 1) {
                    convertView = View.inflate(getActivity(), R.layout.car_item2, null);
                    viewHoder.tvIce = (TextView) convertView.findViewById(R.id.ice);
                    if (!carList.get(position).getIce()) {
                        viewHoder.tvIce.setText("超低价秒杀");
                    }
                } else {
                    convertView = View.inflate(getActivity(), R.layout.car_item1, null);
                }
                viewHoder.ivCar = (ImageView) convertView.findViewById(R.id.icon_car);
                viewHoder.tvCarName = (TextView) convertView.findViewById(R.id.car_name);
                viewHoder.tvCarDesc = (TextView) convertView.findViewById(R.id.car_decs);
                viewHoder.ivXin = (ImageView) convertView.findViewById(R.id.car_xin);
                viewHoder.ivRe = (ImageView) convertView.findViewById(R.id.car_re);
                viewHoder.ivte = (ImageView) convertView.findViewById(R.id.car_te);
                viewHoder.tvPrice = (TextView) convertView.findViewById(R.id.car_price);
                convertView.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) convertView.getTag();
            }
            Glide.with(getActivity()).load(carList.get(position).getIcon().getUrl()).placeholder(R.drawable.car_image).into(viewHoder.ivCar);
            viewHoder.tvCarName.setText(carList.get(position).getCarName());
            viewHoder.tvCarDesc.setText(carList.get(position).getXiangShu() + "｜" +
                    carList.get(position).getPaiLiang() + "｜" + carList.get(position).getChengZuo());
            if (carList.get(position).getXin())
                viewHoder.ivXin.setVisibility(View.VISIBLE);
            if (carList.get(position).getHot())
                viewHoder.ivRe.setVisibility(View.VISIBLE);
            if (carList.get(position).getTe())
                viewHoder.ivte.setVisibility(View.VISIBLE);
            viewHoder.tvPrice.setText(carList.get(position).getPrice() + "");

            return convertView;
        }
    }

    class ViewHoder {
        public ImageView ivCar;
        public TextView tvCarName;
        public TextView tvCarDesc;
        public ImageView ivXin;
        public ImageView ivRe;
        public ImageView ivte;
        public TextView tvPrice;
        public TextView tvIce;
    }

}
