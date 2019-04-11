package com.tobar.woke.woke.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.CurrentActivity;
import com.tobar.woke.woke.R;
import com.tobar.woke.woke.RecyclerView.MyAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Alarm> myDataset;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);


        recyclerView = (RecyclerView) view.findViewById(R.id.currentAlarmsRecycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        CurrentActivity myActivity = (CurrentActivity) getActivity();
        myDataset = myActivity.getDs();

        ArrayList<Alarm> newDs = new ArrayList<>();

        for(int i = 0; i < myDataset.size(); i++) {
            if(myDataset.get(i).getAlarmState()) {
                newDs.add(myDataset.get(i));
            }
        }

//        mAdapter = new MyAdapter(this.getActivity(), myDataset);
        mAdapter = new MyAdapter(this.getActivity(), newDs);
        recyclerView.setAdapter(mAdapter);





     return view;
    }
}
