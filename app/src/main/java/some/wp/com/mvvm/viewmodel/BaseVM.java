package some.wp.com.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.taskmodel.beans.TabClass;

public class BaseVM extends AndroidViewModel {
    protected MutableLiveData<List<BaseBean>> listBeans;
    protected MutableLiveData<BaseBean> simpleBean;

    public BaseVM(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseBean> getSimpleBean() {
        if (simpleBean == null) {
            simpleBean = new MutableLiveData<BaseBean>();
        }
        return simpleBean;
    }

    public MutableLiveData<List<BaseBean>> getListBeans() {
        if (listBeans == null) {
            listBeans = new MutableLiveData<List<BaseBean>>();
        }
        return listBeans;
    }

    public void loadSimpleBean(){
    }

    public void loadListBeans(){

    }


}
