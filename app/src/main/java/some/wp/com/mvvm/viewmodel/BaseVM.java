package some.wp.com.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import some.wp.com.mvvm.taskmodel.BaseBean;

public abstract class BaseVM extends AndroidViewModel {

    protected MutableLiveData<Boolean> isFresh=new MutableLiveData<>();
    protected MutableLiveData<Boolean> isLoadmore=new MutableLiveData<>();

    protected MutableLiveData<List<BaseBean>> freshBeans;
    protected MutableLiveData<List<BaseBean>> moreBeans;
    protected MutableLiveData<BaseBean> simpleBean;
    private static Object object = new Object();

    public BaseVM(@NonNull Application application) {
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
    public abstract void doLoadBean(Object... objects) ;

    public abstract void doRefresh(Object... objects);

    public abstract void doLoadMore(Object... objects);
}
