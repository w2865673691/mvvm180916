package some.wp.com.mvvm.view.main;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import some.wp.com.mvvm.view.BaseService;
import some.wp.com.mvvm.view.main.viewimpl.ViewImplServiceMain;

public class ServiceMain extends BaseService {

    private ViewImplServiceMain viewImpl;

    @Override
    public void onCreate() {
        super.onCreate();

        viewImpl = new ViewImplServiceMain();
        viewImpl.service(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        viewImpl.doLoadBean(null);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
