package com.example.liangjie06.zuche.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.module.bankaccount.BankAccountActivity;
import com.example.liangjie06.zuche.view.hotcity.CityListActivity;

import java.util.ArrayList;

/**
 * Created by liangjie06 on 17/4/23.
 */

public class SelectBankCardActivity extends BaseActivity {


    private ArrayList<Account> bankList;
    private ListView listView;
    private LinearLayout add;
    private Button back;
    private Context mContext;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme_BackDialog);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        setContentView(R.layout.activity_select_bankcard);
        bankList = (ArrayList<Account>) getIntent().getSerializableExtra("card");
        mContext = this;
        initView();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.list);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectBankCardActivity.this.setResult(position);
                SelectBankCardActivity.this.finish();
            }
        });
        add = (LinearLayout) findViewById(R.id.addbankcard);
        back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectBankCardActivity.this.setResult(10);
                SelectBankCardActivity.this.finish();
            }
        });

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return bankList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.select_item, null);
                viewHolder.dui = (ImageView) convertView.findViewById(R.id.dui);
                viewHolder.desc = (TextView) convertView.findViewById(R.id.backid);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String str = bankList.get(position).getBankCard().substring(bankList.get(position).getBankCard().length()-4,bankList.get(position).getBankCard().length());

            viewHolder.desc.setText(bankList.get(position).getBank()+"("+str+")");
            if (position == 0){
                viewHolder.dui.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }

    class ViewHolder{
        public TextView desc;
        private ImageView dui;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SelectBankCardActivity.this.setResult(10);
        SelectBankCardActivity.this.finish();
    }
}
