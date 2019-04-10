package com.tobar.woke.woke.Fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.AlarmDevelopment.NewAlarmClockActivity;
import com.tobar.woke.woke.AlarmDevelopment.NewAlarmClockFragment;
import com.tobar.woke.woke.AlarmListAdapter;
import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.R;
import com.tobar.woke.woke.RecyclerView.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class AlarmsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Alarm> listItems;
    private ArrayList<Alarm> myDataset = new ArrayList<>();
    private ArrayList<Alarm> insideDataSet;


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

        System.out.println("NEW INSTANCE OF ALARMS FRAGMENT ALERT!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


        recyclerView = (RecyclerView) view.findViewById(R.id.alarmRecyclerView);



        System.out.println("myDataSet on Createview==1: " + myDataset);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        listItems = new ArrayList<>();



        for(int i = 0; i < 10; i++) {
            Alarm item = new Alarm("Alarm" + (i + 1), true, 0, 0);
            listItems.add(item);
        }





        CurrentActivity myActivity = (CurrentActivity) getActivity();
        myDataset = myActivity.getDs();

        //myDataset = new ArrayList<>();


        // specify an adapter (see also next example)
        //mAdapter = new AlarmListAdapter(myDataset);
//        mAdapter = new MyAdapter(this.getActivity(), listItems);
        mAdapter = new MyAdapter(this.getActivity(), myDataset);
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
                System.out.println("In Alarms fragment on Click random button");
                Fragment fragment = new NewAlarmClockFragment();

                loadFragment(fragment);


//                Intent alarmIntent = new Intent(getActivity(), NewAlarmClockActivity.class);
//                startActivity(alarmIntent);

                break;
            // ...
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


    public ArrayList<Alarm> getMyDataset() {
        return this.myDataset;
    }




}
