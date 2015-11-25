package com.reft.admin.royalenfield.fragments;

/**
 * Created by arun on 10/20/2015.
 */

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
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

import org.w3c.dom.Text;


public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    Handler handler;
    Runnable runnable;
    ViewFlipper flipper1, flipper2;
    TypedArray bikeApp;
    ScreenResolution screenRes;
    RelativeLayout.LayoutParams params1, params2;
    String[] message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        screenRes = deviceDimensions();
        //Log.i("Height width", "Width:" + screenRes.width + "Height:" + screenRes.height);
        params1 = new RelativeLayout.LayoutParams(screenRes.width, screenRes.height / 2);
        params2 = new RelativeLayout.LayoutParams(screenRes.width, 500);
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        message = getResources().getStringArray(R.array.textMessage);
        flipper1 = (ViewFlipper) rootView.findViewById(R.id.flipper1);
        flipper2 = (ViewFlipper) rootView.findViewById(R.id.flipper2);
        bikeApp = getResources().obtainTypedArray(R.array.AppOpen);
        System.out.println(bikeApp.length());
        timer1();
        timer2();
        runnable = new Runnable() {

            public void run() {
                handler.postDelayed(runnable, 4000);
                flipper1.showNext();
                flipper2.showNext();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);

        return rootView;
    }


    public void timer2() {
        for (int i = 0; i < message.length; i++) {
            setFlipperText(message[i]);
        }
    }

    public void setFlipperText(String abtMsg) {
        TextView msg = new TextView(getActivity());
        params2.addRule(RelativeLayout.CENTER_IN_PARENT);
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        msg.setLayoutParams(params2);
        msg.setTextSize(18);
        msg.setTextColor(Color.BLUE);
        msg.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
        msg.setText(abtMsg);
        flipper2.addView(msg);
    }

    public void timer1() {
        for (int i = 0; i < bikeApp.length(); i++) {
            setFlipperImage(bikeApp.getResourceId(i, -1));
        }
    }

    private void setFlipperImage(int res) {
        //Log.i("Set Filpper Called", res + "");
        ImageView image = new ImageView(getActivity());
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image.setScaleType(ImageView.ScaleType.CENTER);
        image.setLayoutParams(params1);
        image.setBackgroundResource(res);
        flipper1.addView(image);
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


