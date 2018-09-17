package some.wp.com.mvvm.common.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import some.wp.com.mvvm.view.main.ActivityMain;
import some.wp.com.mvvm.view.main.ModuleMain;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = ModuleMain.class)
    abstract ActivityMain activityMain();

}