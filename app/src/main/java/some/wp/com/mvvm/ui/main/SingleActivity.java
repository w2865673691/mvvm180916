package some.wp.com.mvvm.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import some.wp.com.mvvm.R;

//some.wp.com.mvvm.ui.main.SingleActivity
public class SingleActivity extends Activity {

    private TextView info;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        info = findViewById(R.id.info);
        info.setText("num-" + num);
        num++;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        info.setText("num-" + num);
        num++;
    }
}
