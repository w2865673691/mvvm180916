package some.wp.com.mvvm.ui.main;


import com.architecture.wplib.common.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab0;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab1;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab2;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 */
@Module
public abstract class ModuleMain {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab0 fragmentTab0();


    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab1 fragmentTab1();


    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentTab2 fragmentTab2();

}
