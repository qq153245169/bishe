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

import java.util.List;

/**
 * Created by liangjie06 on 17/4/13.
 */

public class RecentVisitCityAdapter extends BaseAdapter {

    private List<String> mRecentVisitCityList;
    private LayoutInflater mInflater;
    private Context mContext;
    private CityListActivity mActivity;

    public RecentVisitCityAdapter(Context context, List<String> recentVisitCityList, CityListActivity activity) {
        this.mRecentVisitCityList=recentVisitCityList;
        this.mContext=context;
        mActivity = activity;
        mInflater= LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mRecentVisitCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecentVisitCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_city,null);
            viewHolder.tvCityName=(TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tvCityName.setText(mRecentVisitCityList.get(position));
        viewHolder.tvCityName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mRecentVisitCityList.get(position)+"", Toast.LENGTH_SHORT).show();
                mActivity.sendCityName(mRecentVisitCityList.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tvCityName;
    }
}
