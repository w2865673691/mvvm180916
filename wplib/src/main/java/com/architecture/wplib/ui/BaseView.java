package com.architecture.wplib.ui;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import com.architecture.wplib.data.BaseBean;
import com.architecture.wplib.model.BaseModel;
import com.architecture.wplib.model.BaseViewModel;

import static dagger.internal.Preconditions.checkNotNull;

public abstract class BaseView {
    protected Integer brID =null;
    protected LayoutInflater inflater;
    protected ViewDataBinding binding;
    protected BaseViewModel baseVM;
    protected BaseService service;
    protected BaseActivity baseActivity;
    protected BaseFragment baseFragment;
    protected Observer<BaseBean> beanObserver = new Observer<BaseBean>() {
        @Override
        public void onChanged(@Nullable BaseBean baseBean) {
            // TabClass tabClass = (TabClass) baseBean;
            //binding.setBean(tabClass);
            if (binding != null&& brID !=null) {
                binding.setVariable(brID, baseBean);
            }
        }
    };

    public BaseView service(BaseService service) {
        this.service = service;
        this.baseActivity = null;
        this.baseFragment = null;
        onReady();
        return this;
    }

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

    //
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

    //
    protected BaseViewModel viewModel(Class<? extends BaseViewModel> modelClass) {
        if (modelClass != null) {
            if (baseFragment != null) {
                baseVM = ViewModelProviders.of(baseFragment).get(modelClass);
                baseVM.getSimpleBean().observe(baseFragment, beanObserver);
            } else {
                baseVM = ViewModelProviders.of(baseActivity).get(modelClass);
                baseVM.getSimpleBean().observe(baseActivity, beanObserver);
            }
        }

        return baseVM;
    }

    protected BaseViewModel viewModel(BaseModel baseModel) {
        if (baseModel != null) {
            Application application = null;
            LifecycleOwner observerOwner = null;
            if (service != null) {
                application = service.getApplication();
                observerOwner = service;
            } else if (baseFragment != null) {
                application = baseFragment.getActivity().getApplication();
                observerOwner = baseFragment;
            } else if (baseActivity != null) {
                application = baseActivity.getApplication();
                observerOwner = baseActivity;
            }

            if (observerOwner != null) {
                baseVM = new BaseViewModel(application);
                baseVM.setBaseModel(baseModel);
                baseVM.getSimpleBean().observe(observerOwner, beanObserver);
            }
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

    public void onDestroy() {

    }

    public Context getContext() {
        Context context = null;
        if (service != null) {
            context = service.getApplicationContext();
        } else if (baseFragment != null) {
            context = baseFragment.getContext();
        } else if (baseActivity != null) {
            context = baseActivity;
        }
        return context;
    }




    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        checkNotNull(receiver);
        checkNotNull(filter);
        Context context = getContext();
        context.registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        checkNotNull(receiver);
        Context context = getContext();
        context.unregisterReceiver(receiver);

    }

    public void startService(@NonNull Class<?> cls) {
        checkNotNull(cls);
        Context context = getContext();
        Intent service = new Intent();
        service.setClass(context, cls);
        context.startService(service);
    }

    public void startActivity(@NonNull Class<?> cls) {
        checkNotNull(cls);
        Context context = getContext();
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    public void startActivity(@NonNull String pakName,String actName) {
        checkNotNull(pakName);
        checkNotNull(actName);
        Context context = getContext();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pakName,actName));
        context.startActivity(intent);
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }


//      mViewModel = new ViewModelProvider(
//                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
//            ).get(ImageViewModel.class);
}
