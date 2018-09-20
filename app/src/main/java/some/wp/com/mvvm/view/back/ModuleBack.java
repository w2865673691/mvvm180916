package some.wp.com.mvvm.view.back;


import com.architecture.wplib.common.dagger.ActivityScoped;
import com.architecture.wplib.view.BaseView;

import dagger.Binds;
import dagger.Module;
import some.wp.com.mvvm.view.back.viewimpl.ViewImplServiceMain;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 */
@Module
public abstract class ModuleBack {

    @ActivityScoped
    @Binds
    abstract BaseView viewImplServiceMain(ViewImplServiceMain viewImplServiceMain);

}
