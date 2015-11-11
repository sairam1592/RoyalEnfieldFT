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
public class Tab4Fragment extends Fragment {
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
        iv.setImageResource(R.drawable.historyfourth);
        txtMsg = (TextView) v.findViewById(R.id.textView_tabMsg);
        txtMsg.setText("Royal Enfield motorcycles were being sold in India ever since 1949. In 1955, the Indian government started looking for a suitable motorcycle for its police forces and the army for patrolling duties on the country's border. The Bullet 350 was chosen as the most suitable bike for the job. The Indian government ordered 800 of these 350 cc motorcycles, an enormous order for that time. Thus In 1955, the Redditch Company partnered with Madras Motors in India to form what was called 'Enfield India' to assemble these 350 cc Bullet motorcycle under licence in erstwhile madras (Now called Chennai). As per their agreement Madras Motors owned the majority (over 50%) of shares in the company. In 1957 tooling equipment was also sold to Enfield India so that they could manufacture components and start full-fledged production. The Enfield Bullet dominated the Indian highways and with each passing year its popularity kept rising.");
        return v;
    }
}
