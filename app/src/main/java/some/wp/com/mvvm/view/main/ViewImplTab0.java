package some.wp.com.mvvm.view.main;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class ViewImplTab0 extends BaseView {

    MainVm mainVm;
    private NewsAdapter newsAdapter;

    @Inject
    public ViewImplTab0() {
    }

    public void onReady() {
        mainVm = (MainVm) model(MainVm.class);
        newsAdapter=new NewsAdapter(baseActivity);
        if(binding!=null){
            binding.setVariable(BR.rvAdapter, newsAdapter);
        }
        Observer<List<BaseBean>> listObsever=new Observer<List<BaseBean>>() {
            @Override
            public void onChanged(@Nullable List<BaseBean> baseBeans) {
                newsAdapter.refreshData(baseBeans);
            }
        };
        mainVm.getListBeans().observe(baseFragment, listObsever);
        mainVm.loadListBeans();
    }

    @Override
    public void onFreshData(Object... objects) {
        super.onFreshData(objects);
    }

    @Override
    public void onMoreData(Object... objects) {
        super.onMoreData(objects);
    }

    @Override
    public void onFailure(Object... objects) {
        super.onFailure(objects);
    }
}
