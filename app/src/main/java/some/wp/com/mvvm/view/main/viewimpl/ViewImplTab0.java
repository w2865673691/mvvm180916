package some.wp.com.mvvm.view.main.viewimpl;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.view.main.NewsAdapter;

public class ViewImplTab0 extends BaseView {

    // MainVm mainVm;
    private NewsAdapter newsAdapter;
    private XRecyclerView xRecyclerView;

    @Inject
    public ViewImplTab0() {
    }

    public void onReady() {
        baseVM = model(new MainModel());
        newsAdapter = new NewsAdapter(baseActivity);
        if (binding != null) {
            binding.setVariable(BR.rvAdapter, newsAdapter);
            binding.setVariable(BR.baseView, this);
        }
        Observer<List<BaseBean>> listObsever = new Observer<List<BaseBean>>() {
            @Override
            public void onChanged(@Nullable List<BaseBean> baseBeans) {
                newsAdapter.refreshData(baseBeans);
                if (xRecyclerView != null) {
                    xRecyclerView.loadMoreComplete(); //结束加载
                    xRecyclerView.refreshComplete(); //结束刷新
                }
            }
        };
        baseVM.getFreshBeans().observe(baseFragment, listObsever);
    }

    @Override
    public void doRefresh(View view) {
        if (view != null) {
            xRecyclerView = (XRecyclerView) view;
        } else {
            xRecyclerView = null;
        }
        super.doRefresh(view);
    }

    @Override
    public void doLoadMore(View view) {
        super.doLoadMore(view);
    }

    @Override
    public void onFailure(View view) {
        super.onFailure(view);
    }
}