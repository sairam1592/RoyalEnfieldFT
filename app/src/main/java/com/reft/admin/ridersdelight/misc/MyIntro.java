package com.reft.admin.ridersdelight.misc;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.reft.admin.ridersdelight.NavMainActivity;
import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.fragments.IntroFragment;


/**
 * Created by arun on 4/9/2016.
 */
public class MyIntro extends AppIntro {


    @Override
    public void init(Bundle savedInstanceState) {

        String[] introText = getResources().getStringArray(R.array.introText);
        TypedArray introImage = getResources().obtainTypedArray(R.array.IntroImage);
        for(int i=0;i<introText.length;i++){
            addSlide(new IntroFragment(introText[i],introImage.getResourceId(i, -1)));
        }
        setZoomAnimation();
        setBarColor(Color.parseColor("#68676A"));
        setSeparatorColor(Color.parseColor("#2196F3"));
    }


    private void loadMainActivity(){
        Intent intent = new Intent(this, NavMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), "Skipping intro.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }

}
