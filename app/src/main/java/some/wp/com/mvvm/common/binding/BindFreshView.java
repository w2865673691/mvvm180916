package some.wp.com.mvvm.common.binding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import some.wp.com.mvvm.viewmodel.BaseVM;

public class BindFreshView {

    @BindingAdapter({"bindVM"})
    public static void bindVM(final View view, final BaseVM baseVM) {
       if(view!=null &&view instanceof XRecyclerView){
           XRecyclerView xRecyclerView= (XRecyclerView) view;
           XRecyclerView.LoadingListener loadListener=new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
                   baseVM.onRefresh(view);
               }

               @Override
               public void onLoadMore() {
                   baseVM.onLoadMore(view);
               }
           };
           xRecyclerView.setLoadingListener(loadListener);
          // xRecyclerView.setLoadingMoreEnabled(true);
       }else  if(view!=null &&view instanceof SwipeRefreshLayout){
           SwipeRefreshLayout swipeRefreshLayout= (SwipeRefreshLayout) view;
           SwipeRefreshLayout.OnRefreshListener loadListener=new SwipeRefreshLayout.OnRefreshListener() {
               @Override
               public void onRefresh() {
                   baseVM.onRefresh(view);
               }
           };
           swipeRefreshLayout.setOnRefreshListener(loadListener);
       }
    }
}
