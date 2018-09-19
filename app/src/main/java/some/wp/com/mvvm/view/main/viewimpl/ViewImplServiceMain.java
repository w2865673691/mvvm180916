package some.wp.com.mvvm.view.main.viewimpl;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import some.wp.com.mvvm.common.LogActs;
import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.taskmodel.beans.TabClass;
import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.view.BaseView;

public class ViewImplServiceMain extends BaseView {


    @Inject
    public ViewImplServiceMain() {
    }

    public void onReady() {
        baseVM = model(new MainModel());
        baseVM.getSimpleBean().observe(service, new Observer<BaseBean>() {
            @Override
            public void onChanged(@Nullable BaseBean baseBean) {
                LogActs.d("onChanged-->"+baseBean);
                if(baseBean!=null){
                    TabClass tabClass= (TabClass) baseBean;
                    LogActs.d("service-->"+tabClass.getTabName());
                }
            }
        });
    }
}
