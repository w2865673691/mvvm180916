package some.wp.com.mvvm.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.viewmodel.BaseVM;

import static dagger.internal.Preconditions.checkNotNull;

public abstract class BaseView {
    protected LayoutInflater inflater;
    protected ViewDataBinding binding;
    protected BaseVM baseVM;
    protected BaseActivity baseActivity;
    protected BaseFragment baseFragment;
    protected Observer<BaseBean> beanObserver = new Observer<BaseBean>() {
        @Override
        public void onChanged(@Nullable BaseBean baseBean) {
            // TabClass tabClass = (TabClass) baseBean;
            //binding.setBean(tabClass);
            if (binding != null) {
                binding.setVariable(BR.simpleBean, baseBean);
            }
        }
    };

    public BaseView activity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        this.baseFragment = null;
        if (baseActivity != null) {
            inflater = (LayoutInflater) baseActivity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return this;
    }

    public BaseView fragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
        Activity activity = baseFragment.getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            baseActivity = (BaseActivity) activity;
        }
        if (baseActivity != null) {
            inflater = (LayoutInflater) baseActivity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return this;
    }

    public ViewDataBinding bind(int layoutId, ViewGroup container) {
        if (baseFragment == null && baseActivity != null) {
            binding = DataBindingUtil.setContentView(baseActivity, layoutId);
        } else if (baseFragment != null && baseActivity != null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        }
        onReady();
        return binding;
    }

    public View root() {
        if (binding != null) {
            return binding.getRoot();
        }
        return null;
    }

    protected BaseVM model(Class<? extends BaseVM> modelClass) {
        if (modelClass != null) {
            if (baseFragment != null) {
                baseVM = ViewModelProviders.of(baseFragment).get(modelClass);
                baseVM.getSimpleBean().observe(baseFragment, beanObserver);
            } else {
                baseVM = ViewModelProviders.of(baseActivity).get(modelClass);
                baseVM.getSimpleBean().observe(baseActivity, beanObserver);
            }


//            ViewModelStore some=new ViewModelStore();
//            new ViewModelProvider(some,new ViewModelProvider.AndroidViewModelFactory(baseActivity.getApplication()));
            //    mViewModel = new ViewModelProvider(
//                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
//            ).get(ImageViewModel.class);
        }

        return baseVM;
    }

    protected abstract void onReady();

    public void doRefresh(View view) {
        baseVM.doRefresh();
    }

    public void doLoadMore(View view) {
        baseVM.doLoadMore(view);
    }
    public void doLoadBean(View view) {
        baseVM.doLoadBean(view);
    }

    public void onFailure(View view) {

    }


    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
}
