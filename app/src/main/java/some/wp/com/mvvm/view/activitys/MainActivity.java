package some.wp.com.mvvm.view.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.WindowManager;

import some.wp.com.mvvm.R;
import some.wp.com.mvvm.databinding.ActivityMainBinding;
import some.wp.com.mvvm.view.BaseActivity;
import some.wp.com.mvvm.viewmodel.impl.MainVm;

public class MainActivity extends BaseActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    baseVM.loadSimpleBean();
                    return true;
                case R.id.navigation_dashboard:
                    baseVM.loadSimpleBean();
                    return true;
                case R.id.navigation_notifications:
                    baseVM.loadSimpleBean();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setBaseContent(R.layout.activity_main, MainVm.class);

        ActivityMainBinding mainBinding= (ActivityMainBinding) binding;
        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
