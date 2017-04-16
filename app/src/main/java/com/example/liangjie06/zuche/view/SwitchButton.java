package com.example.liangjie06.zuche.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.liangjie06.zuche.R;


/**
 * Created by liangjie06 on 17/3/10.
 */

public class SwitchButton extends View implements View.OnClickListener{
    private boolean mState = true;
    private OnChangedListener listener;
    private Drawable bg_on, bg_off;

    public SwitchButton(Context context) {
        super(context);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){
        //载入图片资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        bg_on = typedArray.getDrawable(R.styleable.SwitchButton_bg_on);
        bg_off = typedArray.getDrawable(R.styleable.SwitchButton_bg_off);

        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bg_off.setBounds(0,0,108,52);
        bg_on.setBounds(0,0,108,52);

        if (mState) {
            bg_on.draw(canvas);
        }else {
            bg_off.draw(canvas);
        }

    }

    @Override
    public void onClick(View v) {
        mState = !mState;
        if (listener!=null) {
            listener.Onchanged(mState);
        }
        invalidate();
    }

    public void setState(boolean state) {
        mState = state;
        invalidate();
    }

    public boolean getState() {
        return mState;
    }

    public interface OnChangedListener {
        void Onchanged(boolean isOpen);
    }

    public void setChangedListener(OnChangedListener listener) {
        this.listener = listener;
    }
}
