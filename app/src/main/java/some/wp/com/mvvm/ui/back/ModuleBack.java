package some.wp.com.mvvm.ui.back;


import com.architecture.wplib.common.di.ActivityScoped;
import com.architecture.wplib.ui.BaseView;

import dagger.Binds;
import dagger.Module;
import some.wp.com.mvvm.ui.back.viewimpl.ViewImplServiceMain;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 */
@Module
public abstract class ModuleBack {

    @ActivityScoped
    @Binds
    abstract BaseView viewImplServiceMain(ViewImplServiceMain viewImplServiceMain);

}
