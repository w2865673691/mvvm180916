package some.wp.com.mvvm.view.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.Lazy;
import some.wp.com.mvvm.R;
import some.wp.com.mvvm.databinding.ActivityMainBinding;
import some.wp.com.mvvm.view.BaseView;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class ViewImplMain extends BaseView {

    MainVm mainVm;
    ActivityMainBinding mainBinding;
    private FragmentManager fragmentManager;

    @Inject
    Lazy<FragmentTab0> fragProvider0;
    @Inject
    Lazy<FragmentTab1> fragProvider1;
    @Inject
    Lazy<FragmentTab2> fragProvider2;

    @Inject
    public ViewImplMain() {
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            baseVM.loadSimpleBean();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(fragmentManager, fragProvider1.get(), R.id.fragContent);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(fragmentManager, fragProvider2.get(), R.id.fragContent);
                    return true;
            }
            return false;
        }
    };

    public void onReady() {
        mainVm = (MainVm) model(MainVm.class);
        mainBinding = (ActivityMainBinding) binding;
        fragmentManager = baseActivity.getSupportFragmentManager();

        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);

        baseVM.loadSimpleBean();
    }
}
