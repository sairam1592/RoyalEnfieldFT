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

public class Tab7Fragment extends Fragment {

    TextView txtMsg;
    ImageView iv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        iv = (ImageView) v.findViewById(R.id.imageView_his);
        iv.setImageResource(R.drawable.historyseventh);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("To manufacture quality bikes that are well known worldwide for their reliability and toughness state-of-the-art infrastructure is required, and that is just what RE has done at their Chennai manufacturing facility. An active in-house Research & Development wing is constantly at work to meet changing customer preferences and the challenges of Indian and International environment standards. When introducing a new product, this team undertakes all related planning which includes a rigorous customer contact program, design, concurrent engineering and testing processes. The Motorcycle Design team at RE is well equipped with high-end CAD/CAM workstations and the latest modelling software. Top-notch designers work continuously to come up with innovative bikes designs to meet the market’s expectations. Continuous rigorous testing of motorcycles and components is carried out in the Product Development testing lab to come up with more improvements in enhancing the customer experience.");
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(txtMsg);
        return v;
    }
}
