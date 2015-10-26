package com.example.admin.royalenfield.History;

/**
 * Created by Admin on 10/21/2015.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.royalenfield.R;


/**
 * @author mwho
 *
 */
public class Tab1Fragment extends Fragment {
    /** (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */

    TextView txtMsg;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
            txtMsg=(TextView) v.findViewById(R.id.textView_tabMsg);
            txtMsg.setText("The Enfield Cycle Company made motorcycles, bicycles, lawnmowers and stationary engines under the name Royal Enfield out of its works based at Redditch, Worcestershire. The legacy of weapons manufacture is reflected in the logo comprising the cannon, and the motto \"Made like a gun\". Use of the brand name Royal Enfield was licensed by the Crown in 1890.");

        return v;
    }
}
