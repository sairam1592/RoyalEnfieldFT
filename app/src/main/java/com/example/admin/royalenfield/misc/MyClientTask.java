package com.example.admin.royalenfield.misc;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.royalenfield.misc.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

public class MyClientTask extends AsyncTask<String, Void, JSONObject> {

    String _url;
    private ProgressDialog dialog;
    JSONObject jObj;
    Context context = null;

    public MyClientTask(String url, Context contex) {
        _url = url;
        context = contex;
    }


    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("fetching details..");
        dialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        // TODO Auto-generated method stub
        JSONParser jParser = new JSONParser();
        jObj = jParser.getJSONFromUrl(_url);
        try {
            Log.i("MyClientTask", "JSON String returned is:" + jObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObj;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        Log.i("MyClientTask", "JSON Object returned is:" + result);
        dialog.dismiss();

    }
}

