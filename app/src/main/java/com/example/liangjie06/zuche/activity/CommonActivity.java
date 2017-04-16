package com.example.liangjie06.zuche.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.liangjie06.zuche.R;


/**
 * 通用Activity,用于加载Fragment。
 */
public class CommonActivity extends FragmentActivity {
    public static final String FRAGMENT_NAME = "fragment_name";
    private static final String FROM = "click_from";

    public static void startDxCommonActivity(Context context, String fragment) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra(FRAGMENT_NAME, fragment);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScreenBackDialog.class.getName().equals(getIntent().getStringExtra(FRAGMENT_NAME))) {
            setTheme(R.style.MyTheme_BackDialog);
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            window.setGravity(Gravity.BOTTOM);
            window.setAttributes(lp);
        }
        setContentView(R.layout.common_activity);
        gotoPage(getIntent().getStringExtra(FRAGMENT_NAME));
    }

    public boolean gotoPage(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = Fragment.instantiate(this, key);
        if (fragment == null) {
            return false;
        }
        transaction.replace(R.id.fragment, fragment);
        transaction.commitAllowingStateLoss();
        return true;
    }

    /*public void show() {
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.MONTH_DAY_HOUR_MIN)
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis())
                .setMaxMilliseconds(System.currentTimeMillis() + TWO_MONTH)
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();
        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getSupportFragmentManager(), "year_month_day");
            }
        }
    }*/
}
