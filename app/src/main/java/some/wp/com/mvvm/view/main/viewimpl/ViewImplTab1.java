package some.wp.com.mvvm.view.main.viewimpl;

import com.architecture.wplib.view.BaseView;

import javax.inject.Inject;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.viewmodel.MainVm;

public class ViewImplTab1 extends BaseView {


    @Inject
    public ViewImplTab1() {
        brID = BR.simpleBean;
    }

    public void onReady() {
        model(MainVm.class);
       // baseVM = model(new MainModel());

        doLoadBean(null);
    }
}
