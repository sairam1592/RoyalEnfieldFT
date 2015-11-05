package com.example.admin.royalenfield.History;

/**
 * Created by Admin on 10/21/2015.
 */

import android.content.Intent;
import android.media.Image;
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
public class Tab5Fragment extends Fragment {
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
        iv.setImageResource(R.drawable.historyfifth);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("Royal Enfield UK continued manufacturing motorcycles and came out with some more innovative and powerful machines notably the Royal Enfield Meteor, Constellation and finally the Interceptor 700, before being sold to Norton-Triumph-Villiers (NVT) in 1968. Production ceased in 1970 and the company was dissolved in 1971. Remaining tooling and equipment of the Redditch works were auctioned off. Meanwhile the Bullet 350 continued to be manufactured in India and by the 1980’s the motorcycles were even exported to Europe out of India. Even after the motorcycle manufacturing closed down the precision engineering division ran for some more time and even bicycles were produced until quite late.");
        return v;
    }

}
