package some.wp.com.mvvm.taskmodel;

import some.wp.com.mvvm.viewmodel.BaseVM;

public abstract class BaseModel {
    public abstract void freshData(final BaseVM baseVM);

    public abstract void loadMoreData(BaseVM baseVM);

    public abstract void loadBean(BaseVM baseVM);

}
