package com.wzhy.customviewdemos.customviews.spidernet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wzhy.customviewdemos.R;

/**
 * Created by wangzhengyang on 2017/5/17.
 * 蜘蛛网图
 */

public class SpiderNetView extends View{

    private Paint mPaint;
    private int mNetColor;
    private int mOverlayAlpha;
    private int mOverlayColor;
    private int mTagSize;
    private int mTextColor;

    public SpiderNetView(Context context) {
        super(context);
        init();
    }

    public SpiderNetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderNetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttributes(context, attrs, defStyleAttr);

    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpiderNetView, defStyleAttr, 0);
        int N = ta.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.SpiderNetView_netColor:
                    mNetColor = ta.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.SpiderNetView_overlayAlpha:
                    mOverlayAlpha = ta.getInteger(attr, 0);
                    break;
                case R.styleable.SpiderNetView_overlayColor:
                    mOverlayColor = ta.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.SpiderNetView_tagSize:
                    mTagSize = ta.getInteger(attr, 24);
                    break;
                case R.styleable.SpiderNetView_textColor:
                    mTextColor = ta.getColor(attr, Color.BLUE);
                    break;
            }
        }

    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.CYAN);
    }


}
