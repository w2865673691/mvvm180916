package some.wp.com.mvvm.view.main.viewimpl;

import javax.inject.Inject;

import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.view.BaseView;

public class ViewImplTab1 extends BaseView {


    @Inject
    public ViewImplTab1() {
    }

    public void onReady() {
       // mainVm = (MainVm) model(MainVm.class);
        baseVM = model(new MainModel());
        baseVM.doLoadBean();
    }
}
