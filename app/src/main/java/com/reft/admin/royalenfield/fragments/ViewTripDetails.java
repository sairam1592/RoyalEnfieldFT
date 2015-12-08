package com.reft.admin.royalenfield.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.DBOperations.DBHelper;
import com.reft.admin.royalenfield.misc.Constants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by arun on 11/2/2015.
 */
public class ViewTripDetails extends Activity {

    Intent i;
    ArrayList<HashMap<String, String>> details;
    private DBHelper mydb;
    ListView lv;
    AlertDialog.Builder alert;
    AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtrip);
        Intent i = getIntent();
        details = (ArrayList<HashMap<String, String>>) i.getSerializableExtra("FullList");
        lv = (ListView) findViewById(R.id.listview_view);

        mydb = new DBHelper(this);
        loadListView();
        listViewItemSelect();
    }

    public void loadListView() {
        String[] from = {Constants.TAG_LABELORIGIN, Constants.TAG_LABELDEST, Constants.TAG_DIST, Constants.TAG_DUR, Constants.TAG_ID};
        int[] to = {R.id.textView_from, R.id.textView_to, R.id.textView_distdur, R.id.textView_duration, R.id.textView_id};
        SimpleAdapter adapter = new SimpleAdapter(this, details, R.layout.tab_frag_tofrom, from, to);
        lv.setAdapter(adapter);
    }

    public void listViewItemSelect() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
                Intent itnt = new Intent(ViewTripDetails.this, ViewAllActivity.class);
                itnt.putExtra(Constants.TAG_ID, map.get(Constants.TAG_ID));
                startActivity(itnt);
            }
        });
    }


    public void onShowDialog() {
        alert = new AlertDialog.Builder(this);
        dialog = alert.create();
        dialog.setTitle("Alert");
        dialog.setMessage(Constants.TAG_ASKTOCLEAR);
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        int i = mydb.deleteTravelDetails();
                        if (i > 0) {
                            onBackPressed();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(ViewTripDetails.this, "Some error while deleting or no data found", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewtrip_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            onBackPressed();
            return true;
        }

        if (id == R.id.action_clear) {
            onShowDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        finish();
        return;
    }
}
