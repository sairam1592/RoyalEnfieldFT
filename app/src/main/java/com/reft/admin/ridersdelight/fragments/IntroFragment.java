package com.reft.admin.ridersdelight.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reft.admin.ridersdelight.R;

/**
 * Created by arun on 4/12/2016.
 */
public class IntroFragment extends Fragment {

    TextView introText;
    ImageView introImg;
    String text;
    int imgId;

    public IntroFragment() {
    }


    public IntroFragment(String text, int imgId) {
        this.text = text;
        this.imgId = imgId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        introText = (TextView) view.findViewById(R.id.textView_intro);
        introText.setText(text);
        introImg = (ImageView) view.findViewById(R.id.imageView_intro);
        introImg.setImageResource(imgId);
        return view;
    }
}
