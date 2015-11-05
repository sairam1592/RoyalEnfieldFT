package com.example.admin.royalenfield.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.admin.royalenfield.DBOperations.DBHelper;
import com.example.admin.royalenfield.NavMainActivity;
import com.example.admin.royalenfield.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 10/24/2015.
 */
public class MyDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private DBHelper mydb;
    AlertDialog.Builder alert;
    AlertDialog dialog;
    GridLayout grid1;
    Intent i;
    Spinner bullType_spinner, fuelCost_spinner;
    EditText userName, Mileage, mailId;
    Button bText;
    ArrayList<String> details;
    LinkedHashMap<String, String> userDetails;
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

    public MyDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mydetails, container, false);
        grid1 = (GridLayout) rootView.findViewById(R.id.gridlayout1);
        bullType_spinner = (Spinner) rootView.findViewById(R.id.BullTypeSpinner);
        fuelCost_spinner = (Spinner) rootView.findViewById(R.id.FuelCostSpinner);
        userName = (EditText) rootView.findViewById(R.id.UserNameEditText);
        Mileage = (EditText) rootView.findViewById(R.id.MileageEditText);
        mailId = (EditText) rootView.findViewById(R.id.MailEditText);
        bText = (Button) rootView.findViewById(R.id.buttonText);
        details = new ArrayList<String>();
        userDetails = new LinkedHashMap<String, String>();
        mydb = new DBHelper(getActivity());
        onBullTypeSelection();
        onFuelCostSelection();
        loadDetails(mydb);
        onShowDialog();
        buttonPress(mydb);
        return rootView;
    }

    public void onBullTypeSelection() {
        ArrayAdapter<String> adapter_bulltype = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bullType);
        adapter_bulltype
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bullType_spinner.setAdapter(adapter_bulltype);
        bullType_spinner.setOnItemSelectedListener(this);
    }

    public void onFuelCostSelection() {
        ArrayAdapter<String> adapter_fuelCost = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, fuelCost);
        adapter_fuelCost
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelCost_spinner.setAdapter(adapter_fuelCost);
        fuelCost_spinner.setOnItemSelectedListener(this);
    }

    public void loadDetails(final DBHelper mydb) {
        layoutChildDisable();
        bText.setText("Back");
        Cursor rs = mydb.getUserData();
        System.out.println("Count:" + rs.getCount());
        if (rs.getCount() > 0) {
            rs.moveToFirst();
            for (int i = 1; i <= 5; i++) {
                System.out.println(rs.getString(i));
                details.add(rs.getString(i));
            }
            System.out.println("Arraylist:" + details.toString());
            userName.setText(details.get(0));
            bullType_spinner.setSelection(Integer.parseInt(details.get(1).substring(details.get(1).indexOf("_") + 1)));
            Mileage.setText(details.get(2));
            fuelCost_spinner.setSelection(Integer.parseInt(details.get(3).substring(details.get(3).indexOf("_") + 1)));
            mailId.setText(details.get(4));
        } else {
            Toast.makeText(getActivity(),
                    "no data found", Toast.LENGTH_SHORT).show();
        }
    }

    public void onShowDialog() {
        alert = new AlertDialog.Builder(getActivity());
        dialog = alert.create();
        dialog.setTitle("Details");
        dialog.setMessage("Do you wish to change your details?");
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int which) {
                        layoutChildEnable();
                        bText.setText("Update");
                        dialog.dismiss();
                    }
                });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        layoutChildDisable();
                        bText.setText("Back");
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    public void layoutChildEnable() {
        for (int i = 0; i < grid1.getChildCount(); i++) {
            View child = grid1.getChildAt(i);
            child.setEnabled(true);
        }
    }

    public void layoutChildDisable() {
        for (int i = 0; i < grid1.getChildCount(); i++) {
            View child = grid1.getChildAt(i);
            child.setEnabled(false);
        }
    }

    public void onButtonPressIntent() {
        i = new Intent(getActivity(), NavMainActivity.class);
        startActivity(i);
    }

    public void buttonPress(final DBHelper mydb) {
        System.out.println(bText.getText().toString());

        bText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (bText.getText().toString().equalsIgnoreCase("Back")) {
                    onButtonPressIntent();
                } else if (bText.getText().toString().equalsIgnoreCase("Update")) {
                    int count = mydb.deleteAllDetails();
                    if (count > 0) {
                        if (isValidEmail() && isValidMileage() && isValidSpinnerBull() && isValidSpinnerFuel() && isValidUserName()) {
                            if (mydb.insertRiderDetail(userDetails)) {
                                Toast.makeText(getActivity(), "Details Saved", Toast.LENGTH_SHORT).show();
                                onButtonPressIntent();
                            }
                        }
                    } else {
                        Toast.makeText(getActivity(), "Some error occured while deleting or no data found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public boolean isValidUserName() {
        if (!userName.getText().toString().equalsIgnoreCase("")) {
            userDetails.put("name", userName.getText().toString());
            return true;
        } else {
            userName.setError("Enter username");
            return false;
        }
    }

    public boolean isValidMileage() {
        if (!Mileage.getText().toString().equalsIgnoreCase("")) {
            userDetails.put("mileage", Mileage.getText().toString());
            return true;
        } else {
            Mileage.setError("Enter mileage");
            return false;
        }
    }

    public boolean isValidSpinnerBull() {
        if (!bullType_spinner.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {

            userDetails.put("bulltype", bullType_spinner.getSelectedItem().toString() + "_" + bullType_spinner.getSelectedItemPosition());
            return true;
        } else {
            Toast.makeText(getActivity(), "Kindly select your enfield type", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isValidSpinnerFuel() {
        if (!fuelCost_spinner.getSelectedItem().toString().equalsIgnoreCase("-- Select --")) {
            userDetails.put("fuelcost", fuelCost_spinner.getSelectedItem().toString() + "_" + fuelCost_spinner.getSelectedItemPosition());
            return true;
        } else {
            Toast.makeText(getActivity(), "Kindly select fuel cost per liter", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isValidEmail() {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailId.getText().toString());
        if (matcher.matches()) {
            userDetails.put("mailid", mailId.getText().toString());
            return true;
        } else {
            mailId.setError("Enter proper emailid ex:user@domain.com");
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
