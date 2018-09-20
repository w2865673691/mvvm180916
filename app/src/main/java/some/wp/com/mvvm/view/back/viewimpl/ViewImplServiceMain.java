package some.wp.com.mvvm.view.back.viewimpl;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.architecture.wplib.common.LogActs;
import com.architecture.wplib.taskmodel.BaseBean;
import com.architecture.wplib.view.BaseView;

import javax.inject.Inject;

import some.wp.com.mvvm.taskmodel.beans.TabBean;
import some.wp.com.mvvm.taskmodel.models.MainModel;

public class ViewImplServiceMain extends BaseView {


    @Inject
    public ViewImplServiceMain() {
    }

    public void onReady() {
        baseVM = model(new MainModel());
        baseVM.getSimpleBean().observe(service, new Observer<BaseBean>() {
            @Override
            public void onChanged(@Nullable BaseBean baseBean) {
                if(baseBean!=null){
                    TabBean tabClass= (TabBean) baseBean;
                    LogActs.d("service-->"+tabClass.getTabName());
                }
            }
        });
    }
}
