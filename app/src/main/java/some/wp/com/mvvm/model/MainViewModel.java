package some.wp.com.mvvm.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.architecture.wplib.model.BaseViewModel;

import some.wp.com.mvvm.model.models.MainModel;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
        baseModel = new MainModel();
    }

}
