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

    public static final BikeFragment newInstance(String message, int imageId) {
        BikeFragment f = new BikeFragment();
        Bundle bdl = new Bundle();
        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putInt(EXTRA_IMAGE, imageId);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        int id = getArguments().getInt(EXTRA_IMAGE);
        View v = inflater.inflate(R.layout.bike_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.textView_name);
        messageTextView.setText(message);
        ImageView img = (ImageView) v.findViewById(R.id.imageView_bike);
        img.setImageResource(id);
        return v;
    }
}