package com.wzhy.customviewdemos.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wzhy.customviewdemos.AppSelf;
import com.wzhy.customviewdemos.R;
import com.wzhy.customviewdemos.customviews.animatorstu.ActivityAnimator;
import com.wzhy.customviewdemos.customviews.drawtext.ActivityDrawText;

public class ActivityMain extends AppCompatActivity {

    private RecyclerView mRvCustomViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvCustomViewList = (RecyclerView) findViewById(R.id.rv_custom_view_List);
        //mRvCustomViewList.addItemDecoration(mItemDecoration);
        mRvCustomViewList.setItemAnimator(new DefaultItemAnimator());
        mRvCustomViewList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        final AdapterCustomViewList mAdapter = new AdapterCustomViewList();
        mRvCustomViewList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterCustomViewList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AppSelf.showTestTip(mAdapter.getData()[position]);
                switch (position) {
                    case 0:
                        toActivity(ActivityDrawText.class, null);
                        break;
                    case 1:
                        toActivity(ActivityAnimator.class, null);
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    case 12:

                        break;
                    case 13:

                        break;
                    case 14:

                        break;
                }
            }
        });
    }

    private RecyclerView.ItemDecoration mItemDecoration = new RecyclerView.ItemDecoration() {
        final int offset = (int) AppSelf.getAppContext().getResources().getDimension(R.dimen.rv_offset_v);
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = offset;
            }

            outRect.bottom = offset;
        }
    };

    public void toActivity(Class<? extends Activity> clazz, @Nullable Bundle bundle) {
        startActivity(new Intent(this, clazz), bundle);
    }

}
