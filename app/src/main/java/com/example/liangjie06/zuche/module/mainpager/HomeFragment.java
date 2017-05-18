package com.example.liangjie06.zuche.module.mainpager;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.bean.Car;
import com.example.liangjie06.zuche.base.BaseFragment;
import com.example.liangjie06.zuche.module.gostore.GoStoreActivity;
import com.example.liangjie06.zuche.utils.ThreadPool;
import com.example.liangjie06.zuche.view.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liangjie06 on 17/5/7.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    setTuijian();
                    break;

            }
        }
    };

    private ArrayList<Car> carList;

    private BannerLayout mVPager;
    private List<BannerLayout.BannerEntity> urls = new ArrayList<>();

    private TextView nameL;
    private TextView nameR;
    private TextView priceL;
    private TextView priceR;
    private ImageView iconL;
    private ImageView iconR;
    private ImageView iconD;
    private TextView nameD;
    private TextView descD;
    private TextView priceD;

    @Override
    protected void initData() {
        update();

        mVPager.setOnPagerClickListener(new BannerLayout.OnPagerClickListener() {
            @Override
            public void onClick(BannerLayout.BannerEntity entity) {

                //TODO
                Log.e("lj", "hahahahhaahhaha");
            }
        });
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.page_main, null);

        nameL = (TextView) view.findViewById(R.id.car_name_l);
        nameR = (TextView) view.findViewById(R.id.car_name_r);
        priceL = (TextView) view.findViewById(R.id.car_price_l);
        priceR = (TextView) view.findViewById(R.id.car_price_r);
        iconL = (ImageView) view.findViewById(R.id.icon_left);
        iconR = (ImageView) view.findViewById(R.id.icon_right);

        view.findViewById(R.id.layout2).setOnClickListener(this);
        view.findViewById(R.id.tuijian1).setOnClickListener(this);
        view.findViewById(R.id.tuijian2).setOnClickListener(this);
        mVPager = (BannerLayout) view.findViewById(R.id.vp_lunbo);

        getTuiJian();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout2:
                GoStoreActivity.startActivity(mActivity);
                break;
            case R.id.tuijian1:
                showDialog(0);
                break;
            case R.id.tuijian2:
                showDialog(1);
                break;
            default:
                break;
        }
    }

    public void update() {
        urls.clear();
        urls.add(new HomeFragment.Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
        urls.add(new HomeFragment.Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
        urls.add(new HomeFragment.Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
        urls.add(new HomeFragment.Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        urls.add(new HomeFragment.Entity("http://img3.imgtn.bdimg.com/it/u=2968209827,470106340&fm=21&gp=0.jpg"));
        urls.add(new HomeFragment.Entity("http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg"));


        long t = System.currentTimeMillis();
        mVPager.setDatas(urls);
        Log.w("---", System.currentTimeMillis() - t + "");
    }

    private class Entity implements BannerLayout.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }

    private void setTuijian() {
        nameL.setText(carList.get(0).getCarName());
        priceL.setText(carList.get(0).getPrice()+"");
        nameR.setText(carList.get(1).getCarName());
        priceR.setText(carList.get(1).getPrice()+"");
        Glide.with(getActivity()).load(carList.get(0).getIcon().getUrl()).placeholder(R.drawable.car_image).into(iconL);
        Glide.with(getActivity()).load(carList.get(1).getIcon().getUrl()).placeholder(R.drawable.car_image).into(iconR);

    }

    private void getTuiJian() {
        if (carList != null){
            setTuijian();
            return;
        }
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Car> accountBmobQuery = new BmobQuery<Car>();
                accountBmobQuery.setLimit(2);
                accountBmobQuery.findObjects(new FindListener<Car>() {
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

    private void showDialog(int num) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.dialog_tuijian, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        iconD = (ImageView) view.findViewById(R.id.dialog_icon);
        nameD = (TextView) view.findViewById(R.id.dialog_name);
        descD = (TextView) view.findViewById(R.id.dialog_desc);
        priceD = (TextView) view.findViewById(R.id.dialog_price);

        Glide.with(getActivity()).load(carList.get(num).getIcon().getUrl()).placeholder(R.drawable.car_image).into(iconD);
        nameD.setText(carList.get(num).getCarName());
        descD.setText(carList.get(num).getXiangShu()+"|"+carList.get(num).getPaiLiang()+"|"+carList.get(num).getChengZuo());
        priceD.setText(carList.get(num).getPrice()+"");

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
