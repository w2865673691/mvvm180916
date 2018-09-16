package some.wp.com.mvvm.viewmodel.impl;

import android.app.Application;
import android.support.annotation.NonNull;

import some.wp.com.mvvm.taskmodel.beans.TabClass;
import some.wp.com.mvvm.viewmodel.BaseVM;

public class MainVm extends BaseVM {


    public MainVm(@NonNull Application application) {
        super(application);
    }

    @Override
    public void loadSimpleBean() {
        super.loadSimpleBean();

        TabClass tabClass=new TabClass();
        tabClass.setTabName("MainVm2"+"\n"+tabClass.hashCode());
        simpleBean.setValue(tabClass);
    }

    @Override
    public void loadListBeans() {
        super.loadListBeans();
    }
}
