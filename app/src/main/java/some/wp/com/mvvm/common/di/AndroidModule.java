package some.wp.com.mvvm.common.di;

import com.architecture.wplib.common.di.ActivityScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import some.wp.com.mvvm.ui.back.ServiceMain;
import some.wp.com.mvvm.ui.main.ActivityMain;
import some.wp.com.mvvm.ui.main.ModuleMain;

@Module
public abstract class AndroidModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = ModuleMain.class)
    abstract ActivityMain activityMain();


    @ActivityScoped
    @ContributesAndroidInjector
    abstract ServiceMain serviceMain();
}