package com.reft.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.DBOperations.DBHelper;
import com.reft.admin.royalenfield.NavMainActivity;
import com.reft.admin.royalenfield.misc.Constants;
import com.reft.admin.royalenfield.misc.MyClientTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arun on 11/2/2015.
 */
public class PlanTripDetails extends Activity {

    RelativeLayout rel1;
    EditText from, to;
    Button fetch;
    CheckBox tick;
    String url;
    Intent i;
    JSONObject jObj;
    ArrayList<HashMap<String, String>> jsonList;
    ListView lv;
    private DBHelper mydb;

    private static final String TAG_DESTADDR = "destination_addresses";
    private static final String TAG_ORIGINADDR = "origin_addresses";
    private static final String TAG_ROWS = "rows";
    private static final String TAG_ELEMENTS = "elements";
    private static final String TAG_DISTANCE = "distance";
    private static final String TAG_STATUS = "status";
    private static final String TAG_DURATION = "duration";
    private static final String TAG_TEXT = "text";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tofrom);
        rel1 = (RelativeLayout) findViewById(R.id.relativelayout2);
        from = (EditText) findViewById(R.id.FromEditText);
        to = (EditText) findViewById(R.id.ToEditText);
        tick = (CheckBox) findViewById(R.id.checkBox);
        fetch = (Button) findViewById(R.id.buttonGet);
        lv = (ListView) findViewById(R.id.listview_info);
        mydb = new DBHelper(this);
        jsonList = new ArrayList<HashMap<String, String>>();
        onFormLoad();
        onFetchClick(mydb);
        listViewItemSelect();
    }


    public void onFormLoad() {
        rel1.setVisibility(RelativeLayout.GONE);
    }

    public void onFetchClick(final DBHelper mydb) {
        fetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isValidEditText(from) && isValidEditText(to)) {
                    if (ConnectionCheck()) {
                        //  url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + from.getText().toString() + "&destination=" + to.getText().toString() + "&key=" + Constants.TAG_APIKEY + "";
                        url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + from.getText().toString().replace(" ", "%20") + "&destinations=" + to.getText().toString().replace(" ", "%20") + "&mode=driving&language=en-EN&key=" + Constants.TAG_APIKEY + "";
                        try {
                            jObj = new MyClientTask(url, PlanTripDetails.this).execute().get();
                            jsonList = extractJsonDetails(jObj);
                            populateListView(jsonList, mydb);
                            mydb.close();
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


    public void populateListView(ArrayList<HashMap<String, String>> fromJson, final DBHelper mydb) {
        if (fromJson.get(0).containsKey("Error")) {
            rel1.setVisibility(RelativeLayout.GONE);
            Toast.makeText(PlanTripDetails.this, "Kindly provide proper details", Toast.LENGTH_LONG).show();
        } else {
            rel1.setVisibility(RelativeLayout.VISIBLE);
            String[] from = {Constants.TAG_LABELORIGIN, Constants.TAG_LABELDEST, Constants.TAG_DIST, Constants.TAG_DUR, Constants.TAG_ID};
            int[] to = {R.id.textView_from, R.id.textView_to, R.id.textView_distdur, R.id.textView_duration, R.id.textView_id};
            SimpleAdapter adapter = new SimpleAdapter(this, fromJson, R.layout.tab_frag_tofrom, from, to);
            lv.setAdapter(adapter);
            Cursor rs = mydb.getSelecedTravelData(fromJson);
            if (rs.getCount() > 0) {
                rs.moveToFirst();
                int count = rs.getInt(0);
                // Log.i("PlanTripDetails", "count is:" + count);
                if (count > 0) {
                    Toast.makeText(PlanTripDetails.this, "Details already exists", Toast.LENGTH_LONG).show();
                } else {
                    boolean flag = mydb.insertDistanceDetail(fromJson);
                    if (flag) {
                        Toast.makeText(PlanTripDetails.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            rs.close();
            mydb.close();

        }

    }


    public void listViewItemSelect() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
                String _id = getIdFromTable();
                if (_id.equalsIgnoreCase("error")) {
                    Toast.makeText(PlanTripDetails.this, "View trip details in View My Trip section", Toast.LENGTH_LONG).show();
                } else {
                    Log.i("LISTVIEWSELECT", "ID returned is:" + _id);
                    Intent itnt = new Intent(PlanTripDetails.this, ViewAllActivity.class);
                    itnt.putExtra(Constants.TAG_ID, _id);
                    startActivity(itnt);
                }

                //Toast.makeText(PlanTripDetails.this, "Values are: \norigin" + map.get(Constants.TAG_ORIGIN) + "\nDest:" + map.get(Constants.TAG_DEST) + "\nDist" + map.get(Constants.TAG_DIST) + "\nDuration" + map.get(Constants.TAG_DUR) + "\nId" + map.get(Constants.TAG_ID), Toast.LENGTH_LONG).show();
            }
        });
    }


    public String getIdFromTable() {
        String id = "";
        Cursor rs = mydb.getPlannedTravelData(jsonList);
        if (rs.getCount() > 0) {
            rs.moveToFirst();
            int count = rs.getInt(0);
            if (count > 0) {
                id = rs.getString(rs.getColumnIndex(Constants.TAG_ID));
            } else {
                id = "error";
            }
        }
        rs.close();
        mydb.close();
        return id;
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
            onBackPressed();
            // i = new Intent(PlanTripDetails.this, NavMainActivity.class);
            // startActivity(i);
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

    private ArrayList<HashMap<String, String>> extractJsonDetails(JSONObject json) {
        JSONArray dest, origin;
        ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        JSONArray rowsArray = null;
        String distance, duration, destination, source;
        try {
            dest = json.getJSONArray(TAG_DESTADDR);
            destination = dest.getString(0);
            origin = json.getJSONArray(TAG_ORIGINADDR);
            source = origin.getString(0);
            rowsArray = json.getJSONArray(TAG_ROWS);
            JSONObject rowsObject = rowsArray.getJSONObject(0);//only one element in this array
            JSONArray elementsArray = rowsObject.getJSONArray(TAG_ELEMENTS);
            JSONObject elementsObject = elementsArray.getJSONObject(0);//only one element in this array
            String status = elementsObject.getString(TAG_STATUS);
            //Log.i("PlanTripDetails", "Status fetched is:" + status);
            HashMap<String, String> hm = new HashMap<String, String>();
            if (!status.equalsIgnoreCase("ZERO_RESULTS") && !status.equalsIgnoreCase("NOT_FOUND")) {
                JSONObject distanceObject = elementsObject.getJSONObject(TAG_DISTANCE);
                JSONObject durationObject = elementsObject.getJSONObject(TAG_DURATION);
                distance = distanceObject.getString(TAG_TEXT); //distance in kms
                duration = durationObject.getString(TAG_TEXT);
                //Log.i("JSONRESULE", "Result is:" + destination + source + distance + duration + status);

                HashMap<String, String> otherDetails = new HashMap<String, String>();
                hm.put(Constants.TAG_LABELORIGIN, from.getText().toString());
                hm.put(Constants.TAG_LABELDEST, to.getText().toString());
                hm.put(Constants.TAG_ORIGIN, source);
                hm.put(Constants.TAG_DEST, destination);
                hm.put(Constants.TAG_DIST, distance);
                hm.put(Constants.TAG_DUR, duration);
                String withoutComma;
                Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
                Matcher matcher;
                if (distance.contains(",")) {
                    //Log.i("PlanTripDetails", "Wth Comma, so taking it away!");
                    withoutComma = distance.replace(",", "");
                    matcher = pattern.matcher(withoutComma);
                } else {
                    matcher = pattern.matcher(distance);
                }

                if (matcher.find()) {
                    //Log.i("PLANTRIPDETAILS", "MATCHER is:" + matcher.group());
                }

                if (tick.isChecked()) {
                    otherDetails = finalCalc(matcher.group(), true);
                    hm.put(Constants.TAG_RETURNTICK, "true");
                } else {
                    otherDetails = finalCalc(matcher.group(), false);
                    hm.put(Constants.TAG_RETURNTICK, "false");
                }
                hm.put(Constants.TAG_LITRE, otherDetails.get(Constants.TAG_LITRE));
                hm.put(Constants.TAG_AMOUNT, otherDetails.get(Constants.TAG_AMOUNT));
                hm.put(Constants.TAG_URL, "https://www.google.com/maps/dir/" + source + "/" + destination + "/");
                //Log.i("PlanTRIPDETAILS", "Hashmap with values is:" + hm);
                aList.add(hm);
            } else {
                //Log.i("JSONRESULE", "Result is NONE");
                hm.put("Error", "null");
                aList.add(hm);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aList;
    }

    public HashMap<String, String> finalCalc(String dist, boolean isChecked) {
        ArrayList<String> details = new ArrayList<String>();
        HashMap<String, String> al = new HashMap<String, String>();
        Cursor rs = mydb.getUserData();
        if (rs.getCount() > 0) {
            rs.moveToFirst();
            for (int i = 1; i <= 5; i++) {
                System.out.println(rs.getString(i));
                details.add(rs.getString(i));
            }
        }
        int mi = Integer.parseInt(details.get(2).toString());
        int fu = Integer.parseInt(details.get(3).toString().substring(0, 2));
        int dis;
        if (dist.contains(".")) {
            dis = Integer.parseInt(dist.toString().substring(0, dist.indexOf(".")));
            //Log.i("PlanTripDetails", "Distance as int is:" + dis);
        } else {
            dis = Integer.parseInt(dist.toString());
        }
        float totalAmt;
        if (isChecked) {
            totalAmt = (fu * (dis * 2)) / mi;
        } else {
            totalAmt = (fu * dis) / mi;
        }
        String totalAmount = String.valueOf(totalAmt);
        float litre = totalAmt / fu;
        String litres = String.valueOf(litre);
        al.put(Constants.TAG_LITRE, litres);
        al.put(Constants.TAG_AMOUNT, totalAmount);
        return al;
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        finish();
        return;
    }
}
