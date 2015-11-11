package com.reft.admin.royalenfield.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.DBOperations.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

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
    HashMap<String, String> details;
    ArrayList<HashMap<String, String>> viewDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mytrip, container, false);
        plan = (Button) rootView.findViewById(R.id.buttonPlanTrip);
        view = (Button) rootView.findViewById(R.id.buttonViewTrip);
        mydb = new DBHelper(getActivity());

        viewDetails = new ArrayList<HashMap<String, String>>();
        onViewClick(mydb);
        onPlanClick();
        return rootView;
    }

    public void onViewClick(final DBHelper mydb) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Cursor rs = mydb.getTravelData();
                System.out.println("Count is:" + rs.getCount());
                System.out.println("Column count is:" + rs.getColumnCount());
                if (rs.getCount() > 0) {
                    // looping through all rows and adding to list
                    if (rs.moveToFirst()) {
                        while (true) {
                            details = new HashMap<String, String>();
                            for (int i = 0; i < rs.getColumnCount(); i++) { //changing i value from 1 to 0 -Arun-Nov 6
                                details.put(rs.getColumnName(i), rs.getString(i));
                            }
                            viewDetails.add(details);
                            if (rs.isLast()) break;
                            rs.moveToNext();
                        }
                        rs.close();
                    }

                    mydb.close();
                    i = new Intent(getActivity(), ViewTripDetails.class);
                    i.putExtra("FullList", viewDetails);
                    startActivity(i);
                    viewDetails.clear();
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
