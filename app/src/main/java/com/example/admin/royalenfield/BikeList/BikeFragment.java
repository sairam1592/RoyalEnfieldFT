package com.example.admin.royalenfield.BikeList;

/**
 * Created by Admin on 10/20/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.royalenfield.R;

public class BikeFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";

    public static final BikeFragment newInstance(String message) {
        BikeFragment f = new BikeFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        String image = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.bike_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.textView_name);
        messageTextView.setText(message);
        ImageView img = (ImageView) v.findViewById(R.id.imageView_bike);
        // img.setImageResource();
        return v;
    }
}