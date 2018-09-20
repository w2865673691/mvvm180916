package some.wp.com.mvvm.view.main;

import android.os.Bundle;

import javax.inject.Inject;

import some.wp.com.mvvm.R;
import com.architecture.wplib.view.BaseActivity;
import some.wp.com.mvvm.view.main.viewimpl.ViewImplMain;

public class ActivityMain extends BaseActivity {

    @Inject
    ViewImplMain viewImplMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        viewImplMain.activity(this).bind(R.layout.activity_main,null);
    }
}
