package com.example.admin.royalenfield.misc;

/**
 * Created by Admin on 10/19/2015.
 */

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;

public class SplashScreen extends Activity {

    Animation fadeIn;
    TextView reText1, reText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animText();
        moveToNavActivity();
    }

    public void animText() {
        reText1 = (TextView) findViewById(R.id.textView_1);

        reText2 = (TextView) findViewById(R.id.textView_2);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        reText1.setText("Royal Enfield");
        reText1.startAnimation(fadeIn);
        reText2.setText("Fuel Tracker");
        reText2.startAnimation(fadeIn);

    }

    public void moveToNavActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, NavMainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);


    }

}
