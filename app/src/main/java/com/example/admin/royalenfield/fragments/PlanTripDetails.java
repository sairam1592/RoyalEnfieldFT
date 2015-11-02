package com.example.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;

/**
 * Created by arun on 11/2/2015.
 */
public class PlanTripDetails extends Activity {

    RelativeLayout rel1, rel2;
    EditText from, to;
    Button fetch;
    Intent i;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tofrom);
        rel1 = (RelativeLayout) findViewById(R.id.relativelayout2);
        rel2 = (RelativeLayout) findViewById(R.id.relativelayout3);
        from = (EditText) findViewById(R.id.FromEditText);
        to = (EditText) findViewById(R.id.ToEditText);
        fetch = (Button) findViewById(R.id.buttonGet);
        onFormLoad();
        onFetchClick();
    }


    public void onFormLoad() {
        rel1.setVisibility(RelativeLayout.GONE);
        rel2.setVisibility(RelativeLayout.GONE);
    }

    public void onFetchClick() {
        fetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isValidEditText(from) && isValidEditText(to)) {
                    Toast.makeText(PlanTripDetails.this,
                            "Not left empty", Toast.LENGTH_SHORT).show();
                    //JSON operation to be done here IMPORTANT
                }
            }
        });
    }

    public boolean isValidEditText(EditText view) {
        if (!view.getText().toString().equalsIgnoreCase("")) {
            return true;
        } else {
            view.setError("Field cannot be left empty");
            return false;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            i = new Intent(PlanTripDetails.this, NavMainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
