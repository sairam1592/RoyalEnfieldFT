package com.example.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.royalenfield.DBOperations.DBHelper;
import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arun on 11/6/2015.
 */
public class ViewAllActivity extends Activity {

    Intent i;
    String id, mapUrl;
    private DBHelper mydb;
    TextView from, to, distance, amount, litre, type_of_journey;
    Button view_in_map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        Intent i = getIntent();
        id = i.getStringExtra(Constants.TAG_ID);
        mydb = new DBHelper(this);
        from = (TextView) findViewById(R.id.FromValue);
        to = (TextView) findViewById(R.id.ToValue);
        distance = (TextView) findViewById(R.id.DistanceValue);
        amount = (TextView) findViewById(R.id.AmountValue);
        litre = (TextView) findViewById(R.id.LitreValue);
        type_of_journey = (TextView) findViewById(R.id.TypeOfJourneyValue);
        view_in_map = (Button) findViewById(R.id.buttonURL);
        fillLayoutFromDB(id, mydb);
        onViewMapClick();
        onViewMapClick();
        //Toast.makeText(ViewAllActivity.this, "ID passed is::" + id, Toast.LENGTH_LONG).show();
    }

    public void fillLayoutFromDB(String _id, final DBHelper mydb) {
        Cursor rs = mydb.getDataFromId(_id);
        if (rs.getCount() > 0) {
            rs.moveToFirst();
            mapUrl = rs.getString(rs.getColumnIndex(Constants.TAG_URL));
            from.setText(rs.getString(rs.getColumnIndex(Constants.TAG_ORIGIN)));
            to.setText(rs.getString(rs.getColumnIndex(Constants.TAG_DEST)));
            amount.setText("Rs. " + rs.getString(rs.getColumnIndex(Constants.TAG_AMOUNT)));
            litre.setText(rs.getString(rs.getColumnIndex(Constants.TAG_LITRE)) + " Ltr.");
            Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
            Matcher matcher = pattern.matcher(rs.getString(rs.getColumnIndex(Constants.TAG_DIST)));
            if (matcher.find()) {
                Log.i("VIEWALLACTIVITY", "MATCHER DISTANCE is:" + matcher.group());
            }
            int dis;
            if (matcher.group().toString().contains(".")) {
                dis = Integer.parseInt(matcher.group().toString().substring(0, matcher.group().indexOf(".")));
            } else {
                dis = Integer.parseInt(matcher.group().toString());
            }
            if (rs.getString(rs.getColumnIndex(Constants.TAG_RETURNTICK)).equalsIgnoreCase("true")) {
                distance.setText(dis * 2 + " Kms");
                type_of_journey.setText("Return (Two Way)");
            } else if (rs.getString(rs.getColumnIndex(Constants.TAG_RETURNTICK)).equalsIgnoreCase("false")) {
                type_of_journey.setText("One Way");
                distance.setText(dis + " Kms");
            }
        }
    }


    public void onViewMapClick() {
        view_in_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mapUrl));
                startActivity(intent);
            }
        });
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
            i = new Intent(ViewAllActivity.this, NavMainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
