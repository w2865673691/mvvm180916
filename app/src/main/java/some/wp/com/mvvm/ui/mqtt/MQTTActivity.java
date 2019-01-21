package some.wp.com.mvvm.ui.mqtt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import some.wp.com.mvvm.R;

public class MQTTActivity extends FragmentActivity implements IGetMessageCallBack {

    private TextView textView;
    private Button button;
    private MyServiceConnection serviceConnection;
    private MQTTService mqttService;
    private EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);
        info = (EditText) findViewById(R.id.info);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        serviceConnection = new MyServiceConnection();
        serviceConnection.setIGetMessageCallBack(MQTTActivity.this);

        Intent intent = new Intent(this, MQTTService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MQTTService.publish(info.getText().toString().trim());
            }
        });

//        getSupportFragmentManager().beginTransaction().commitNow();
    }

    @Override
    public void setMessage(String message) {
        textView.setText(message);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
