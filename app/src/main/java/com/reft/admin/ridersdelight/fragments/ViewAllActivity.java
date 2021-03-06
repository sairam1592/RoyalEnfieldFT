package com.reft.admin.ridersdelight.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.DBOperations.DBHelper;
import com.reft.admin.ridersdelight.misc.Constants;
import com.reft.admin.ridersdelight.misc.WebViewActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arun on 11/6/2015.
 */
public class ViewAllActivity extends Activity {

    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent i;
    String id, mapUrl, duration;
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
    }

    public void fillLayoutFromDB(String _id, final DBHelper mydb) {
        Cursor rs = mydb.getDataFromId(_id);
        if (rs.getCount() > 0) {
            rs.moveToFirst();
            mapUrl = rs.getString(rs.getColumnIndex(Constants.TAG_URL));
            duration = rs.getString(rs.getColumnIndex(Constants.TAG_DUR));
            from.setText(rs.getString(rs.getColumnIndex(Constants.TAG_ORIGIN)));
            to.setText(rs.getString(rs.getColumnIndex(Constants.TAG_DEST)));
            amount.setText("Rs. " + rs.getString(rs.getColumnIndex(Constants.TAG_AMOUNT)));
            litre.setText(rs.getString(rs.getColumnIndex(Constants.TAG_LITRE)) + " Ltr.");
            String withoutComma;
            Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
            Matcher matcher;
            if (rs.getString(rs.getColumnIndex(Constants.TAG_DIST)).contains(",")) {
                withoutComma = rs.getString(rs.getColumnIndex(Constants.TAG_DIST)).replace(",", "");
                matcher = pattern.matcher(withoutComma);
            } else {
                matcher = pattern.matcher(rs.getString(rs.getColumnIndex(Constants.TAG_DIST)));
            }
            if (matcher.find()) {}
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
        rs.close();
        mydb.close();
    }


    public void onViewMapClick() {
        view_in_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                /*onShowDialog();*/
                Intent intent = new Intent(ViewAllActivity.this, WebViewActivity.class);
                intent.putExtra(Constants.TAG_URL, mapUrl);
                startActivity(intent);
            }
        });
    }

    public void onShowDialog() {
        alert = new AlertDialog.Builder(this);
        dialog = alert.create();
        dialog.setMessage("View the map?");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Inside App",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(ViewAllActivity.this, WebViewActivity.class);
                        intent.putExtra(Constants.TAG_URL, mapUrl);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Outside App",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(mapUrl));
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    public void onShareClick() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hey there!!..\nPlease find the trip plan below \n");
        sb.append("From: " + from.getText().toString());
        sb.append("\nTo: " + to.getText().toString());
        sb.append("\nDistance: " + distance.getText().toString());
        sb.append("\nJourney Type: " + type_of_journey.getText().toString());
        sb.append("\nFuel Cost: " + amount.getText().toString());
        sb.append("\nFuel Quantity: " + litre.getText().toString());
        sb.append("\nDuration(one way): " + duration);
        sb.append("\nMap link: " + mapUrl.toString().replace(" ", "%20"));
        sb.append("\n\nLets go for a ride shall we...");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void openCalendar() {
        String desc = "Kindly set reminder for your road trip, from: " + from.getText().toString() + " ,to: " + to.getText().toString();
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, 0, 1, 7, 00);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Road Trip")
                .putExtra(CalendarContract.Events.DESCRIPTION, desc)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, to.getText().toString())
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewall_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_back:
                onBackPressed();
                break;
            case R.id.action_share:
                onShareClick();
                break;
            case R.id.action_remind:
                openCalendar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        finish();
        return;
    }
}
