package some.wp.com.mvvm.ui.main;

import android.os.Bundle;

import com.architecture.wplib.ui.BaseActivity;

import javax.inject.Inject;

import some.wp.com.mvvm.R;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplMain;

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
