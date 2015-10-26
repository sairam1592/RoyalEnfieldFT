package com.example.admin.royalenfield.History;

/**
 * Created by Admin on 10/21/2015.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.royalenfield.R;


/**
 * @author mwho
 *
 */
public class Tab2Fragment extends Fragment {
    /** (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */

    TextView txtMsg;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_frag_layout, container, false);
            txtMsg=(TextView) v.findViewById(R.id.textView_tabMsg);
            txtMsg.setText("In 1909 Royal Enfield surprised the motorcycling world by introducing a small Motorcycle with a 2 ¼ HP V twin Motosacoche engine of Swiss origin. In 1911 the next model was powered by a 2 ¾ HP engine and boasted of the well known Enfield 2-speed gear. In 1912 came the JAP 6 HP 770 CC V twin with a sidecar combination. It was this motorcycle which made Enfield a household name. 1914 saw the 3 HP motorcycles this time with Enfield’s own engine which now had the standardised Enfield paint scheme of black enamelled parts and green tank with gold trim.");

        return v;
    }
}
