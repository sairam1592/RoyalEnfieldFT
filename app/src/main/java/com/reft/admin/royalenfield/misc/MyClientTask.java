package com.reft.admin.royalenfield.misc;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObj;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        dialog.dismiss();

    }
}

