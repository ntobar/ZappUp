package com.tobar.woke.woke.AlarmDevelopment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.Fragment.AlarmsFragment;
import com.tobar.woke.woke.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    AlarmReceiver alarmReceiver;
    Uri alarmUri;
    Ringtone ringtone;
    Context context;

    boolean onoff = true;

    public boolean isOnoff() {
        return onoff;
    }




    public AlarmReceiver getAlarmReceiver() {
        return alarmReceiver;
    }

    public void setAlarmReceiver(AlarmReceiver alarmReceiver) {
        this.alarmReceiver = alarmReceiver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        AlarmReceiver ar = new AlarmReceiver();



        //Ringtone ri = this.alarmReceiver.getRingtone();



        TextView time = findViewById(R.id.alarmTimeID);
        Button snoozeButton = findViewById(R.id.snoozeButton);
        snoozeButton.setOnClickListener(this);
        String alarmTime = "";



        Bundle extras = getIntent().getExtras();

        System.out.println("Got extras: " + extras);

        if(extras != null) {


            alarmTime = extras.getString("alarmTime");
            alarmUri = (Uri) extras.get("alarmUri");
           // context = (Context) extras.get("context");






        }



        time.setText(alarmTime);


    }




    private boolean loadFragment(Fragment toLoad) {
        if(toLoad != null) {

            //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();


            Fragment fragment = new AlarmsFragment();

            CurrentActivity a = new CurrentActivity();
           // a.getSupportFragmentManager()

            a.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, toLoad).commit();



//            getSupportFragmentManager().beginTransaction()
//                    .replace(this., toLoad).commit();

            return true;
        }

        return false;

    }


    @Override
    public void onClick(View view) {

        String a = "android.app.ReceiverRestrictedContext@7198cde";

        //Ringtone ri = this.alarmReceiver.getRingtone();

//        AlarmReceiver ar = new AlarmReceiver();
//
//        //this.alarmReceiver.stopAlarm();

        Ringtone r = AlarmReceiver.ringtone;

        Vibrator v = AlarmReceiver.vibrator;
//
//        ar.stopAlarm();
        //this.onoff = false;


//        ringtone = RingtoneManager
//                .getRingtone(getApplicationContext(), alarmUri);
//        System.out.println("RINGTONE CREATED");

        //alarmReceiver.stopAlarm();

        System.out.println("URI at NOTI= " + alarmUri);
        System.out.println(getApplicationContext() + " = Application Context at NotiActivity");

//        RingtoneManager
//                .getRingtone(getApplicationContext(), alarmUri).stop();

//        RingtoneManager ringMan = new RingtoneManager(getApplicationContext());
//        //ringMan.stopPreviousRingtone();
//
//        Intent stopIntent = new Intent(this, RingtonePlayingService.class);
//        this.stopService(stopIntent);

        r.stop();
        r = null;

        v.cancel();


        //ringMan.getRingtone(getApplicationContext()).stop();


        //ringtone.stop();









//
//                ringtone = RingtoneManager.getRingtone(alarmReceiver.getContext(), alarmUri);
//                ringtone.stop();
//                ringtone = null;

        //alarmReceiver.stopAlarm();
        Intent intent1 = new Intent(NotificationActivity.this, CurrentActivity.class);
       // AlarmsFragment af = new AlarmsFragment();

       // intent1.putExtra("stop", "stop");

        startActivity(intent1);
        //loadFragment(af);
        //Fragment fragment = new AlarmsFragment();

        //loadFragment(fragment);
        finish();

    }
}
