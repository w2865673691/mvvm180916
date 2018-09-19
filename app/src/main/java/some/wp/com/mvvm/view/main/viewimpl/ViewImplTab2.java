package some.wp.com.mvvm.view.main.viewimpl;

import javax.inject.Inject;

import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class ViewImplTab2 extends BaseView {


    @Inject
    public ViewImplTab2() {
    }

    public void onReady() {
       model(MainVm.class);
       // baseVM = model(new MainModel());
        baseVM.doLoadBean();
    }
}
