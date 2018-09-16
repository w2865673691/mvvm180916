package some.wp.com.mvvm.common.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import some.wp.com.mvvm.ToDoApplication;

@Singleton
@Component(modules = { ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<ToDoApplication> {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}