package some.wp.com.mvvm.common.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link
 * AppComponent}.
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    //https://blog.csdn.net/u013519084/article/details/79103014
    @Binds
    abstract Context bindContext(Application application);
}

