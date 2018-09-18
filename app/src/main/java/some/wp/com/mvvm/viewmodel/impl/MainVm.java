package some.wp.com.mvvm.viewmodel.impl;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import some.wp.com.mvvm.taskmodel.BaseBean;
import some.wp.com.mvvm.taskmodel.beans.NewsBean;
import some.wp.com.mvvm.taskmodel.beans.TabClass;
import some.wp.com.mvvm.taskmodel.models.MainModel;
import some.wp.com.mvvm.viewmodel.BaseVM;

public class MainVm extends BaseVM {
    @Inject
    public MainModel mainModel;

    public MainVm(@NonNull Application application) {
        super(application);
    }

    @Override
    public void loadSimpleBean(Object... objects) {
        super.loadSimpleBean(objects);

        TabClass tabClass = new TabClass();
        tabClass.setTabName("MainVm4" + "\n" + tabClass.hashCode());
        simpleBean.setValue(tabClass);
    }

    @Override
    public void onRefresh(Object... objects) {
        super.onRefresh(objects);
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537189415069&di=3be36525ed4a3a722ae14aa873298d88&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201411%2F07%2F20141107164334_2PWXw.jpeg";
        List<BaseBean> value = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsBean bean = new NewsBean();
            bean.setName("name" + i);
            bean.setDescription("和客户的开发活动开始" + i);
            bean.setGood(false);
            // bean.setUrl("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
            bean.setUrl(url);
            value.add(bean);
        }
        listBeans.setValue(value);
    }

    @Override
    public void onLoadMore(Object... objects) {
        super.onLoadMore(objects);
    }
}
