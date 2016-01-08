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

import com.reft.admin.ridersdelight.R;


/**
 * @author mwho
 */
public class Tab1Fragment extends Fragment {
    /**
     * (non-Javadoc)
     *
     * @see Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */

    TextView txtMsg;
    ImageView iv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
        iv = (ImageView) v.findViewById(R.id.imageView_his);
        iv.setImageResource(R.drawable.historyfirst);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("The RE Cycle Company made motorcycles, bicycles, lawnmowers and stationary engines under the name RE out of its works based at Redditch, Worcestershire. The legacy of weapons manufacture is reflected in the logo comprising the cannon, and the motto \"Made like a gun\". Use of the brand name RE was licensed by the Crown in 1890.");
        return v;
    }
}
