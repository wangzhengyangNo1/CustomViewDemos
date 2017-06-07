package com.wzhy.customviewdemos.customviews.animatorstu;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wzhy.customviewdemos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentValueAnimatorStart extends Fragment {


    private View mLayout;
    private TextView mTvCrosss;
    private Button mBtnStart;
    private Button mBtnCancel;
    private ValueAnimator mAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_fragment_value_animator_start, container, false);


        initView();
        addEvents();
        //initRepeatAnim();

        return mLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRepeatAnim();
    }

    private void initView(){
        mTvCrosss = (TextView) mLayout.findViewById(R.id.tv_cross);
        mBtnStart = (Button) mLayout.findViewById(R.id.btn_start);
        mBtnCancel = ((Button) mLayout.findViewById(R.id.btn_cancel));
    }

    private void addEvents(){
        mBtnStart.setOnClickListener(mClickListener);
        mBtnCancel.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    mAnimator.start();
                    break;
                case R.id.btn_cancel:
                    if (null == mAnimator) return;
                    //mAnimator.removeAllListeners();
                    //mAnimator.removeAllUpdateListeners();
                    mAnimator.cancel();
                    break;
            }
        }
    };


    /**
     * ValueAnimator常用方法：
     *  ·ValueAnimator setDuration(long duration) //设置动画时长，单位：毫秒
     *  ·Object getAnimatedValue() //获取动画运行时，当前动画帧的属性值
     *  ·void start() //开始动画
     *  ·void setRepeatCount(int value) //设置循环次数，设置为INFINITE表示无限循环
     *  ·void setRepeatMode(int value) //设置循环模式，value可取值，ValueAnimator.RESTART, ValueAnimator.REVERSE
     *  ·void cancel() //取消动画
     *
     * ValueAnimator的动画监听：
     * ·void addUpdateListener(AnimatorUpdateListener listener)
     * ·void addListener(AnimatorListener listener)  //父类Animator的动画监听方法
     *  AnimatorUpdateListener //监听动画的实时变化状态（属性值的）
     *  AnimatorListener //监听动画的运行状态，开始-start，结束-end，取消-cancel，重复-repeat
     *
     *  取消动画监听：
     *  ·void removeUpdateListener(AnimatorUpdateListener listener)
     *  ·void removeAllListeners()
     *  ·void removeListener(AnimatorListener listener)
     *  ·void removeAllUpdateListeners()
     *
     *  延迟启动和克隆：
     *  void setStartDelay(long startDelay) //延时开始动画
     *  ValueAnimator clone() //完全克隆一个ValueAnimator实例，包括它所有的设置以及所有对监听器代码的处理
     */
    private void initRepeatAnim(){
        Log.i("tag", "initRepeatAnim: top = " + mTvCrosss.getTop());
        mAnimator = ValueAnimator.ofInt(60, 460);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value  = (int) animation.getAnimatedValue();
                mTvCrosss.layout(mTvCrosss.getLeft(), value, mTvCrosss.getRight(), value + mTvCrosss.getHeight());
            }
        });

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("Anim", "Animation Start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("Anim", "Animation End");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("Anim", "Animation Cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("Anim", "Animation Repeat");
            }
        });
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }

}
