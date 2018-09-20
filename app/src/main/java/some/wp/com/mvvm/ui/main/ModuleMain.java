package some.wp.com.mvvm.ui.main;


import com.architecture.wplib.common.di.ActivityScoped;
import com.architecture.wplib.common.di.FragmentScoped;
import com.architecture.wplib.ui.BaseView;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab0;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab1;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab2;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplMain;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplTab0;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplTab1;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplTab2;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 */
@Module
public abstract class ModuleMain {

    @ActivityScoped
    @Binds
    abstract BaseView baseViewMain(ViewImplMain viewImplMain);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab0 fragmentTab0();

    @ActivityScoped
    @Binds
    abstract BaseView baseViewTab0(ViewImplTab0 viewImplTab0);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab1 fragmentTab1();

    @ActivityScoped
    @Binds
    abstract BaseView baseViewTab1(ViewImplTab1 viewImplTab1);


    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab2 fragmentTab2();

    @ActivityScoped
    @Binds
    abstract BaseView baseViewTab2(ViewImplTab2 viewImplTab2);
}
