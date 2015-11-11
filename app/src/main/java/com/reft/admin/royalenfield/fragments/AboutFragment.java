package com.reft.admin.royalenfield.fragments;

/**
 * Created by arun on 10/20/2015.
 */

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.reft.admin.royalenfield.R;


public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    Handler handler;
    Runnable runnable;
    TextView about;
    ViewFlipper flipper;
    TypedArray bikeApp;
    ScreenResolution screenRes;
    RelativeLayout.LayoutParams params;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        screenRes = deviceDimensions();
        //Log.i("Height width", "Width:" + screenRes.width + "Height:" + screenRes.height);
        params = new RelativeLayout.LayoutParams(screenRes.width, screenRes.height / 2);

        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        about = (TextView) rootView.findViewById(R.id.textView_abt);
        flipper = (ViewFlipper) rootView.findViewById(R.id.flipper1);
        bikeApp = getResources().obtainTypedArray(R.array.AppOpen);
        System.out.println(bikeApp.length());
        timer1();
        runnable = new Runnable() {

            public void run() {
                handler.postDelayed(runnable, 3000);
                flipper.showNext();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);

        setTextView();
        return rootView;
    }


    public void timer1() {
        for (int i = 0; i < bikeApp.length(); i++) {
            setFlipperImage(bikeApp.getResourceId(i, -1));
        }
    }

    private void setFlipperImage(int res) {
        //Log.i("Set Filpper Called", res + "");
        ImageView image = new ImageView(getActivity());
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image.setScaleType(ImageView.ScaleType.CENTER);
        image.setLayoutParams(params);
        image.setBackgroundResource(res);
        flipper.addView(image);
    }

    public void setTextView() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Royal Enfield Fuel Tracker app.\n");
        sb.append("\nThis app serves as the heartbeat for all Royal Enfield lovers out there!\n");
        sb.append("\nPeople who wish to buy a Royal Enfield can view motorcycle specifications,pictures and history of RE.\n");
        sb.append("\nPeople who own a Royal Enfield can plan for trips,share trip plan with others.\n");
        sb.append("\nIf you run out of fuel, you can find the nearest gas station, also the current fuel price in the market.\n");
        sb.append("\nAs most Royal Enfield bikes come without a fuel indicator, the important feature in this app is to compute the amount of fuel required to travel.\n");
        sb.append("\nHappy Riding! Thump it up...");
        //sb.append("\n\nNote: This is not the official Android app of RoyalEnfield (Eicher Motors).\n");
        about.setText(sb.toString());
    }

    private class ScreenResolution {
        int width, height;

        public ScreenResolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }


    ScreenResolution deviceDimensions() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // getsize() is available from API 13
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return new ScreenResolution(size.x, size.y);
        } else {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            // getWidth() & getHeight() are depricated
            return new ScreenResolution(display.getWidth(), display.getHeight());
        }
    }
}


