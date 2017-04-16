package com.example.liangjie06.zuche.view.hotcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.view.hotcity.CityListActivity;
import com.example.liangjie06.zuche.view.hotcity.bean.City;

import java.util.List;

/**
 * Created by liangjie06 on 17/3/13.
 */

public class HotCityAdapter extends BaseAdapter {
    private List<City> mHotCityList;
    private LayoutInflater mInflater;
    private Context mContext;
    private CityListActivity mActivity;

    public HotCityAdapter(Context context, List<City> hotCityList, CityListActivity activity) {
        this.mHotCityList = hotCityList;
        this.mContext = context;
        mActivity = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mHotCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_city, null);
            viewHolder.tvCityName = (TextView) convertView
                    .findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCityName.setText(mHotCityList.get(position).getName());
        viewHolder.tvCityName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mHotCityList.get(position).getName() + "", Toast.LENGTH_SHORT).show();
                mActivity.sendCityName(mHotCityList.get(position).getName());
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvCityName;
    }
}
