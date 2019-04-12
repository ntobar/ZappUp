package com.tobar.woke.woke.Fragment;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tobar.woke.woke.R;


import com.tobar.woke.woke.Alarm;
import com.tobar.woke.woke.AlarmDevelopment.NewAlarmClockActivity;
import com.tobar.woke.woke.AlarmDevelopment.NewAlarmClockFragment;
import com.tobar.woke.woke.AlarmListAdapter;
import com.tobar.woke.woke.CurrentActivity;

import com.tobar.woke.woke.RecyclerView.MyAdapter;
import com.tobar.woke.woke.RecyclerView.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;


public class AlarmsFragment extends Fragment implements View.OnClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{
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





    //TODO: FIX WHEN ADDING TIME THATS 00 it only adds one 0

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarms, container, false);

        ImageView addIcon = (ImageView) view.findViewById(R.id.addIconID);
        addIcon.setOnClickListener(this);




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





        final CurrentActivity myActivity = (CurrentActivity) getActivity();
        myDataset = myActivity.getDs();

        //myDataset = new ArrayList<>();


        // specify an adapter (see also next example)
        //mAdapter = new AlarmListAdapter(myDataset);
//        mAdapter = new MyAdapter(this.getActivity(), listItems);
        mAdapter = new MyAdapter(this.getActivity(), myDataset);

        recyclerView.setAdapter(mAdapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);



//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
//                0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//                System.out.println("Deleting alarm at int: " + viewHolder.getAdapterPosition() + "from list of size: " + myDataset.size());
//
//                Alarm toDelete = myDataset.get(viewHolder.getAdapterPosition());
//                int deleteIndex = viewHolder.getAdapterPosition();
//                mAdapter.
//
//
//
//
//
//                myDataset.remove(myDataset.get(viewHolder.getAdapterPosition()));
//                myActivity.getDs().remove(myActivity.getDs().get(viewHolder.getAdapterPosition()));
//                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//
//
//                Toast.makeText(getActivity(), "Alarm Deleted", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//            }
//        }).attachToRecyclerView(recyclerView);


        return view;
       // return inflater.inflate(R.layout.fragment_alarms, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.addIconID:

                System.out.println("In Alarms fragment on Click IMAGE VIEW button");
                Fragment fragment1 = new NewAlarmClockFragment();

                loadFragment(fragment1);

                break;

//            case R.id.removeAlarmID:
//
//                System.out.println("Remove alarm Button");
//                Toast.makeText(getContext(), "Select an Alarm to Delete it", Toast.LENGTH_LONG).show();
//
//
//                for(int i = 0; i < myDataset.size(); i++) {
//
//
//
//
//
//
//                }
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


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof MyAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
            String name = myDataset.get(viewHolder.getAdapterPosition()).getAlarmTime();

            // backup of removed item for undo purpose
            final Alarm deletedItem = myDataset.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view

            MyAdapter adapter = (MyAdapter) mAdapter;


            adapter.removeItem(viewHolder.getAdapterPosition());

//            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(viewHolder, name + " removed from Alarms!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    // undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedItem, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();
        }

    }
}
