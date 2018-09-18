package some.wp.com.mvvm.taskmodel.models;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import some.wp.com.mvvm.taskmodel.BaseModel;

public class MainModel extends BaseModel{

    @Inject
    public MainModel() {
    }

    public void getData(){
        ObservableOnSubscribe<?> source=new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

            }
        };
        Observable.create(source);
    }
}
