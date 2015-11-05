package com.example.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;
import com.example.admin.royalenfield.misc.MyClientTask;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by arun on 11/2/2015.
 */
public class PlanTripDetails extends Activity {

    RelativeLayout rel1, rel2;
    EditText from, to;
    Button fetch;
    CheckBox tick;
    String url;
    Intent i;
    JSONObject jObj;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tofrom);
        rel1 = (RelativeLayout) findViewById(R.id.relativelayout2);
        rel2 = (RelativeLayout) findViewById(R.id.relativelayout3);
        from = (EditText) findViewById(R.id.FromEditText);
        to = (EditText) findViewById(R.id.ToEditText);
        tick = (CheckBox) findViewById(R.id.checkBox);
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
                    if (ConnectionCheck()) {
                        //  url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + from.getText().toString() + "&destination=" + to.getText().toString() + "&key=" + Constants.TAG_APIKEY + "";
                        url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + from.getText().toString().replace(" ", "%20") + "&destinations=" + to.getText().toString().replace(" ", "%20") + "&mode=driving&language=en-EN&key=" + Constants.TAG_APIKEY + "";
                        try {
                            jObj = new MyClientTask(url, PlanTripDetails.this).execute().get();
                            Toast.makeText(PlanTripDetails.this, "JSON OBJ returned is:" + jObj, Toast.LENGTH_LONG).show();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(PlanTripDetails.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
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

    public boolean ConnectionCheck() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = info.isConnected();

        info = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = info.isConnected();

        if (isWifiConn) {
            return true;
        } else if (isMobileConn) {
            return true;
        } else {
            return false;
        }
    }
}
