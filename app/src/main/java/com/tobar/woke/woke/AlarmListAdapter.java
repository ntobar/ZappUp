package com.tobar.woke.woke;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.MyViewHolder> {
    private ArrayList<Alarm> mDataset;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView alarmTime;
        public Switch alarmState;
        public TextView numberSnoozes;
        public TextView snoozeInterval;


        public MyViewHolder(View itemView) {
            super(itemView);


            alarmTime = itemView.findViewById(R.id.alarm_time);
            alarmState = itemView.findViewById(R.id.alarm_state);


            numberSnoozes = itemView.findViewById(R.id.alarm_snoozes);
            snoozeInterval = itemView.findViewById(R.id.alarm_intervals);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlarmListAdapter(ArrayList<Alarm> myDataset) {
        this.mDataset = myDataset;
        System.out.println("MyDataSet in AlarmListAdapter:: " + mDataset);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlarmListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_alarms, parent, false);


        System.out.println("dataset in oncreateviewholder::== " + mDataset);


        View alarmView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_item, parent, false);
        MyViewHolder vh = new MyViewHolder(alarmView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        Alarm alarm = mDataset.get(position);

        String alarmTime = alarm.getAlarmTime();
        boolean alarmState = alarm.getAlarmState();
        int numberSnoozes = alarm.getNumberSnoozes();
        int snoozeInterval = alarm.getSnoozeInterval();


        System.out.println("MyDataSet in AlarmListAdapter in ONBINDVIEWHOLDER:: " + mDataset);


        // Set item views based on your views and data model
        TextView alarmText1 = holder.alarmTime;
        Switch alarmState1 = holder.alarmState;
        //alarmState.setChecked(alarm.getAlarmState());
        TextView snoozes1 = holder.numberSnoozes;
        TextView interval1 = holder.snoozeInterval;


        alarmText1.setText(alarmTime);
        alarmState1.setChecked(alarmState);
        snoozes1.setText(String.valueOf(numberSnoozes));
        interval1.setText(String.valueOf(snoozeInterval));





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        if(mDataset != null) {
            return mDataset.size();
        } else {
            return 0;
        }
    }
}
