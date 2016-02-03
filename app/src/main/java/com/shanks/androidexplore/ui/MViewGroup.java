package com.shanks.androidexplore.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by shanksYao on 10/13/15.
 */
public class MViewGroup extends ViewGroup {
    public MViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MViewGroup(Context context) {
        super((context));
        init(context);
    }

    public MViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context mContext) {
        Button btn = new Button(mContext);
        btn.setText("I am Button");
        this.addView(btn);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int specSize_Widht = MeasureSpec.getSize(widthMeasureSpec);
        int specSize_Heigth = MeasureSpec.getSize(heightMeasureSpec);

        Log.e("MV", "**** specSize_Widht " + specSize_Widht + " * specSize_Heigth   *****" + specSize_Heigth);
        Log.e("MM",  MeasureSpec.getMode(widthMeasureSpec)+"===="+  MeasureSpec.getMode(heightMeasureSpec));

        for (int i = 0; i < getChildCount(); i++) {
          //  getChildAt(i).measure();
            measureChildren(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout", "----------");
    }
}
