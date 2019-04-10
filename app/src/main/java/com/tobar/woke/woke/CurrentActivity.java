package com.tobar.woke.woke;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tobar.woke.woke.Fragment.AlarmsFragment;
import com.tobar.woke.woke.Fragment.DevicesFragment;
import com.tobar.woke.woke.Fragment.HomeFragment;

import java.util.ArrayList;

public class CurrentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ArrayList<Alarm> ds;
    AlarmsFragment alarmsFragment;
    DevicesFragment devicesFragment;
    HomeFragment homeFragment;

    //Start of new stuff


    //End of new stuff

//    private TextView mTextMessage;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
////                    setContentView(R.layout.activity_alarm);
//                    return true;
//
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);


//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(this);


        //CHECK BUNDLE
        Bundle extras = getIntent().getExtras();

        System.out.println("NEW CUrREnt ACTiVITY created WANERFEAF++++++++++++");

        alarmsFragment = new AlarmsFragment();
        homeFragment = new HomeFragment();
        devicesFragment = new DevicesFragment();


        Fragment fragment;
        ds = new ArrayList<>();

        if(extras != null) {



            //AlarmsFragment alarmsFragment = new AlarmsFragment();

            String alarmTime = extras.getString("alarmTime");
            boolean alarmState = extras.getBoolean("alarmState");
            int alarmSnoozes = extras.getInt("alarmSnoozes");
            int alarmInterval = extras.getInt("alarmInterval");

            Alarm newAlarm = new Alarm(alarmTime, alarmState, alarmSnoozes, alarmInterval);
            System.out.println(alarmTime + " " + alarmState + " " + alarmSnoozes + " " + alarmInterval);

            ds.add(newAlarm);

            System.out.println("ds::-:: " + ds);
            alarmsFragment.getMyDataset().add(newAlarm);

            System.out.println("Dataset: " +  this.alarmsFragment.getMyDataset());



            //boolean ss = alarmsFragment.getMyDataset().add(newAlarm);





            loadFragment(this.alarmsFragment);


        } else {




            loadFragment(homeFragment);


        }






    }

    private boolean loadFragment(Fragment toLoad) {
        if(toLoad != null) {



            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, toLoad).commit();

            return true;
        }

        return false;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch(menuItem.getItemId()) {
            case R.id.navigation_home:
                //fragment = new HomeFragment();
                fragment = homeFragment;
                break;

            case R.id.navigation_dashboard:
                fragment =  alarmsFragment;
                AlarmsFragment s = (AlarmsFragment) fragment;
                System.out.println("dataset nav: " + s.getMyDataset());
//                ArrayList<Alarm> alarm = (AlarmsFragment) fragment.
                break;

            case R.id.navigation_notifications:
                //fragment = new DevicesFragment();
                fragment = devicesFragment;
                break;
        }



        return loadFragment(fragment);
    }

    public ArrayList<Alarm> getDs() {
        return ds;
    }

    public void setDs(ArrayList<Alarm> ds) {
        this.ds = ds;
    }
}
