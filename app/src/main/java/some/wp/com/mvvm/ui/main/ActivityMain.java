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



        //getSharedPreferences( "",  MODE_MULTI_PROCESS);
        //https://blog.csdn.net/asjqkkkk/article/details/80714234
        //https://blog.csdn.net/u012400885/article/details/53505597/
//        https://blog.csdn.net/xiao_sier/article/details/78651414
//        https://repo.eclipse.org/content/repositories/paho-releases/org/eclipse/paho/
    }

    @Override
    protected void onDestroy() {
        viewImplMain.onDestroy();
        super.onDestroy();
    }
}
