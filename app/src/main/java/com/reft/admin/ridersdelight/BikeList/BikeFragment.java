package com.reft.admin.ridersdelight.BikeList;

/**
 * Created by Admin on 10/20/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.reft.admin.ridersdelight.R;
import com.squareup.picasso.Picasso;

public class BikeFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_IMAGEURL = "EXTRA_IMAGEURL";
    Intent i;

    public static final BikeFragment newInstance(String message, String url, int img) {
        BikeFragment f = new BikeFragment();
        Bundle bdl = new Bundle();
        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putString(EXTRA_IMAGEURL, url);
        bdl.putInt(EXTRA_IMAGE, img);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        String message = getArguments().getString(EXTRA_MESSAGE);
        String imgUrl = getArguments().getString(EXTRA_IMAGEURL);
        int himImg = getArguments().getInt(EXTRA_IMAGE);
        View v = inflater.inflate(R.layout.bike_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.textView_name);
        messageTextView.setText(message);
        ImageView img = (ImageView) v.findViewById(R.id.imageView_bike);
        if (message.equalsIgnoreCase("Himalayan")) {
            img.setImageResource(himImg);
        } else {
            Picasso.with(getActivity())
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_placeholder) // optional
                    .error(R.drawable.ic_error_fallback)
                    .into(img);
        }
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .playOn(img);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.back_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_back) {
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}