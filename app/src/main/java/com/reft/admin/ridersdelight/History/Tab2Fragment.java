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
public class Tab2Fragment extends Fragment {
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
        iv.setImageResource(R.drawable.historysecond1);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("In 1909 RE surprised the motorcycling world by introducing a small Motorcycle with a 2 ¼ HP V twin Motosacoche engine of Swiss origin. In 1911 the next model was powered by a 2 ¾ HP engine and boasted of the well known RE 2-speed gear. In 1912 came the JAP 6 HP 770 CC V twin with a sidecar combination. It was this motorcycle which made RE a household name. 1914 saw the 3 HP motorcycles this time with RE’s own engine which now had the standardised RE paint scheme of black enamelled parts and green tank with gold trim.");
        return v;
    }
}
