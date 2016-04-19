package com.reft.admin.ridersdelight.History;

/**
 * Created by Admin on 10/21/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.reft.admin.ridersdelight.R;

public class Tab6Fragment extends Fragment {

    TextView txtMsg;
    ImageView iv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        iv = (ImageView) v.findViewById(R.id.imageView_his);
        iv.setImageResource(R.drawable.historysixth);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("In 1990, RE India entered into a strategic alliance with the Eicher Group, and later merged with it in 1994. It was during this merger that the name RE India changed to RE. The Eicher Group is one of India's leading automotive groups with diversified interests in the manufacture of Tractors, Commercial Vehicles, Automotive Gears, Exports, Garments, Management Consultancy and Motorcycles. Since then, the Company has made considerable investments in modernizing its manufacturing technology and systems. In 1996, when the Government decided to impose stringent norms for emission RE was the first motorcycle manufacturer to comply, a tradition which has stuck on thus making emission norms being one of the most important factors the company focuses on.");
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(txtMsg);
        return v;
    }
}
