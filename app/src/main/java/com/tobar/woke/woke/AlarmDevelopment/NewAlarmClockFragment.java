package com.tobar.woke.woke.AlarmDevelopment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.Fragment.AlarmsFragment;
import com.tobar.woke.woke.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.content.Context.ALARM_SERVICE;

//implements View.OnClickListener

/**
 * This Class represents an Alarm Clock and manages everything related to it:
 * - Setting an Alarm and its Snoozes
 * - Adding the Alarm
 */
public class NewAlarmClockFragment extends Fragment implements View.OnClickListener, TextWatcher {
    AlarmManager alarmManager;
    //TimePicker timePicker;
    TextView updateText;
    Context context;
    String storeTime;
    boolean alarmState;
    int nSnoozes;
    int snoozeInterval;
    EditText nSnoozesText;
    EditText snoozeIntText;
    Switch alarmToggle;

//    AlarmReceiver ar;

    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static NewAlarmClockFragment inst;
    private TextView alarmTextView;


    private void cancelAlarm() {
        if (alarmManager!= null) {
            alarmManager.cancel(pendingIntent);
        }
    }




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

        String minutesS = Integer.toString(minutes);

        if(minutes < 10) {
            minutesS = "0" + minutes;
        }


        if(hours == 12) {

            result = hours + ":" + minutesS + " PM";


        } else if(hours == 24) {

            result = (hours - 12) + ":" + minutesS + " AM";

        } else if(hours == 0) {

            result = 12 + ":" + minutesS + " AM";

        } else if(hours > 12) {
            result = (hours - 12) + ":" + minutesS + " PM";

        }  else if(hours < 12) {

            result = hours + ":" + minutesS + " AM";

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

                System.out.println("Reached switch button");

                System.out.println("currHour: " + alarmTimePicker.getCurrentHour() + " currMinute: " + alarmTimePicker.getCurrentMinute());


                storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
                setAlarmText(storeTime);

                this.alarmState = true;

                //Changing udpate text box

//                setAlarmText("Alarm set for " + timeConversion(timePicker.getHour(), timePicker.getMinute()));
//
//                this.storeTime = timeConversion(timePicker.getHour(), timePicker.getMinute());


                break;

//            case R.id.alarm_off:
//                System.out.println("AlarmOFF button pressed");
//
//                //Chaning udpate text box
//
//                setAlarmText("Alarm Off!");
//                storeTime = "";
//
//                this.alarmState = false;
//
//
//                break;

            case R.id.addAlarmID:

                System.out.println("reached ADD ALARM BUTTON");



                //TAKE INTO ACCOUNT EXCEPTIONS.  NEED to handle things seperately
                //TODO: Handle exceptions individually when creating an alarm


                //ADD TRY CATCH WHEN DONE



                //USE THIS WHEN DONE
//                this.alarmsFragment.getMyDataset().add(
//                        new Alarm(this.storeTime,true ,this.nSnoozes,this.snoozeInterval));


                //USING 0 for values of snoozes for testing

                //storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
//                this.alarmsFragment.getMyDataset().add(
//                        new Alarm(this.storeTime,true ,0,0));


                storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());


                //Gets the Snooze number and Snooze Interval
                this.nSnoozes = Integer.parseInt(this.nSnoozesText.getText().toString());
                this.snoozeInterval = Integer.parseInt(this.snoozeIntText.getText().toString());

                Alarm newAlarm = new Alarm(storeTime, alarmState, nSnoozes, snoozeInterval);




                //SETTING THE ALARM

                if (this.alarmToggle.isChecked()) {


                    storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
                    Log.d("MyActivity", "Alarm On at " + storeTime);

//


                    Toast.makeText(getActivity().getApplicationContext(), "Alarm added for " + storeTime, Toast.LENGTH_SHORT).show();

                    setAlarmText("Alarm Set for: " + storeTime);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

//            Intent intent = new Intent(this, Mote.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1253, intent, 0);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.cancel(pendingIntent);




                    //Class<AlarmReceiver> ar = AlarmReceiver.class;
                    Intent myIntent = new Intent(this.getActivity(), AlarmReceiver.class);

                    myIntent.putExtra("alarmTime", storeTime);



                    // NOTIFICATION CODE


                    //ENDS HERE




                    //Intent myIntent = new Intent(this.getActivity(), AlarmReceiver.class);

//                    Intent myIntent = new Intent(this.getActivity(), AlarmReceiverActivity.class);
                    pendingIntent = PendingIntent.getBroadcast(this.getActivity(), 0, myIntent, 0);

//                    /** This intent invokes the activity DemoActivity, which in turn opens the AlertDialog window */
//                    Intent i = new Intent("in.wptrafficanalyzer.servicealarmdemo.demoactivity");
//
//                    /** Creating a Pending Intent */
//                    PendingIntent operation = PendingIntent.getActivity(this.getActivity(), 0, i, 0);




                    System.out.println(calendar.getTimeInMillis() + "= TimeinMILLIS");
                    System.out.println("HOURS OF " + TimeUnit.MILLISECONDS.toHours(calendar.getTimeInMillis()));


                    //If User sets number of Snoozes and Snooze interval
                    if ((nSnoozes > 0) || (snoozeInterval > 0)) {
                        //Sets Repeating alarm with users desired amount of time in between snoozes
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                1000 * 60 * this.snoozeInterval, pendingIntent);

                        //Converting snooze Interval Minutes to MilliSeconds
                        long snoozeIntMS = TimeUnit.MINUTES.toMillis(this.snoozeInterval);




                        long FINAL_WAKE_TIME = calendar.getTimeInMillis() + (this.nSnoozes * snoozeIntMS);

                        System.out.println(TimeUnit.MILLISECONDS.toHours(FINAL_WAKE_TIME));




                    } else {


                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }

                }


                    //---------------------------------------------------------------------------------



