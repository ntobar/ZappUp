package com.tobar.woke.woke;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//implements View.OnClickListener

public class NewAlarmClockActivity extends AppCompatActivity implements View.OnClickListener {
    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView updateText;
    Context context;
    String storeTime;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_alarm_clock);
//
//        this.context = this;
//
//        //Initialize alarm manager
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        //Initializing TimePicker
//        timePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
//
//        //Initialize Text Time Update
//        updateText = (TextView) findViewById(R.id.alarmIndicator);
//
//
//        //Create calendar instance
//        Calendar calendar = Calendar.getInstance();
//
//        //Initialize buttons
//
//        Switch startAlarm = (Switch) findViewById(R.id.alarm_on);
//        startAlarm.setOnClickListener(this);
//        Button stopAlarm = (Button) findViewById(R.id.alarm_off);
//        stopAlarm.setOnClickListener(this);
//
//    }
//
//
//
        /**
         * "Given a 24-hour format, convert it to 12-Hour AM/PM time"
         *
         * @param hours - the given 12-hour time as a String.
         * @return String representing the converted time in 24-hour format
         */
        public String timeConversion(int hours, int minutes) {
            /*
             * Write your code here.
             */

            //The new hour in 24-hour format
            //int newHours;

            String result = "";

            //Example 24:00

            if(hours == 12) {

                result = hours + ":" + minutes + " PM";


            } else if(hours == 24) {

                result = (hours - 12) + ":" + minutes + " AM";

            } else if(hours == 0) {

                result = 12 + ":" + minutes + " AM";

            } else if(hours > 12) {
                result = (hours - 12) + ":" + minutes + " PM";

            }  else if(hours < 12) {

                result = hours + ":" + minutes + " AM";

            }


            return result;

        }


    public String getStoreTime() {
        return storeTime;
    }
//
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alarm_on:
                System.out.println("hours: " + timePicker.getHour());


                this.storeTime = timeConversion(timePicker.getHour(), timePicker.getMinute());
                setAlarmText(storeTime);

                //Chaning udpate text box

//                setAlarmText("Alarm set for " + timeConversion(timePicker.getHour(), timePicker.getMinute()));
//
//                this.storeTime = timeConversion(timePicker.getHour(), timePicker.getMinute());


                break;

            case R.id.alarm_off:
                System.out.println("AlarmOFF button pressed");

                //Chaning udpate text box

                setAlarmText("Alarm Off!");
                storeTime = "";


                break;

        }
    }

    private void setAlarmText(String s) {

        updateText.setText(s);


    }

    //AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static NewAlarmClockActivity inst;
    private TextView alarmTextView;

    public static NewAlarmClockActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm_clock);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        //ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        Switch alarmToggle = (Switch) findViewById(R.id.alarm_on);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onToggleClicked(View view) {
//        if (((ToggleButton) view).isChecked()) {
        if (((Switch) view).isChecked()) {
            Log.d("MyActivity", "Alarm On at " + alarmTimePicker.getCurrentHour() + ": " + alarmTimePicker.getCurrentMinute());

            this.storeTime = timeConversion(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
            setAlarmText("Alarm Set for: " + storeTime);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());


            Intent myIntent = new Intent(NewAlarmClockActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(NewAlarmClockActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            //setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }

//    public void setAlarmText(String alarmText) {
//        alarmTextView.setText(alarmText);
//    }


}


