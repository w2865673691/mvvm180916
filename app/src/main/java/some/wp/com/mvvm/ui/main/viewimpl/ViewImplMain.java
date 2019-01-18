package some.wp.com.mvvm.ui.main.viewimpl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.architecture.wplib.ui.BaseView;

import javax.inject.Inject;

import dagger.Lazy;
import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.R;
import some.wp.com.mvvm.databinding.ActivityMainBinding;
import some.wp.com.mvvm.model.MainViewModel;
import some.wp.com.mvvm.ui.back.ServiceMain;
import some.wp.com.mvvm.ui.main.SingleActivity;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab0;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab1;
import some.wp.com.mvvm.ui.main.fragements.FragmentTab2;
import some.wp.com.mvvm.ui.mqtt.MQTTService;

public class ViewImplMain extends BaseView {

    //  MainViewModel mainVm;
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
            boolean result = false;
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);
                    startService(ServiceMain.class);
                    result = true;
                }
                break;
                case R.id.navigation_dashboard: {
                    replaceFragment(fragmentManager, fragProvider1.get(), R.id.fragContent);
                    startService(ServiceMain.class);
                    result = true;
                }
                break;
                case R.id.navigation_notifications: {
//                    replaceFragment(fragmentManager, fragProvider2.get(), R.id.fragContent);
//                    startService(ServiceMain.class);
                    startActivity(SingleActivity.class);
                    result = true;
                }
                break;
            }
            doLoadBean(null);
            return result;
        }
    };

    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String action = intent.getAction();
                if(MQTTService.MQTT_MESSAGE.equals(action)){
                    mainBinding.message.setText(intent.getStringExtra(MQTTService.KEY_MQTT_MESSAGE));
                }
            }
        }
    };

    @Override
    public void onReady() {
        baseVM = (MainViewModel) viewModel(MainViewModel.class);
        // baseVM = viewModel(new MainModel());
        mainBinding = (ActivityMainBinding) binding;
        fragmentManager = baseActivity.getSupportFragmentManager();

        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(fragmentManager, fragProvider0.get(), R.id.fragContent);

        doLoadBean(null);

        IntentFilter filter=new IntentFilter();
        filter.addAction(MQTTService.MQTT_MESSAGE);
        registerReceiver(receiver,filter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
    }
}
