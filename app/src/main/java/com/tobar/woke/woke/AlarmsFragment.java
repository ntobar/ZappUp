package com.tobar.woke.woke;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

//implements View.OnClickListener


public class AlarmsFragment extends Fragment  {


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.randomButton:
//                System.out.println("ADD_ALARM_BUTTON_PRESSED");
//                Intent alarmIntent = new Intent(getActivity(), AlarmActivity.class);
//                startActivity(alarmIntent);
//                break;
//            // ...
//        }
//
//    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarms, container, false);
        Button addAlarm = (Button) view.findViewById(R.id.randomButton);
        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Inside onCLick on oncreateview");

                Intent alarmIntent = new Intent(getActivity(), AlarmActivity.class);
                startActivity(alarmIntent);

            }
        });
        //addAlarm.setOnClickListener(this);



        return view;
       // return inflater.inflate(R.layout.fragment_alarms, null);
    }













}
