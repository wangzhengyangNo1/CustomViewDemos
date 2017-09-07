package com.wzhy.customviewdemos;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStart;
    private ImageView mIvBall;
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStart = (Button) findViewById(R.id.btn_start);
        mIvBall = (ImageView) findViewById(R.id.iv_ball);
        initAnim();

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimator.start();
            }
        });
    }

    private void initAnim(){
        mAnimator = ValueAnimator.ofInt(0,300);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new BounceInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                mIvBall.setTranslationY(mBtnStart.getBottom() + animatedValue);
            }
        });
    }
}
