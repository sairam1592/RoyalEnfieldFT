package com.reft.admin.ridersdelight.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.misc.ConnectionHelper;
import com.reft.admin.ridersdelight.misc.Constants;
import com.reft.admin.ridersdelight.misc.GPSTracker;
import com.reft.admin.ridersdelight.misc.MyClientTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


/**
 * Created by arun on 11/3/2015.
 */
public class NearbyGasStatFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public NearbyGasStatFragment() {
    }

    private String[] range_ = {"-- Select --", "1",
            "2", "3", "4",
            "5", "6", "7", "8",
            "9", "10"};
    Spinner range;
    Button fetch;
    RelativeLayout rel2;
    String url;
    GPSTracker gps;
    double latitude;
    double longitude;
    JSONObject jObj;
    ListView lv;
    private ProgressDialog dialog;
    static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private boolean isPaused;
    ArrayList<HashMap<String, String>> jsonList;
    private static final String TAG_RESULTS = "results";
    private static final String TAG_NAME = "name";
    private static final String TAG_PLACEID = "place_id";
    private static final String TAG_VICINITY = "vicinity";
    private static final String TAG_URL = "mapsURL";
    private static final String TAG_ADDRESS = "formatted_address";
    private static final String TAG_STATUS = "status";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearbystation, container, false);
        range = (Spinner) rootView.findViewById(R.id.RangeSpinner);
        fetch = (Button) rootView.findViewById(R.id.buttonFetch);
        rel2 = (RelativeLayout) rootView.findViewById(R.id.relativelayout2);
        gps = new GPSTracker(getActivity());
        jsonList = new ArrayList<HashMap<String, String>>();
        lv = (ListView) rootView.findViewById(R.id.listview_gas);
        onRangeSelection();
        onFormLoad();
        onFetchClick();
        listViewItemSelect();
        dialog = new ProgressDialog(getActivity());
        return rootView;
    }

    public void onRangeSelection() {
        ArrayAdapter<String> adapter_range = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, range_);
        adapter_range
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        range.setAdapter(adapter_range);
        range.setOnItemSelectedListener(this);
    }

    public void onFormLoad() {
        rel2.setVisibility(RelativeLayout.GONE);
    }

    public void doJsonOperation() throws ExecutionException, InterruptedException {
        if (isValidSpinner(range)) {
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                if (latitude == 0.0 || longitude == 0.0) {
                    Toast.makeText(getActivity(), Constants.TAG_LOCATIONMESSAGE, Toast.LENGTH_SHORT).show();
                } else {
                    url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=" + range.getSelectedItem().toString() + "000&types=" + Constants.TAG_TYPE + "&key=" + Constants.TAG_APIKEY + "";
                    jObj = new MyClientTask(url, getActivity()).execute().get();
                    jsonList = extractJsonDetails(jObj);
                    populateListView(jsonList);
                }
            } else {
                gps.showSettingsAlert();
            }
        }
    }

    public void onFetchClick() {
        fetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (ConnectionCheck()) {
                    try {
                        doJsonOperation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), Constants.TAG_CHECKINTERNET, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void populateListView(ArrayList<HashMap<String, String>> fromJson) {
        if (fromJson.get(0).containsKey("Error")) {
            rel2.setVisibility(RelativeLayout.GONE);
            Toast.makeText(getActivity(), "Error loading details!! Try again.", Toast.LENGTH_LONG).show();
        } else {
            rel2.setVisibility(RelativeLayout.VISIBLE);
            String[] from = {TAG_NAME, TAG_VICINITY, TAG_URL};
            int[] to = {R.id.textView_gasStation, R.id.textView_address, R.id.textView_url};
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), fromJson, R.layout.simple_list_gasstation, from, to);
            lv.setAdapter(adapter);
        }
    }

    public void listViewItemSelect() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
                gps.stopUsingGPS();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(map.get(TAG_URL)));
                getActivity().overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                startActivity(intent);
            }
        });
    }

    public boolean isValidSpinner(Spinner range) {
        if (!range.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {
            return true;
        } else {
            Toast.makeText(getActivity(), Constants.TAG_RANGEENTER, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public boolean ConnectionCheck() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
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


    public ArrayList<HashMap<String, String>> extractJsonDetails(JSONObject jObj) {

        ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        JSONArray resultsArr = null;
        String status, name, place_id, vicinity, url;
        String latLong = "'" + latitude + "," + longitude + "'";
        try {
            HashMap<String, String> hm;
            status = jObj.getString(TAG_STATUS);
            if (status.equalsIgnoreCase("OK")) {
                resultsArr = jObj.getJSONArray(TAG_RESULTS);
                for (int i = 0; i < resultsArr.length(); i++) {
                    hm = new HashMap<String, String>();
                    JSONObject resultObject = resultsArr.getJSONObject(i);
                    name = resultObject.getString(TAG_NAME);
                    place_id = resultObject.getString(TAG_PLACEID);
                    vicinity = resultObject.getString(TAG_VICINITY);
                    hm.put(TAG_NAME, name);
                    hm.put(TAG_PLACEID, place_id);
                    hm.put(TAG_VICINITY, vicinity);
                    url = "https://www.google.com/maps/dir/" + latLong + "/" + vicinity + "/";
                    hm.put(TAG_URL, url);
                    aList.add(hm);
                }

            } else {
                hm = new HashMap<String, String>();
                hm.put("Error", "null");
                aList.add(hm);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aList;
    }


    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(CONNECTIVITY_CHANGE_ACTION);
        getActivity().registerReceiver(mChangeConnectionReceiver, filter);
        isPaused = false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChangeConnectionReceiver != null) {
            getActivity().unregisterReceiver(mChangeConnectionReceiver);
        }
    }

    private final BroadcastReceiver mChangeConnectionReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (CONNECTIVITY_CHANGE_ACTION.equals(action) && !isPaused) {
                //check internet connection
                if (!ConnectionHelper.isConnectedOrConnecting(getActivity().getApplicationContext())) {
                    if (context != null) {
                        boolean show = false;
                        if (ConnectionHelper.lastNoConnectionTs == -1) {//first time
                            show = true;
                            ConnectionHelper.lastNoConnectionTs = System.currentTimeMillis();
                        } else {
                            if (System.currentTimeMillis() - ConnectionHelper.lastNoConnectionTs > 1000) {
                                show = true;
                                ConnectionHelper.lastNoConnectionTs = System.currentTimeMillis();
                            }
                        }

                        if (show && ConnectionHelper.isOnline) {
                            rel2.setVisibility(RelativeLayout.GONE);
                            Toast.makeText(getActivity(), Constants.TAG_CHECKINTERNET, Toast.LENGTH_SHORT).show();
                            ConnectionHelper.isOnline = false;
                            dialog.setCancelable(false);
                            dialog.setMessage("Checking connection..");
                            dialog.show();
                        }
                    }
                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    try {
                        doJsonOperation();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ConnectionHelper.isOnline = true;
                }
            }
        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.RangeSpinner:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
