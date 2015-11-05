package com.example.admin.royalenfield.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.royalenfield.DBOperations.DBHelper;
import com.example.admin.royalenfield.R;

/**
 * Created by arun on 11/2/2015.
 */
public class MyTripFragment extends Fragment {

    public MyTripFragment() {
    }

    private DBHelper mydb;
    Button plan;
    Button view;
    Intent i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mytrip, container, false);
        plan = (Button) rootView.findViewById(R.id.buttonPlanTrip);
        view = (Button) rootView.findViewById(R.id.buttonViewTrip);
        mydb = new DBHelper(getActivity());
        onViewClick(mydb);
        onPlanClick();
        return rootView;
    }

    public void onViewClick(final DBHelper mydb) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Cursor rs = mydb.getTravelData();
                System.out.println("Count is:" + rs.getCount());
                if (rs.getCount() > 0) {
                    i = new Intent(getActivity(), ViewTripDetails.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(),
                            "no data found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onPlanClick() {
        plan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                i = new Intent(getActivity(), PlanTripDetails.class);
                startActivity(i);
            }
        });
    }
}
