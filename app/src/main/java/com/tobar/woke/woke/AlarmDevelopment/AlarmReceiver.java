package com.tobar.woke.woke.AlarmDevelopment;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.view.Window;

import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.MQTT.MqttClientConnector;
//import com.tobar.woke.woke.MQTT.MqttPubClientTestApp;
import com.tobar.woke.woke.R;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.logging.Logger;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    //private static MqttPubClientTestApp SubApp;
    private MqttClientConnector mqttClient;
    private static final Logger logger = Logger.getLogger(AlarmReceiver.class.getName());

    private String _protocol = "tcp";
    //    private String _host = "test.mosquitto.org";
    private String _host = "tcp://iot.eclipse.org:1883";
    //private int _port = 1883;

    private String _authToken;
    private String _clientID = "jSub";
    private String _brokerAddr;
    private String _pemFileName;
    private boolean _isSecureConn = false;
    private MqttClient mqttClient1;
    private static String msg;
    private String payload;
    private String topic1 = "matsensor";
    private String topic2 = "useractivity";
    private int qos = 1;
    private MqttClientConnector myMqtt = new MqttClientConnector(this);
    IMqttClient iMqttClientSub;
    MemoryPersistence persistence = new MemoryPersistence();
    Ringtone ringtone;
    NotificationHelper notificationHelper;

    public NotificationHelper getNotificationHelper() {
        return notificationHelper;
    }

    public MqttClientConnector getMqttClient() {
        return mqttClient;
    }

    public void start(String topicName)
    {
        mqttClient = new MqttClientConnector(this);
        Boolean data = true;
        String sensor = MqttClientConnector.dataToJson(data);

        logger.info("SensorData after converting into Json\n");


        mqttClient.connect();
//        mqttClient.publishMessage(topicName, 1 , sensor.getBytes());
        String a = "on";
        String b = "undone";

        byte[] payload = a.getBytes();
        byte[] payload1 = b.getBytes();

        mqttClient.publishMessage(topicName, 1 , payload);
        mqttClient.publishMessage("useractivity", 1 , payload1);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {


        try {
            iMqttClientSub = new MqttClient(_host, _clientID, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            iMqttClientSub.setCallback(myMqtt);
            iMqttClientSub.connect(connOpts);
            System.out.println("Connected");

            iMqttClientSub.subscribe(topic1, qos);

            iMqttClientSub.subscribe(topic2, qos);
            System.out.println("Subscribed");

        } catch (MqttException e1) {
            e1.printStackTrace();
        }



        //this will update the UI with message
        NewAlarmClockActivity inst = NewAlarmClockActivity.instance();
//        inst.setAlarmText("Alarm! Wake up! Wake up!");


        //SubApp = new MqttPubClientTestApp();
        String topic = "matsensor";
        String topic1 = "useractivity";
        try {
            //SubApp.start(topic);
            this.start(topic);
            //SubApp.start(topic1);



        }catch (Exception ex) {
            ex.printStackTrace();
        }


        System.out.println("ALARM RECIEVER REACHED");

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);



        




        //NOTIFICATION OPTION 1
        notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());


        Intent notifyIntent = new Intent(AlarmReceiver.class);
// Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this.getActivity(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentIntent(notifyPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.getActivity());
        notificationManager.notify(NOTIFICATION_ID, builder.build());





        //NOTIFICATION OPTION 2
//        Notification notification = new Notification.Builder(context)
//                .setContentTitle("ALARM ON").setContentText("You have set up an Alarm")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notificationManager.notify(0,notification);

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);






            if (mqttClient.getPayload().equals("off")) {


                vibrator.cancel();

                ringtone.stop();


                System.out.println("PAYLOAD IS OFFFFFF");





        }


//        /** Creating an Alert Dialog Window */
//        AlertFragment alert = new AlertFragment();
//
//        /** Opening the Alert Dialog Window. This will be opened when the alarm goes off */
//
//        alert.show(, "AlertDemo");
    }


    public void stopAlarm() {
        System.out.println("RINGTONE  -  STOP");
        ringtone.stop();
    }
}