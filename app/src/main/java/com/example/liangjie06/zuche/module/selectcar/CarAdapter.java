package com.example.liangjie06.zuche.module.selectcar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.liangjie06.zuche.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack-Liang on 2017/3/5.
 */
public class CarAdapter extends BaseAdapter {
    private List mList = new ArrayList();
    private Context mContext;

    public CarAdapter(Context context, List list){
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        //return mList.size();
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.car_item1, null);
            viewHoder = new ViewHoder();
            viewHoder.ivCar = (ImageView) convertView.findViewById(R.id.icon_car);
            viewHoder.tvCarName = (TextView) convertView.findViewById(R.id.car_name);
            viewHoder.tvCarDesc = (TextView) convertView.findViewById(R.id.car_decs);
            viewHoder.ivXin = (ImageView) convertView.findViewById(R.id.car_xin);
            viewHoder.ivRe = (ImageView) convertView.findViewById(R.id.car_re);
            viewHoder.ivte = (ImageView) convertView.findViewById(R.id.car_te);
            viewHoder.tvPrice = (TextView) convertView.findViewById(R.id.car_price);
            viewHoder.btnChangeStroe = (Button) convertView.findViewById(R.id.change_store);
            convertView.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        viewHoder.ivCar.setImageResource(R.drawable.account_logout_content_title);
        viewHoder.tvCarName.setText("劳斯莱斯幻影");
        viewHoder.tvCarDesc.setText("三厢｜2.0｜乘坐5人");
        viewHoder.ivXin.setVisibility(View.VISIBLE);
        viewHoder.ivRe.setVisibility(View.VISIBLE);
        viewHoder.ivte.setVisibility(View.VISIBLE);
        viewHoder.tvPrice.setText("88");
        viewHoder.btnChangeStroe.setVisibility(View.VISIBLE);

        return convertView;
    }

    static class ViewHoder {
        public ImageView ivCar;
        public TextView tvCarName;
        public TextView tvCarDesc;
        public ImageView ivXin;
        public ImageView ivRe;
        public ImageView ivte;
        public TextView tvPrice;
        public Button btnChangeStroe;
    }
}
