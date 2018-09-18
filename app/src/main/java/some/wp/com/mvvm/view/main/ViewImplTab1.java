package some.wp.com.mvvm.view.main;

import javax.inject.Inject;

import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class ViewImplTab1 extends BaseView {

    MainVm mainVm;

    @Inject
    public ViewImplTab1() {
    }

    public void onReady() {
        mainVm = (MainVm) model(MainVm.class);

        baseVM.loadSimpleBean();
    }
}
