package com.wzhy.customviewdemos.customviews.animatorstu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzhy.customviewdemos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCommon extends Fragment {

    private View mlayout;
    private TextView mTvTag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mlayout = inflater.inflate(R.layout.fragment_fragment_common, container, false);
        mTvTag = (TextView) mlayout.findViewById(R.id.tv_tag);
        mTvTag.setText("");
        return mlayout;
    }

}
