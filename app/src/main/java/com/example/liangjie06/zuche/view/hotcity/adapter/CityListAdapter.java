package com.example.liangjie06.zuche.view.hotcity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.liangjie06.zuche.MyApplication;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.view.hotcity.CityListActivity;
import com.example.liangjie06.zuche.view.hotcity.bean.City;
import com.example.liangjie06.zuche.view.hotcity.view.MyGridView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by liangjie06 on 17/3/13.
 */

public class CityListAdapter extends BaseAdapter {

    private Context mContext;
    private List<City> mAllCityList;
    private List<City> mHotCityList;
    private List<String> mRecentCityList;
    public HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[] sections;// 存放存在的汉语拼音首字母
    private String currentCity;//当前城市
    private boolean isNeedRefresh;//当前定位的城市是否需要刷新
    private TextView tvCurrentLocateCity;
    private ProgressBar pbLocate;
    private TextView tvLocate;
    private final int VIEW_TYPE = 5;//view的类型个数
    private CityListActivity mActivity;

    public MyLocationListener myLocationListener;
    private MyApplication mApp = MyApplication.getInstance();


    public CityListAdapter(Context context, List<City> allCityList,
                           List<City> hotCityList, List<String> recentCityList,
                           CityListActivity activity) {
        this.mContext = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
        this.mRecentCityList=recentCityList;
        mActivity = activity;

        alphaIndexer = new HashMap<String, Integer>();
        sections = new String[allCityList.size()];

        //这里的主要目的是将listview中要显示字母的条目保存下来，方便在滑动时获得位置，alphaIndexer在Acitivity有调用
        for (int i = 0; i < mAllCityList.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = getAlpha(mAllCityList.get(i).getPinyin());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? getAlpha(mAllCityList.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(mAllCityList.get(i).getPinyin());
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
        isNeedRefresh=true;
        initLocation();
    }

    @Override
    public int getViewTypeCount() {

        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return mAllCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int viewType = getItemViewType(position);
        if (viewType == 0) {//view类型为0，也就是：当前定位城市的布局
            convertView = View.inflate(mContext, R.layout.item_location_city,
                    null);
            tvLocate=(TextView) convertView.findViewById(R.id.tv_locate);
            tvCurrentLocateCity=(TextView) convertView.findViewById(R.id.tv_current_locate_city);
            pbLocate = (ProgressBar) convertView.findViewById(R.id.pb_loacte);

            if(!isNeedRefresh){
                tvLocate.setText("当前定位城市");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText(currentCity);
                pbLocate.setVisibility(View.GONE);
            }else{
                mApp.mLocationClient.startLocation();

            }

            tvCurrentLocateCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pbLocate.setVisibility(View.VISIBLE);
                    tvLocate.setText("正在定位");
                    tvCurrentLocateCity.setVisibility(View.GONE);
                    mApp.mLocationClient.startLocation();

                }
            });

        } else if (viewType == 1) {//最近访问城市
            convertView = View.inflate(mContext,R.layout.item_recent_visit_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.tv_recent_visit_city);
            tvRecentVisitCity.setText("最近访问城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.gv_recent_visit_city);
            gvRecentVisitCity.setAdapter(new RecentVisitCityAdapter(mContext,mRecentCityList, mActivity));
            gvRecentVisitCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO
                    mActivity.sendCityName(mRecentCityList.get(position));
                }
            });

        } else if (viewType == 2) {//热门城市
            convertView = View.inflate(mContext,R.layout.item_recent_visit_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.tv_recent_visit_city);
            tvRecentVisitCity.setText("热门城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.gv_recent_visit_city);
            gvRecentVisitCity.setAdapter(new HotCityAdapter(mContext,mHotCityList,mActivity));
        } else if (viewType == 3) {//全部城市，仅展示“全部城市这四个字”
            convertView = View.inflate(mContext,R.layout.item_all_city_textview, null);
        } else {//数据库中所有的城市的名字展示
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_city_list,null);
                viewHolder.tvAlpha = (TextView) convertView.findViewById(R.id.tv_alpha);
                viewHolder.tvCityName = (TextView) convertView.findViewById(R.id.tv_city_name);
                viewHolder.llMain=(LinearLayout) convertView.findViewById(R.id.ll_main);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position >= 1) {
                viewHolder.tvCityName.setText(mAllCityList.get(position).getName());
                /*viewHolder.llMain.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,mAllCityList.get(position).getName()+"as",Toast.LENGTH_SHORT).show();
                        mActivity.sendCityName(mAllCityList.get(position).getName());
                    }
                });*/
                String currentStr = getAlpha(mAllCityList.get(position).getPinyin());
                String previewStr = (position - 1) >= 0 ? getAlpha(mAllCityList
                        .get(position - 1).getPinyin()) : " ";
                //如果当前的条目的城市名字的拼音的首字母和其前一条条目的城市的名字的拼音的首字母不相同，则将布局中的展示字母的TextView展示出来
                if (!previewStr.equals(currentStr)) {
                    viewHolder.tvAlpha.setVisibility(View.VISIBLE);
                    viewHolder.tvAlpha.setText(currentStr);
                } else {
                    viewHolder.tvAlpha.setVisibility(View.GONE);
                }
            }

        }

        return convertView;
    }

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "定位";
        } else if (str.equals("1")) {
            return "最近";
        } else if (str.equals("2")) {
            return "热门";
        } else if (str.equals("3")) {
            return "全部";
        } else {
            return "#";
        }
    }

    class ViewHolder {
        TextView tvAlpha;
        TextView tvCityName;
        LinearLayout llMain;
    }

    public void initLocation() {
        myLocationListener=new MyLocationListener();
        //设置定位回调监听
        mApp.mLocationClient.setLocationListener(myLocationListener);
        mApp.mLocationClient.startLocation();
    }

    public class MyLocationListener implements AMapLocationListener{
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                    tvLocate.setText("当前定位城市");
                    tvCurrentLocateCity.setVisibility(View.VISIBLE);
                    tvCurrentLocateCity.setText(aMapLocation.getCity());
                    mApp.mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                    pbLocate.setVisibility(View.GONE);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());

                    tvCurrentLocateCity.setVisibility(View.VISIBLE);
                    tvLocate.setText("未定位到城市,请选择");
                    tvCurrentLocateCity.setText("重新选择");
                    pbLocate.setVisibility(View.GONE);
                    return;
                }
            }
        }

    }

    public interface GetCity{
        void getCity(String str);
    }


}
