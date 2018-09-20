package com.architecture.wplib.common.binding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import com.architecture.wplib.view.BaseView;

public class BindFreshView {

    @BindingAdapter({"bindVM"})
    public static void bindVM(final View view, final BaseView baseView) {
       if(view!=null &&view instanceof XRecyclerView){
           XRecyclerView xRecyclerView= (XRecyclerView) view;
           XRecyclerView.LoadingListener loadListener=new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
                   baseView.doRefresh(view);
               }

               @Override
               public void onLoadMore() {
                   baseView.doLoadMore(view);
               }
           };
           xRecyclerView.setLoadingListener(loadListener);
          // xRecyclerView.setLoadingMoreEnabled(true);
       }else  if(view!=null &&view instanceof SwipeRefreshLayout){
           SwipeRefreshLayout swipeRefreshLayout= (SwipeRefreshLayout) view;
           SwipeRefreshLayout.OnRefreshListener loadListener=new SwipeRefreshLayout.OnRefreshListener() {
               @Override
               public void onRefresh() {
                   baseView.doLoadBean(view);
               }
           };
           swipeRefreshLayout.setOnRefreshListener(loadListener);
       }
    }
}
