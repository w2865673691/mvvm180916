package com.architecture.wplib.model;

import com.architecture.wplib.model.BaseViewModel;

public abstract class BaseModel {
    public abstract void freshData(final BaseViewModel baseVM);

    public abstract void loadMoreData(BaseViewModel baseVM);

    public abstract void loadBean(BaseViewModel baseVM);

}
