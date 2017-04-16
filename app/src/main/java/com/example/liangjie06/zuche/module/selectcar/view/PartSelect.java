package com.example.liangjie06.zuche.module.selectcar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liangjie06.zuche.R;


/**
 * Created by liangjie06 on 17/3/6.
 */

public class PartSelect extends FrameLayout {
    private ImageView mIcon;
    private TextView mTitle;
    private TextView mDecs;
    public PartSelect(Context context) {
        super(context);
    }

    public PartSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public PartSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.part_select, this);
        mIcon = (ImageView) view.findViewById(R.id.part_left_icon);
        mTitle = (TextView) view.findViewById(R.id.part_title);
        mDecs = (TextView) view.findViewById(R.id.part_decs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PartSelect);
        int color2 = typedArray.getColor(R.styleable.PartSelect_partTitleColor, R.color.partTitleColor);
        Boolean iconShow = typedArray.getBoolean(R.styleable.PartSelect_iconVisiable, false);
        String partTitle = typedArray.getString(R.styleable.PartSelect_partTitle);
        String partDesc = typedArray.getString(R.styleable.PartSelect_partDecs);
        Drawable drawable = typedArray.getDrawable(R.styleable.PartSelect_iconResouse);

        if (iconShow) {
            mIcon.setVisibility(VISIBLE);
        }else {
            mIcon.setVisibility(INVISIBLE);
        }

        mIcon.setImageDrawable(drawable);
        mTitle.setText(partTitle);
        mTitle.setTextColor(color2);
        mDecs.setText(partDesc);

    }

    public void setPartTitle(String string) {
        mTitle.setText(string);
    }

    public void setPartTitleColor(int color) {
        mTitle.setTextColor(color);
        Log.e("lj","hahahhahahahah");
    }

    public void setPartDesc(String string){
        mDecs.setText(string);
    }
}
