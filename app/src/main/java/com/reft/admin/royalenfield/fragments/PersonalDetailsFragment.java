package com.reft.admin.royalenfield.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.DBOperations.DBHelper;
import com.reft.admin.royalenfield.NavMainActivity;
import com.reft.admin.royalenfield.misc.Constants;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 10/24/2015.
 */

public class PersonalDetailsFragment extends Activity implements OnItemSelectedListener {

    private DBHelper mydb;
    LinkedHashMap<String, String> userDetails;
    Spinner bullType_spinner, fuelCost_spinner;
    private String[] bullType = {"-- Select --", "Standard Bullet 350",
            "Bullet Electra 350", "Classic 350", "Thunderbird 350",
            "Bullet 500", "Classic 500", "Desert Storm 500", "Thunderbird500",
            "Continental GT 500"};

    private String[] fuelCost = {"-- Select --", "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61",
            "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72",
            "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
            "84", "85", "86", "87", "88", "90", "91", "92", "93", "94", "95",
            "96", "97", "98", "99", "100"};
    AlertDialog.Builder alert;
    AlertDialog dialog;
    LinearLayout layPos;
    RelativeLayout rel1, rel2;
    GridLayout grid1, grid2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        Toast.makeText(PersonalDetailsFragment.this, "This is a one time activity", Toast.LENGTH_LONG).show();
        mydb = new DBHelper(this);
        userDetails = new LinkedHashMap<String, String>();
        bullType_spinner = (Spinner) findViewById(R.id.BullTypeSpinner);
        fuelCost_spinner = (Spinner) findViewById(R.id.FuelCostSpinner);
        layPos = (LinearLayout) findViewById(R.id.linearlayoutButtons);
        rel1 = (RelativeLayout) findViewById(R.id.relativelayout1);
        rel2 = (RelativeLayout) findViewById(R.id.relativelayout2);
        grid1 = (GridLayout) findViewById(R.id.gridlayout1);
        grid2 = (GridLayout) findViewById(R.id.gridlayout2);
        onBullTypeSelection();
        onFuelCostSelection();
        layPos.setVisibility(LinearLayout.GONE);

        Button save = (Button) findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isValidEmail() && isValidMileage() && isValidSpinnerBull() && isValidSpinnerFuel() && isValidUserName()) {
                    if (mydb.insertRiderDetail(userDetails)) {
                        Toast.makeText(PersonalDetailsFragment.this, "Details Saved", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PersonalDetailsFragment.this, NavMainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
        mydb.close();
    }

    public void onBullTypeSelection() {
        ArrayAdapter<String> adapter_bulltype = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bullType);
        adapter_bulltype
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bullType_spinner.setAdapter(adapter_bulltype);
        bullType_spinner.setOnItemSelectedListener(this);

    }

    public void onFuelCostSelection() {
        ArrayAdapter<String> adapter_fuelCost = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fuelCost);
        adapter_fuelCost
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelCost_spinner.setAdapter(adapter_fuelCost);
        fuelCost_spinner.setOnItemSelectedListener(this);

    }

    public boolean isValidUserName() {
        EditText userName = (EditText) findViewById(R.id.UserNameEditText);
        if (!userName.getText().toString().equalsIgnoreCase("")) {
            userDetails.put("name", userName.getText().toString());
            return true;
        } else {
            userName.setError(Constants.TAG_USERNAMEENTER);
            return false;
        }
    }

    public boolean isValidMileage() {
        EditText mileage = (EditText) findViewById(R.id.MileageEditText);
        if (!mileage.getText().toString().equalsIgnoreCase("")) {
            userDetails.put("mileage", mileage.getText().toString());
            return true;
        } else {
            mileage.setError(Constants.TAG_MILEAGEENTER);
            return false;
        }
    }

    public boolean isValidSpinnerBull() {

        if (!bullType_spinner.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {

            userDetails.put("bulltype", bullType_spinner.getSelectedItem().toString() + "_" + bullType_spinner.getSelectedItemPosition());
            return true;
        } else {
            Toast.makeText(PersonalDetailsFragment.this, Constants.TAG_BULLTYPEENTER, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isValidSpinnerFuel() {

        if (!fuelCost_spinner.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {
            userDetails.put("fuelcost", fuelCost_spinner.getSelectedItem().toString() + "_" + fuelCost_spinner.getSelectedItemPosition());
            return true;
        } else {
            Toast.makeText(PersonalDetailsFragment.this, Constants.TAG_FUELCOSTENTER, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isValidEmail() {
        Pattern pattern;
        Matcher matcher;
        EditText emailid = (EditText) findViewById(R.id.MailEditText);
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailid.getText().toString());
        if (matcher.matches()) {
            userDetails.put("mailid", emailid.getText().toString());
            return true;
        } else {
            emailid.setError(Constants.TAG_MAILIDENTER);
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.BullTypeSpinner:
                break;
            case R.id.FuelCostSpinner:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

