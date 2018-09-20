package some.wp.com.mvvm.common.dagger;

import com.architecture.wplib.common.dagger.ActivityScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import some.wp.com.mvvm.view.back.ModuleBack;
import some.wp.com.mvvm.view.back.ServiceMain;
import some.wp.com.mvvm.view.main.ActivityMain;
import some.wp.com.mvvm.view.main.ModuleMain;

@Module
public abstract class ViewModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = ModuleMain.class)
    abstract ActivityMain activityMain();


    @ActivityScoped
    @ContributesAndroidInjector(modules = ModuleBack.class)
    abstract ServiceMain serviceMain();
}