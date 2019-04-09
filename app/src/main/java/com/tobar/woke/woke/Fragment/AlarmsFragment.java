package com.tobar.woke.woke.Fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.AlarmDevelopment.NewAlarmClockActivity;
import com.tobar.woke.woke.AlarmListAdapter;
import com.tobar.woke.woke.R;

import java.util.ArrayList;


public class AlarmsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Alarm> myDataset = new ArrayList<>();


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


        recyclerView = (RecyclerView) view.findViewById(R.id.alarmRecyclerView);


        System.out.println("myDataSet on Createview==1: " + myDataset);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);


        //myDataset = new ArrayList<>();

        System.out.println("myDataSet on Createview==2: " + myDataset);

        // specify an adapter (see also next example)
        mAdapter = new AlarmListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);





        return view;
       // return inflater.inflate(R.layout.fragment_alarms, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.randomButton:
                //NewAlarmClockActivity alarmClockActivity = new NewAlarmClockActivity();
                //alarmClockActivity.setAlarmsFragment(this);
                Intent alarmIntent = new Intent(getActivity(), NewAlarmClockActivity.class);
                startActivity(alarmIntent);

                break;
            // ...
        }

    }


    public ArrayList<Alarm> getMyDataset() {
        return this.myDataset;
    }


}
