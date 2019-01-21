package some.wp.com.mvvm.ui.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;

import com.architecture.wplib.common.LogActs;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTService extends Service {
    public static final String MQTT_MESSAGE = "com.bdstar.www.sendtocar.MQTT_MESSAGE";
    public static final String KEY_MQTT_MESSAGE = "KEY_MQTT_MESSAGE";
    private static MqttAndroidClient client;
    private MqttConnectOptions connectOptions;

    private String host = "tcp://10.0.2.2:61613";
    private String userName = "admin";
    private String passWord = "password";
    private static String carTopic = "VIA/STC/IHU";
    private String clientId = "androidClientId";//客户端标识
    private IGetMessageCallBack IGetMessageCallBack;

    @Override
    public void onCreate() {
        super.onCreate();
        LogActs.d("");
        init();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogActs.d("");
        return new CustomBinder();
    }

    @Override
    public void onDestroy() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        stopSelf();
        super.onDestroy();
    }

    public class CustomBinder extends Binder {
        public MQTTService getService() {
            return MQTTService.this;
        }
    }

    public void setIGetMessageCallBack(IGetMessageCallBack IGetMessageCallBack) {
        this.IGetMessageCallBack = IGetMessageCallBack;
    }


    public static void publish(String msg) {
        LogActs.d("msg:" + msg);
        Integer qos = 0;
        Boolean retained = false;
        try {
            if (client != null && client.isConnected()) {
                client.publish(carTopic, msg.getBytes(), qos.intValue(), retained.booleanValue());
            }
        } catch (Exception e) {
            LogActs.d(e.getMessage());
            e.printStackTrace();
        }
    }

    private void init() {
        boolean doConnect = true;
        try {
            // 服务器地址（协议+地址+端口号）
            String uri = host;
            client = new MqttAndroidClient(this, uri, clientId);
            client.setCallback(mqttCallback);

            connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            // 设置超时时间，单位：秒
            connectOptions.setConnectionTimeout(10);
            // 心跳包发送间隔，单位：秒
            connectOptions.setKeepAliveInterval(5);
            connectOptions.setUserName(userName);
            connectOptions.setPassword(passWord.toCharArray());     //将字符串转换为字符串数组

            // last will message
            String message = "{\"terminal_uid\":\"" + clientId + "\"}";
            Integer qos = 0;
            Boolean retained = false;
            connectOptions.setWill(carTopic, message.getBytes(), qos.intValue(), retained.booleanValue());
        } catch (Exception e) {
            LogActs.d(e.getMessage());
            doConnect = false;
            iMqttActionListener.onFailure(null, e);
        }

        if (doConnect) {
            connect();
        }
    }

    /**
     * 连接MQTT服务器
     */
    private void connect() {
        if (isNetNormal() && !client.isConnected()) {
            try {
                LogActs.d("");
                client.connect(connectOptions, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken iMqttToken) {
            LogActs.d("" + iMqttToken);
            try {
                client.subscribe(carTopic, 1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable throwable) {
            LogActs.d("" + throwable.getMessage());
            throwable.printStackTrace();
            //TODO 连接失败，重连
        }
    };

    // MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {

            String payload = new String(message.getPayload());
            if (IGetMessageCallBack != null) {
                IGetMessageCallBack.setMessage(payload);
            }
            String str2 = topic + "; qos:" + message.getQos() + "; retained:" + message.isRetained();

            LogActs.d("payload:" + payload + "; topic:" + str2);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {
            LogActs.d("");
        }

        @Override
        public void connectionLost(Throwable arg0) {
            LogActs.d(arg0.getMessage());
            arg0.printStackTrace();
            //TODO 失去连接，重连
        }
    };

    /**
     * 判断网络是否连接
     */
    private boolean isNetNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            String name = info.getTypeName();
            LogActs.d("Normal:" + name);
            return true;
        } else {
            LogActs.d("没有可用网络");
            return false;
        }
    }
}