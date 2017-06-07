package com.wzhy.customviewdemos.customviews.animatorstu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wzhy.customviewdemos.AppSelf;
import com.wzhy.customviewdemos.R;

public class FragmentValueAnimatorBase extends Fragment {


    private View mLayout;
    private TextView mTvObject;
    private Button mBtnStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_value_animator_base, container, false);
        mTvObject = (TextView) mLayout.findViewById(R.id.tv_object);
        mBtnStart = (Button) mLayout.findViewById(R.id.btn_start);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimatorStu.startAnimatorOnView(mTvObject);
            }
        });

        mTvObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSelf.showTip("我被点击了...");
            }
        });

        return mLayout;
    }

}
