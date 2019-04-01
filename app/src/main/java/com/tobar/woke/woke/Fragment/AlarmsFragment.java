package com.tobar.woke.woke.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tobar.woke.woke.Alarm.NewAlarmClockActivity;
import com.tobar.woke.woke.R;

//implements View.OnClickListener


public class AlarmsFragment extends Fragment implements View.OnClickListener {





//    private boolean loadFragment(Fragment toLoad) {
//        if(toLoad != null) {
//
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, toLoad).commit();
//
//            return true;
//        }
//
//        return false;
//
//    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarms, container, false);
        Button addAlarm = (Button) view.findViewById(R.id.randomButton);
        addAlarm.setOnClickListener(this);
//        addAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                System.out.println("Inside onCLick on oncreateview");
//
//                Intent alarmIntent = new Intent(getActivity(), NewAlarmClockActivity.class);
//                startActivity(alarmIntent);
//
//            }
//        });
        //addAlarm.setOnClickListener(this);



        return view;
       // return inflater.inflate(R.layout.fragment_alarms, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.randomButton:
                Intent alarmIntent = new Intent(getActivity(), NewAlarmClockActivity.class);
                startActivity(alarmIntent);

                break;
            // ...
        }

    }



}
