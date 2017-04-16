package com.example.liangjie06.zuche.view.hotcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.view.hotcity.bean.City;

import java.util.List;

/**
 * Created by liangjie06 on 17/4/13.
 */

public class SearchResultAdapter extends BaseAdapter {

    private List<City> mSearchList;
    private Context mContext;
    private LayoutInflater mInflater;

    public SearchResultAdapter(Context context, List<City> searchList){
        this.mSearchList=searchList;
        this.mContext=context;
        mInflater= LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchList.get(position);
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
            convertView=mInflater.inflate(R.layout.item_search_list,null);
            viewHolder.tvCityName=(TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder) convertView.getTag();
        }

        viewHolder.tvCityName.setText(mSearchList.get(position).getName());
        viewHolder.tvCityName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mSearchList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder{
        LinearLayout ll_item;
        TextView tvCityName;
    }
}
