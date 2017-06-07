package com.wzhy.customviewdemos.customviews.animatorstu;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by techfit on 2017/6/5.
 */

public class ValueAnimatorStu {

    public static void startAnimatorOnView(final View view){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(curValue, curValue, curValue + view.getWidth(), curValue + view.getHeight());
            }
        });

        valueAnimator.start();
    }
}
