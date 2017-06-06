package com.wzhy.customviewdemos.customviews.animatorstu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzhy.customviewdemos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentValueAnimatorBase extends Fragment {


    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_value_animator_base, container, false);
        return layout;
    }

}
