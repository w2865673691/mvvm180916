package some.wp.com.mvvm.ui.mqtt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.architecture.wplib.common.LogActs;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogActs.d(intent.getAction());
        context.startService(new Intent(context, MQTTService.class));
    }
}
