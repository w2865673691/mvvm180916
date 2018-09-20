package com.architecture.wplib.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ServiceLifecycleDispatcher;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import dagger.android.DaggerService;

public class BaseService extends DaggerService implements LifecycleOwner {

    private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);

    @CallSuper
    @Override
    public void onCreate() {
        mDispatcher.onServicePreSuperOnCreate();
        super.onCreate();
    }

    @CallSuper
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mDispatcher.onServicePreSuperOnBind();
        return null;
    }

    @SuppressWarnings("deprecation")
    @CallSuper
    @Override
    public void onStart(Intent intent, int startId) {
        mDispatcher.onServicePreSuperOnStart();
        super.onStart(intent, startId);
    }

    // this method is added only to annotate it with @CallSuper.
    // In usual service super.onStartCommand is no-op, but in LifecycleService
    // it results in mDispatcher.onServicePreSuperOnStart() call, because
    // super.onStartCommand calls onStart().
    @CallSuper
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mDispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    @Override
    public Lifecycle getLifecycle() {
        return mDispatcher.getLifecycle();
    }
}
