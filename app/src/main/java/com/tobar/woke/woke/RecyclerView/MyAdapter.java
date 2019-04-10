package com.tobar.woke.woke.RecyclerView;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<Alarm> listItems;


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
        viewHolder.numberSnoozes.setText(item.getNumberSnoozes() + " snoozes");
        viewHolder.snoozeInterval.setText(item.getSnoozeInterval() + "min Interval");
        viewHolder.alarmState.setChecked(item.getAlarmState());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alarmTime;
        public TextView numberSnoozes;
        public TextView snoozeInterval;
        public Switch alarmState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            alarmTime = (TextView) itemView.findViewById(R.id.alarm_time);
            numberSnoozes = (TextView) itemView.findViewById(R.id.alarm_snoozes);
            snoozeInterval = (TextView) itemView.findViewById(R.id.alarm_intervals);
            alarmState = (Switch) itemView.findViewById(R.id.alarm_state);



        }
    }
}
