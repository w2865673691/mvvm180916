package some.wp.com.mvvm.viewmodel.impl;

import android.app.Application;
import android.support.annotation.NonNull;

import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.viewmodel.BaseVM;

public class MainVm extends BaseVM {

    public MainVm(@NonNull Application application) {
        super(application);
        baseModel = new MainModel();
    }

}
