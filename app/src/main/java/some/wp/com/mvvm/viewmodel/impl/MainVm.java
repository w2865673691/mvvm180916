package some.wp.com.mvvm.viewmodel.impl;

import android.app.Application;
import android.support.annotation.NonNull;

import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.viewmodel.BaseVM;

public class MainVm extends BaseVM {
    public MainModel mainModel;

    public MainVm(@NonNull Application application) {
        super(application);
        mainModel=new MainModel();
    }

    @Override
    public void doLoadBean(Object... objects) {
        mainModel.loadBean(this);
    }

    @Override
    public void doRefresh(Object... objects) {
        mainModel.freshData(this);
    }

    @Override
    public void doLoadMore(Object... objects) {
        mainModel.loadMoreData(this);
    }
}
