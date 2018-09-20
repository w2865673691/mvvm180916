package com.architecture.wplib.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.architecture.wplib.data.BaseBean;

import java.util.List;


public class BaseViewModel extends AndroidViewModel {


    protected BaseModel baseModel;
    protected MutableLiveData<Boolean> isFresh = new MutableLiveData<>();
    protected MutableLiveData<Boolean> isLoadmore = new MutableLiveData<>();

    protected MutableLiveData<List<BaseBean>> freshBeans;
    protected MutableLiveData<List<BaseBean>> moreBeans;
    protected MutableLiveData<BaseBean> simpleBean;
    private static Object object = new Object();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseBean> getSimpleBean() {
        if (simpleBean == null) {
            synchronized (object) {
                if (simpleBean == null) {
                    simpleBean = new MutableLiveData<BaseBean>();
                }
            }
        }
        return simpleBean;
    }

    public MutableLiveData<List<BaseBean>> getFreshBeans() {
        if (freshBeans == null) {
            synchronized (object) {
                if (freshBeans == null) {
                    freshBeans = new MutableLiveData<List<BaseBean>>();
                }
            }
        }
        return freshBeans;
    }

    public MutableLiveData<List<BaseBean>> getMoreBeans() {
        if (moreBeans == null) {
            synchronized (object) {
                if (moreBeans == null) {
                    moreBeans = new MutableLiveData<List<BaseBean>>();
                }
            }
        }
        return moreBeans;
    }

    //
    public void doLoadBean(Object... objects) {
        if (baseModel != null) {
            baseModel.loadBean(this);
        }
    }

    public void doRefresh(Object... objects) {
        if (baseModel != null) {
            baseModel.freshData(this);
        }
    }

    public void doLoadMore(Object... objects) {
        if (baseModel != null) {
            baseModel.loadMoreData(this);
        }
    }
    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }
}
