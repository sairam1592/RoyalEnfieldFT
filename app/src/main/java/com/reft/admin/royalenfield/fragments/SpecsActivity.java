package com.reft.admin.royalenfield.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.NavMainActivity;
import com.reft.admin.royalenfield.misc.Constants;

/**
 * Created by Admin on 11/8/2015.
 */
public class SpecsActivity extends Activity {

    TextView name, detailText;
    //ImageView img;
    String[] detail;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec);

        name = (TextView) findViewById(R.id.textView_name);
       // img = (ImageView) findViewById(R.id.imageView_img);
        detailText = (TextView) findViewById(R.id.textView_detail);

        Intent i = getIntent();
        Bundle b = this.getIntent().getExtras();
        name.setText(b.getString(Constants.TAG_BIKENAME));
        //img.setImageResource(b.getInt(Constants.TAG_IMAGE));

        StringBuilder sb = new StringBuilder();
        detail = b.getStringArray(Constants.TAG_BIKESPECS);
        for (int j = 0; j < detail.length; j++) {
            sb.append(detail[j] + "\n\n");
        }

        detailText.setText(sb.toString());
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
            Intent i = new Intent(SpecsActivity.this, NavMainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
