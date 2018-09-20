package some.wp.com.mvvm.ui.main.viewimpl;

import com.architecture.wplib.ui.BaseView;

import javax.inject.Inject;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.model.MainViewModel;

public class ViewImplTab2 extends BaseView {


    @Inject
    public ViewImplTab2() {
        brID = BR.simpleBean;
    }

    public void onReady() {
       viewModel(MainViewModel.class);
       // baseVM = viewModel(new MainModel());

        doLoadBean(null);
    }
}
