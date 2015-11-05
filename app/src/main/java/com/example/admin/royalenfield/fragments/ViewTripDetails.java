package com.example.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.royalenfield.DBOperations.DBHelper;
import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;

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
        String[] from = {Constants.TAG_LABELORIGIN, Constants.TAG_LABELDEST, Constants.TAG_DIST, Constants.TAG_DUR};
        int[] to = {R.id.textView_from, R.id.textView_to, R.id.textView_distdur, R.id.textView_duration};
        SimpleAdapter adapter = new SimpleAdapter(this, details, R.layout.tab_frag_tofrom, from, to);
        lv.setAdapter(adapter);
    }

    public void listViewItemSelect() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
                Toast.makeText(ViewTripDetails.this, "Values are: origin" + map.get(Constants.TAG_ORIGIN) + "Dest:" + map.get(Constants.TAG_DEST) + "Dist" + map.get(Constants.TAG_DIST) + "Duration" + map.get(Constants.TAG_DUR), Toast.LENGTH_LONG).show();
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
            i = new Intent(ViewTripDetails.this, NavMainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
