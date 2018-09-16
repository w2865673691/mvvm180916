package some.wp.com.mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.List;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.R;
import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.viewmodel.BaseVM;

public class BaseActivity extends AppCompatActivity {
    protected ViewDataBinding binding;
    protected BaseVM baseVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void setBaseContent(int layoutId,Class<? extends BaseVM> modelClass) {
        binding = DataBindingUtil.setContentView(this, layoutId);
        baseVM = ViewModelProviders.of(this).get(modelClass);
        Observer<BaseBean> beanObserver = new Observer<BaseBean>() {
            @Override
            public void onChanged(@Nullable BaseBean baseBean) {
                // TabClass tabClass = (TabClass) baseBean;
                //binding.setBean(tabClass);
                binding.setVariable(BR.bean, baseBean);
            }
        };
        baseVM.getSimpleBean().observe(this, beanObserver);


        Observer<List<BaseBean>> listObsever=new Observer<List<BaseBean>>() {
            @Override
            public void onChanged(@Nullable List<BaseBean> baseBeans) {

            }
        };
        baseVM.getListBeans().observe(this,listObsever);

        baseVM.loadSimpleBean();
        baseVM.loadListBeans();
    }


//    mViewModel = new ViewModelProvider(
//                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
//            ).get(ImageViewModel.class);
}
