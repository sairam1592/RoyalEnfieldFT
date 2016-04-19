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

public class Tab5Fragment extends Fragment {

    TextView txtMsg;
    ImageView iv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        iv = (ImageView) v.findViewById(R.id.imageView_his);
        iv.setImageResource(R.drawable.historyfifth);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("RE UK continued manufacturing motorcycles and came out with some more innovative and powerful machines notably the RE Meteor, Constellation and finally the Interceptor 700, before being sold to Norton-Triumph-Villiers (NVT) in 1968. Production ceased in 1970 and the company was dissolved in 1971. Remaining tooling and equipment of the Redditch works were auctioned off. Meanwhile the Bullet 350 continued to be manufactured in India and by the 1980’s the motorcycles were even exported to Europe out of India. Even after the motorcycle manufacturing closed down the precision engineering division ran for some more time and even bicycles were produced until quite late.");
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(txtMsg);
        return v;
    }

}
