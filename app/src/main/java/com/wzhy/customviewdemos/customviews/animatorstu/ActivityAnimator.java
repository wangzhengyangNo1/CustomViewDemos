package com.wzhy.customviewdemos.customviews.animatorstu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzhy.customviewdemos.AppSelf;
import com.wzhy.customviewdemos.R;

public class ActivityAnimator extends AppCompatActivity {


    private HorizontalScrollView mHsv;
    private View mIndicatorView;
    private LinearLayout mLlContent;

    /**
     * 前一个tab的id
     */
    private int mPreId = 0;
    /**
     * 前一个tab距离屏幕左边的距离
     */
    private int preLeft = 0;
    private int widthPixels;

    private final String[] mTags = new String[]{
            "ValueAnimator", "tag2", "tag3", "tag4", "tag5", "tag6",
            "tag7", "tag8", "tag9", "tag10", "tag11", "tag12","tag13",
            "tag14", "tag15", "tag16", "tag17", "tag18", "tag19", "tag20"
    };
    private FragmentManager mFragmentManager;
    private FragmentValueAnimatorBase mFragVab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        widthPixels = getScreenWidth();
        initView();
        initFragments();

        mLlContent.getChildAt(0).post(new Runnable() {
            @Override
            public void run() {
                mLlContent.getChildAt(0).performClick();
            }
        });
    }

    private int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void initView() {
        mHsv = (HorizontalScrollView) findViewById(R.id.hsv);
        mIndicatorView = findViewById(R.id.view_indicator);
        mLlContent = (LinearLayout) findViewById(R.id.ll_content);
        initTags();

    }

    private void switchPage(int id) {
        TextView curTag;
        if (mPreId == id){
            if (id == 0) {
                curTag = (TextView) mLlContent.getChildAt(id);
                curTag.setTextColor(Color.RED);
                int measuredWidth = curTag.getMeasuredWidth();
                Log.d("ActivityAnimator", "curTag.getLeft():" + curTag.getLeft());

                float preLeftRat = 1.0f * curTag.getLeft()/widthPixels;
                float curLeftRat = 1.0f * curTag.getLeft()/widthPixels;
                translateIndicator(preLeftRat, curLeftRat);
                mIndicatorView.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, FrameLayout.LayoutParams.MATCH_PARENT));
            }
            return;
        }

        TextView preTag = (TextView) mLlContent.getChildAt(mPreId);
        preTag.setTextColor(Color.DKGRAY);
        curTag = (TextView) mLlContent.getChildAt(id);
        curTag.setTextColor(Color.RED);

        int scrollX = (int) (curTag.getLeft() - 0.6f * widthPixels);
        if (scrollX < 0) {
            scrollX = 0;
        }

        int maxScrollX = mHsv.getChildAt(0).getRight() - widthPixels;
        if (scrollX > maxScrollX) scrollX = maxScrollX;
        mHsv.smoothScrollTo(scrollX, 0);

        int curLeft = curTag.getLeft() - scrollX;

        float preLeftRat = 1.0f * preLeft/widthPixels;
        float curLeftRat = 1.0f * curLeft/widthPixels;

        translateIndicator(preLeftRat, curLeftRat);

        mIndicatorView.setLayoutParams(new FrameLayout.LayoutParams(curTag.getWidth(), FrameLayout.LayoutParams.MATCH_PARENT));
        preLeft = curLeft;

        Fragment prePage = mFragmentManager.findFragmentByTag("tag" + mPreId);
        Fragment curPage = mFragmentManager.findFragmentByTag("tag" + id);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(prePage);
        transaction.show(curPage);
        transaction.commit();

        mPreId = id;
    }

    private void translateIndicator(float preLeftRat, float curLeftRat) {
        TranslateAnimation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, preLeftRat,
                Animation.RELATIVE_TO_PARENT, curLeftRat,
                Animation.RELATIVE_TO_PARENT, mIndicatorView.getTop(),
                Animation.RELATIVE_TO_PARENT, mIndicatorView.getTop());

        anim.setDuration(100);
        anim.setFillAfter(true);
        mIndicatorView.startAnimation(anim);
    }

    private void initTags() {
        mLlContent.removeAllViews();
        for (int i = 0; i < mTags.length; i++) {
            TextView tvTag = (TextView) AppSelf.getInflater().inflate(R.layout.layout_tag, mLlContent, false);
            tvTag.setTextColor(Color.DKGRAY);
            tvTag.setText(mTags[i]);
            tvTag.setId(i);
            mLlContent.addView(tvTag);
            tvTag.setOnClickListener(mClickListener);
        }
    }

    private void initFragments() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mFragVab = new FragmentValueAnimatorBase();
        transaction.add(R.id.fl_container, mFragVab, "tag0");
        for (int i = 1; i < mTags.length; i++) {
            FragmentCommon fragmentCommon = new FragmentCommon();
            transaction.add(R.id.fl_container, fragmentCommon, "tag" + i);
            transaction.hide(fragmentCommon);
        }
        transaction.commit();
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchPage(v.getId());
        }
    };

}
