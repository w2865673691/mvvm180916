package some.wp.com.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.architecture.wplib.viewmodel.BaseVM;

import some.wp.com.mvvm.taskmodel.models.MainModel;

public class MainVm extends BaseVM {

    public MainVm(@NonNull Application application) {
        super(application);
        baseModel = new MainModel();
    }

}
