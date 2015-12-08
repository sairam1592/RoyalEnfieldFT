package com.reft.admin.royalenfield.misc;

/**
 * Created by Admin on 10/19/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.DBOperations.DBHelper;
import com.reft.admin.royalenfield.NavMainActivity;
import com.reft.admin.royalenfield.fragments.PersonalDetailsFragment;

public class SplashScreen extends Activity {

    Animation fadeIn;
    TextView reText1;
    private DBHelper mydb;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mydb = new DBHelper(this);
        animText();
        moveToNavActivity(mydb);
    }

    public void animText() {
        reText1 = (TextView) findViewById(R.id.textView_1);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Capture_it.ttf");
        reText1.setTypeface(face);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        reText1.setText("Enfield Throttler");
        reText1.startAnimation(fadeIn);
    }

    public void moveToNavActivity(final DBHelper mydb) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int num = mydb.numberOfRows("riderdetails");
                if (num >= 1) {
                    i = new Intent(SplashScreen.this, NavMainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    i = new Intent(SplashScreen.this, PersonalDetailsFragment.class);
                    startActivity(i);
                    finish();
                }
            }
        }, Constants.SPLASH_TIME_OUT);


    }

}
