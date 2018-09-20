package com.architecture.wplib.taskmodel;

import com.architecture.wplib.viewmodel.BaseVM;

public abstract class BaseModel {
    public abstract void freshData(final BaseVM baseVM);

    public abstract void loadMoreData(BaseVM baseVM);

    public abstract void loadBean(BaseVM baseVM);

}
