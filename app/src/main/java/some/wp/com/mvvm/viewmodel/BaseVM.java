package some.wp.com.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import some.wp.com.mvvm.taskmodel.BaseBean;

public class BaseVM extends AndroidViewModel {
    public boolean isFresh=false;
    public boolean isLoadmore=false;

    protected MutableLiveData<List<BaseBean>> listBeans;
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

    public MutableLiveData<List<BaseBean>> getListBeans() {
        if (listBeans == null) {
            synchronized (object) {
                if (listBeans == null) {
                    listBeans = new MutableLiveData<List<BaseBean>>();
                }
            }
        }
        return listBeans;
    }

    //
    public void loadSimpleBean(Object... objects) {
    }

    public void onRefresh(Object... objects) {

    }

    public void onLoadMore(Object... objects) {

    }
}
