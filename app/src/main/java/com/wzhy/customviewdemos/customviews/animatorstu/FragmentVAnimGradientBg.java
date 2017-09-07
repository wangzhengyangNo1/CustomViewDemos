package com.wzhy.customviewdemos.customviews.animatorstu;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzhy.customviewdemos.R;

public class FragmentVAnimGradientBg extends Fragment {

    private View mLayoutView;
    private TextView mBgGradientTextView;
    private ValueAnimator mBgGradientAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_fragment_vanim_gradient_bg, container, false);
        initView();
        initBgGradientAnimator();
        setEvents();
        mBgGradientAnimator.start();
        return mLayoutView;
    }

    /**
     * 初始化控件
     */
    private void initView(){
        mBgGradientTextView = (TextView) mLayoutView.findViewById(R.id.tv_bg_gradient);
    }

    /**
     * 初始化背景渐变属性动画
     */
    private void initBgGradientAnimator(){
        mBgGradientAnimator = ValueAnimator.ofInt(Color.RED, Color.BLUE).setDuration(2000);
        mBgGradientAnimator.setEvaluator(new ArgbEvaluator());
        mBgGradientAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mBgGradientAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }

    private void setEvents(){
        mBgGradientAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                mBgGradientTextView.setBackground(new ColorDrawable(color));

                int a = (color >> 24) & 0xFF;
                int r = (color >> 16) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = color & 0xFF;

                int textColor = a << 24
                        | (0xFF - r) << 16
                        | (0xFF - g) << 8
                        | (0xFF - b);

                mBgGradientTextView.setTextColor(textColor);
            }
        });
    }

    @Override
    public void onDestroy() {
        mBgGradientAnimator.cancel();
        mBgGradientAnimator.removeAllUpdateListeners();
        super.onDestroy();
    }
}
