package com.example.liangjie06.zuche.module.bankaccount;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.base.BaseActivity;
import com.example.liangjie06.zuche.bean.Account;
import com.example.liangjie06.zuche.bean.User;
import com.example.liangjie06.zuche.utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;



/**
 * Created by liangjie06 on 17/4/21.
 */

public class BankAccountActivity extends BaseActivity {

    private final static String TAG = "BankAccountActivity";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    break;
                case 1:
                    mAdapter.notifyDataSetChanged();
                    Log.e("lj", "zhixinglema ");

                    longDelete();
                    break;

            }
        }
    };

    private Context mContext;
    private ListView listView;
    private ArrayList<Account> bankList;
    private MyAdapter mAdapter;


    public static void startBankAccountActivity(Context context){
        Intent intent = new Intent(context, BankAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        mContext = this;

        initView();
        getInfo();
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);

    }

    private void initView(){
        listView = (ListView) findViewById(R.id.bank_card_list);
        listView = (ListView) findViewById(R.id.bank_card_list);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBankCardActivity.startAddBankCardActivity(mContext);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInfo();
        mAdapter.notifyDataSetChanged();
    }

    private void getInfo(){
        ThreadPool.runOnPool(new Runnable() {
            @Override
            public void run() {
                User user = BmobUser.getCurrentUser(User.class);
                BmobQuery<Account> accountBmobQuery = new BmobQuery<Account>();
                accountBmobQuery.addWhereEqualTo("userName",user.getUsername())
                        .findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> list, BmobException e) {
                        if (e == null){
                            bankList = (ArrayList<Account>) list;

                            Message msg= Message.obtain();
                            msg.what = 1;
                            Log.e("lj", "银行卡列表查询成功"+ list.size() );
                            mHandler.sendEmptyMessage(msg.what);
                        }else {
                            Message msg= Message.obtain();
                            msg.what = 0;
                            Log.e("lj", "银行卡列表查询失败"+e.toString());
                            mHandler.sendEmptyMessage(msg.what);
                        }
                    }
                });
            }
        });
    }

    private void longDelete(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteData(position);

                return false;
            }
        });
    }

    private void deleteData(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BankAccountActivity.this);

        builder.setMessage("确定删除？");
        builder.setTitle("提示");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Account a = new Account();
                a.setObjectId(bankList.get(position).getObjectId());
                a.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){

                        }else {
                            Log.e(TAG,"数据删除失败" + e.toString());
                        }
                    }
                });
                bankList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bankList == null ? 0: bankList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("lj", "zhixinglema getview");

            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(mContext,R.layout.bank_card_item, null);
                holder.tvBankName = (TextView) convertView.findViewById(R.id.bankname);

                holder.tvBankNum = (TextView) convertView.findViewById(R.id.bank_card_id1);
                holder.btn = (Button) convertView.findViewById(R.id.delete1);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvBankName.setText(bankList.get(position).getBank());
            holder.tvBankNum.setText(bankList.get(position).getBankCard());


            return convertView;
        }
    }

    class ViewHolder {
        TextView tvBankName;
        TextView tvBankNum;
        Button btn;
    }
}
