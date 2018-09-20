package com.architecture.wplib.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context mContext;
    public List<?> mList; // 数据源
    public LayoutInflater inflater;

    public BaseAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        onBindVH(vh, position);
    }

    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    public abstract void onBindVH(VH vh, int position);

    /**
     * 刷新数据
     */
    public void refreshData(List data) {
        if(data==null){
            return;
        }
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void loadMoreData(List data) {
        if(data==null){
          return;
        }
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
