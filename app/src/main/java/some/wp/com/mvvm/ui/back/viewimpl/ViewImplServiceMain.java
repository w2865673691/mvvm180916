package some.wp.com.mvvm.ui.back.viewimpl;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.architecture.wplib.common.LogActs;
import com.architecture.wplib.data.BaseBean;
import com.architecture.wplib.ui.BaseView;

import javax.inject.Inject;

import some.wp.com.mvvm.data.beans.TabBean;
import some.wp.com.mvvm.model.models.MainModel;

public class ViewImplServiceMain extends BaseView {

    @Inject
    public void setSimpleA(SimpleA simpleA) {
        this.simpleA = simpleA;
    }

    SimpleA simpleA;

    @Inject
    public ViewImplServiceMain() {
    }

    public void onReady() {
        baseVM = viewModel(new MainModel());
        baseVM.getSimpleBean().observe(service, new Observer<BaseBean>() {
            @Override
            public void onChanged(@Nullable BaseBean baseBean) {
                if(baseBean!=null){
                    TabBean tabClass= (TabBean) baseBean;
                    LogActs.d("service-->"+tabClass.getTabName());
                }

                LogActs.d("SimpleA-->"+simpleA);
                LogActs.d("SimpleA-->"+simpleA.simpleB);
                simpleA.simpleB.dosome();
            }
        });
    }
}
