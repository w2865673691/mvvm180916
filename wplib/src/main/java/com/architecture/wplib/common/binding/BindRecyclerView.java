package com.architecture.wplib.common.binding;

import android.databinding.BindingAdapter;

import com.architecture.wplib.ui.adapter.BaseAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


public class BindRecyclerView {

    @BindingAdapter({"recyclerAapter"})
    public static void recyclerAapter(XRecyclerView recyclerView, BaseAdapter baseAdapter) {
        recyclerView.setAdapter(baseAdapter);
    }
}
