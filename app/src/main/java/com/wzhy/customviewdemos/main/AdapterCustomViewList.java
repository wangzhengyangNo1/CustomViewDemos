package com.wzhy.customviewdemos.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzhy.customviewdemos.AppSelf;
import com.wzhy.customviewdemos.R;

/**
 * Created by techfit on 2017/5/17.
 */

public class AdapterCustomViewList extends RecyclerView.Adapter<AdapterCustomViewList.VH> {

    private String[] itemArr = AppSelf.getAppContext().getResources().getStringArray(R.array.custom_view_arr);

    private OnItemClickListener mItemClickListener;

    public String[] getData() {
        return itemArr;
    }

    @Override
    public AdapterCustomViewList.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = AppSelf.getInflater().inflate(R.layout.item_custom_view, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterCustomViewList.VH holder, final int position) {
        holder.mItemTextView.setText(itemArr[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArr.length;
    }


    public class VH extends RecyclerView.ViewHolder{

        TextView mItemTextView;

        public VH(View itemView) {
            super(itemView);
            mItemTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

}
