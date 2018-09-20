package some.wp.com.mvvm.taskmodel.models;


import com.architecture.wplib.common.LogActs;
import com.architecture.wplib.taskmodel.BaseBean;
import com.architecture.wplib.taskmodel.BaseModel;
import com.architecture.wplib.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import some.wp.com.mvvm.taskmodel.beans.NewsBean;
import some.wp.com.mvvm.taskmodel.beans.TabBean;

public class MainModel extends BaseModel {

    public MainModel() {
    }

    public void freshData(final BaseVM baseVM) {
        LogActs.d("freshData-->" + Thread.currentThread());
        Observable.create(new ObservableOnSubscribe<BaseBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBean> emitter) throws Exception {
                emitter.onNext(new BaseBean());
                emitter.onComplete();
            }
        })
                .map(new Function<BaseBean, List<BaseBean>>() {
                    @Override
                    public List<BaseBean> apply(BaseBean baseBean) throws Exception {
                        LogActs.d("map1-->" + Thread.currentThread());
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
                        return value;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BaseBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogActs.d("onSubscribe-->" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(List<BaseBean> baseBeans) {
                        LogActs.d("onNext-->" + Thread.currentThread());
                        baseVM.getFreshBeans().setValue(baseBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogActs.d("onError-->" + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        LogActs.d("onComplete-->" + Thread.currentThread());
                    }
                });

    }

    public void loadMoreData(BaseVM baseVM) {

    }

    public void loadBean(BaseVM baseVM) {
        TabBean tabClass = new TabBean();
        tabClass.setTabName("inject2" + ":" + tabClass.hashCode());
        baseVM.getSimpleBean().setValue(tabClass);
    }
}
