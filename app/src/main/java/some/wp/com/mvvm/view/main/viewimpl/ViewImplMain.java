package some.wp.com.mvvm.view.main.viewimpl;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.architecture.wplib.view.BaseView;

import javax.inject.Inject;

import dagger.Lazy;
import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.R;
import some.wp.com.mvvm.databinding.ActivityMainBinding;
import some.wp.com.mvvm.view.main.fragements.FragmentTab0;
import some.wp.com.mvvm.view.main.fragements.FragmentTab1;
import some.wp.com.mvvm.view.main.fragements.FragmentTab2;
import some.wp.com.mvvm.view.back.ServiceMain;
import some.wp.com.mvvm.viewmodel.MainVm;

public class ViewImplMain extends BaseView {

  //  MainVm mainVm;
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
        brID = BR.simpleBean;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean result=false;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);
                    startService(ServiceMain.class);
                    result=  true;
                case R.id.navigation_dashboard:
                    replaceFragment(fragmentManager, fragProvider1.get(), R.id.fragContent);
                    startService(ServiceMain.class);
                    result=  true;
                case R.id.navigation_notifications:
                    replaceFragment(fragmentManager, fragProvider2.get(), R.id.fragContent);
                    startService(ServiceMain.class);
                    result= true;
            }
            doLoadBean(null);
            return result;
        }
    };

    public void onReady() {
        baseVM = (MainVm) model(MainVm.class);
       // baseVM = model(new MainModel());
        mainBinding = (ActivityMainBinding) binding;
        fragmentManager = baseActivity.getSupportFragmentManager();

        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);

        doLoadBean(null);
    }
}
