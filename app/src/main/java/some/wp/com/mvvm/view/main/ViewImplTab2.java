package some.wp.com.mvvm.view.main;

import javax.inject.Inject;

import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class ViewImplTab2 extends BaseView {

    MainVm mainVm;

    @Inject
    public ViewImplTab2() {
    }

    public void onReady() {
        mainVm = (MainVm) model(MainVm.class);

        baseVM.doLoadBean();
    }
}
