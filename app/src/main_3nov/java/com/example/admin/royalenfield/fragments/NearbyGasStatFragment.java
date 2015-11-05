package com.example.admin.royalenfield.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;
import com.example.admin.royalenfield.misc.GPSTracker;
import com.example.admin.royalenfield.misc.MyClientTask;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearbystation, container, false);
        range = (Spinner) rootView.findViewById(R.id.RangeSpinner);
        fetch = (Button) rootView.findViewById(R.id.buttonFetch);
        rel2 = (RelativeLayout) rootView.findViewById(R.id.relativelayout2);
        gps = new GPSTracker(getActivity());
        onRangeSelection();
        onFormLoad();
        onFetchClick();
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

    public void onFetchClick() {
        fetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isValidSpinner(range)) {
                    if (ConnectionCheck()) {
                        if (gps.canGetLocation()) {
                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();
                            if (latitude == 0.0 || longitude == 0.0) {
                                Toast.makeText(getActivity(), "Error in loading current location", Toast.LENGTH_SHORT).show();
                            } else {
                                url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=" + range.getSelectedItem().toString() + "000&types=" + Constants.TAG_TYPE + "&key=" + Constants.TAG_APIKEY + "";
                                try {
                                    jObj = new MyClientTask(url, getActivity()).execute().get();
                                    Toast.makeText(getActivity(), "JSON OBJ returned is:" + jObj, Toast.LENGTH_LONG).show();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            gps.showSettingsAlert();
                        }
                    } else {
                        Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean isValidSpinner(Spinner range) {
        if (!range.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {
            return true;
        } else {
            Toast.makeText(getActivity(), "Kindly select range", Toast.LENGTH_SHORT).show();
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
