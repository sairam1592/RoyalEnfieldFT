package com.example.admin.royalenfield.History;

/**
 * Created by Admin on 10/21/2015.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;


/**
 * @author mwho
 */
public class Tab6Fragment extends Fragment {
    /**
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */

    TextView txtMsg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("In 1990, Enfield India entered into a strategic alliance with the Eicher Group, and later merged with it in 1994. It was during this merger that the name Enfield India changed to Royal Enfield. The Eicher Group is one of India's leading automotive groups with diversified interests in the manufacture of Tractors, Commercial Vehicles, Automotive Gears, Exports, Garments, Management Consultancy and Motorcycles. Since then, the Company has made considerable investments in modernizing its manufacturing technology and systems. In 1996, when the Government decided to impose stringent norms for emission Royal Enfield was the first motorcycle manufacturer to comply, a tradition which has stuck on thus making emission norms being one of the most important factors the company focuses on.");
        return v;
    }
}
