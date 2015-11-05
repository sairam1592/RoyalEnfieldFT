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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;


/**
 * @author mwho
 */
public class Tab7Fragment extends Fragment {
    /**
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */

    TextView txtMsg;
    ImageView iv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        iv = (ImageView) v.findViewById(R.id.imageView_his);
        iv.setImageResource(R.drawable.historyseventh);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("To manufacture quality bikes that are well known worldwide for their reliability and toughness state-of-the-art infrastructure is required, and that is just what Royal Enfield has done at their Chennai manufacturing facility. An active in-house Research & Development wing is constantly at work to meet changing customer preferences and the challenges of Indian and International environment standards. When introducing a new product, this team undertakes all related planning which includes a rigorous customer contact program, design, concurrent engineering and testing processes. The Motorcycle Design team at Royal Enfield is well equipped with high-end CAD/CAM workstations and the latest modelling software. Top-notch designers work continuously to come up with innovative bikes designs to meet the market’s expectations. Continuous rigorous testing of motorcycles and components is carried out in the Product Development testing lab to come up with more improvements in enhancing the customer experience.");
        return v;
    }
}
