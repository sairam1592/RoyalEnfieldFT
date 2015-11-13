package com.reft.admin.royalenfield.History;

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

import com.reft.admin.royalenfield.R;


/**
 * @author mwho
 */
public class Tab3Fragment extends Fragment {
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
        iv.setImageResource(R.drawable.historythird);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("At the time of the outbreak of WW I Royal Enfield supplied consignments of their 6 HP sidecar Outfit motorcycles with Stretchers to the Crown. This same motorcycle also came with a Vickers machine Gun sidecar attachment which could also be turned skywards and used against low flying aircraft. Royal Enfield supplied large numbers of motorcycles to the British War Department and also won a motorcycle contract for the Imperial Russian Government.");
        return v;
    }

}