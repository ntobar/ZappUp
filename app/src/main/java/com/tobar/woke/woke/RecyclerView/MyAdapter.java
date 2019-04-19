package com.tobar.woke.woke.RecyclerView;

import android.app.AlarmManager;
import android.app.LauncherActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.AlarmDevelopment.AlarmReceiver;
import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.R;

import java.util.List;


/**
 * 
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Alarm> listItems;



    public interface OnItemClickListener {

        void onItemClick(int position);
        void onDeleteClick(int position);



    }


    public MyAdapter(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alarm_item, viewGroup, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        Alarm item = listItems.get(i);


        viewHolder.alarmTime.setText(item.getAlarmTime());
        if((item.getNumberSnoozes() == 0) || (item.getNumberSnoozes() > 1)) {
            viewHolder.numberSnoozes.setText(item.getNumberSnoozes() + " snoozes");
        } else if((item.getNumberSnoozes() == 1)) {
            viewHolder.numberSnoozes.setText(item.getNumberSnoozes() + " snooze");
        }
//        viewHolder.numberSnoozes.setText(item.getNumberSnoozes() + " snooze(s)");
        viewHolder.snoozeInterval.setText(item.getSnoozeInterval() + " m. Interval");
        viewHolder.alarmState.setChecked(item.getAlarmState());


//        if(!(item.isDelete())) {
//            viewHolder.alarmState.setVisibility(View.INVISIBLE);
//            viewHolder.deleteIcon.setVisibility(View.VISIBLE);
//
//        } else {
//            viewHolder.alarmState.setVisibility(View.VISIBLE);
//            viewHolder.deleteIcon.setVisibility(View.INVISIBLE);
//
//        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public List<Alarm> getListItems() {
        return listItems;
    }


    public void removeItem(int position) {
        listItems.remove(position);
        notifyItemRemoved(position);
    }

    public void setAlarms(List<Alarm> alarms) {
        this.listItems = alarms;
        notifyDataSetChanged();
    }

    public Alarm getAlarmAt(int position) {
        return listItems.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AdapterView.OnItemClickListener {
        public TextView alarmTime;
        public TextView numberSnoozes;
        public TextView snoozeInterval;
        public Switch alarmState;
        public ImageView deleteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);



            alarmTime = (TextView) itemView.findViewById(R.id.alarm_time);
            numberSnoozes = (TextView) itemView.findViewById(R.id.alarm_snoozes);
            snoozeInterval = (TextView) itemView.findViewById(R.id.alarm_intervals);
            alarmState = (Switch) itemView.findViewById(R.id.alarm_state);
            //deleteIcon = (ImageView) itemView.findViewById(R.id.removeAlarmID);

            alarmState.setOnClickListener(this);




        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(this != null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {

                    switch(view.getId()) {

                        case R.id.alarm_state:

                            System.out.println("Reached ONiTEMCLIKC BAD BUNNT");

                        Alarm toChange = listItems.get(position);

                        if(toChange.getAlarmState()) {
                            toChange.setAlarmState(false);

                            System.out.println("YAS");
                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                            Intent myIntent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                    context.getApplicationContext(), 0, myIntent, 0);

                            alarmManager.cancel(pendingIntent);



                        } else {
                            toChange.setAlarmState(true);
                        }

                        break;

                    }
                }

            }

        }



        @Override
        public void onClick(View view) {
            switch(view.getId()) {


                case R.id.alarm_state:

                    System.out.println("Reached ONiTEMCLIKC BAD BUNNT");

                    Alarm toChange = listItems.get(getAdapterPosition());

                    System.out.println("POSITION: " + getAdapterPosition());

                    if(toChange.getAlarmState()) {
                        toChange.setAlarmState(false);
                    } else {
                        toChange.setAlarmState(true);
                    }

                    break;


//                case R.id.removeAlarmID:
//
//
//                    for(int i = 0; i < listItems.size(); i++) {
//
//                        listItems.get(i).setDelete(true);
//
//                        //alarmState.setVisibility(View.INVISIBLE);
//
//
//                        break;


                   // }



//                int pos = getAdapterPosition();
//
//                Alarm alarm = listItems.get(pos);
//
//                Toast.makeText(context, alarm.getAlarmTime(), Toast.LENGTH_LONG).show();
//
//                break;

//                case R.id.alarm_state:
//
//                    if(listener !=)

            }

        }


    }
}