//                Alarm newAlarm = new Alarm(storeTime, alarmState, nSnoozes, snoozeInterval);

                    CurrentActivity activity = (CurrentActivity) this.getActivity();

                    ArrayList<Alarm> dataSet = activity.getDs();


                    dataSet.add(newAlarm);

                    Fragment fragment = new AlarmsFragment();

                    loadFragment(fragment);

                    //OLD CODE STARTS Here
//
//                Intent alarmIntent = new Intent(this, CurrentActivity.class);
//
//                alarmIntent.putExtra("alarmTime", storeTime);
//                alarmIntent.putExtra("alarmState", alarmState);
//                alarmIntent.putExtra("alarmSnoozes", nSnoozes);
//                alarmIntent.putExtra("alarmInterval", snoozeInterval);
//
//                startActivity(alarmIntent);

                    // OLD CODE fINISHes here


//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.aaactivity, this.alarmsFragment);
//                transaction.commit();

//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.aaactivity, this.alarmsFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

//                transaction.replace(R.id.container, alarmsFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();


                    //oadFragment(this.alarmsFragment);
                break;








        }
    }


    private boolean loadFragment(Fragment toLoad) {
        if(toLoad != null) {

            //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, toLoad).commit();



//            getSupportFragmentManager().beginTransaction()
//                    .replace(this., toLoad).commit();

            return true;
        }

        return false;

    }


    /**
     * Changes the text that displays the current Alarm state: Off or Alarm set at Time xx:xx PM/AM.
     * @param s - The String to replace the current Text.
     */
    private void setAlarmText(String s) {

        updateText.setText(s);


    }

    //AlarmManager alarmManager;


    public static NewAlarmClockFragment instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new_alarm_clock, container, false);

        alarmTimePicker = (TimePicker) view.findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) view.findViewById(R.id.alarmText);
        //ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmToggle = (Switch) view.findViewById(R.id.alarm_on);
        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        snoozeIntText = view.findViewById(R.id.nSnoozesID);
        snoozeIntText.setText("0");
        snoozeIntText.addTextChangedListener(this);


        nSnoozesText = view.findViewById(R.id.snoozeIntID);
        nSnoozesText.setText("0");
        nSnoozesText.addTextChangedListener(this);


        Button addAlarmButton = (Button) view.findViewById(R.id.addAlarmID);
        addAlarmButton.setOnClickListener(this);
        alarmToggle.setOnClickListener(this);




        this.storeTime = "";
        this.alarmState = false;
        this.nSnoozes = 0;
        this.snoozeInterval = 0;

        //Initialize Text Time Update
        updateText = (TextView) view.findViewById(R.id.alarmIndicator);
        updateText.setVisibility(View.INVISIBLE);


        //if(ar.getMqttClient().getPayload() == )

            return view;
    }

    public void onToggleClicked(View view) {
//        if (((ToggleButton) view).isChecked()) {
        if (((Switch) view).isChecked()) {



            storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
            Log.d("MyActivity", "Alarm On at " + storeTime);

//


            Toast.makeText(getActivity().getApplicationContext(), "Alarm added for " + storeTime, Toast.LENGTH_SHORT).show();

            setAlarmText("Alarm Set for: " + storeTime);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

//            Intent intent = new Intent(this, Mote.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1253, intent, 0);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.cancel(pendingIntent);


            System.out.println("Time in MS" + calendar.getTimeInMillis());


            Intent myIntent = new Intent(this.getActivity(), AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this.getActivity(), 0, myIntent, 0);


            //Gets the Snooze number and Snooze Interval
            this.nSnoozes = Integer.parseInt(this.nSnoozesText.getText().toString());
            this.snoozeInterval = Integer.parseInt(this.snoozeIntText.getText().toString());

            System.out.println(calendar.getTimeInMillis() + "= TimeinMILLIS");


            //If User sets number of Snoozes and Snooze interval
            if((nSnoozes > 0) || (snoozeInterval > 0)) {
                //Sets Repeating alarm with users desired amount of time in between snoozes
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * this.snoozeInterval, pendingIntent);

                long FINAL_WAKE_TIME = calendar.getTimeInMillis() + (this.nSnoozes * (1000 * 60 * this.snoozeInterval));







            } else {


                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            }
        } else {
            alarmManager.cancel(pendingIntent); // CANCELS THE ALARM
            storeTime = timeConversion(alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
            Toast.makeText(getActivity().getApplicationContext(), "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            setAlarmText("Alarm Turned Off");
            Log.d("MyActivity", "Alarm Off");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


//        Calendar calendar = Calendar.getInstance();
//        this.nSnoozes = Integer.parseInt(this.nSnoozesText.getText().toString());
//        Intent intent = new Intent(this.getActivity(), AlarmReceiver.class);
//        PendingIntent alarmIntent = PendingIntent.getBroadcast(this.getActivity(), 0, intent, 0);
//
//        //Sets Repeating alarm with users desired amount of time in between snoozes
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 * this.nSnoozes, alarmIntent);

        System.out.println("Snoozes set to: " + this.nSnoozes);

    }

//    public void setAlarmText(String alarmText) {
//        alarmTextView.setText(alarmText);
//    }


    public Switch getAlarmToggle() {
        return alarmToggle;
    }
}


