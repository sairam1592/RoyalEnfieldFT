package com.example.admin.royalenfield.BikeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 10/20/2015.
 */
public class MotorCycleList extends Activity {


    String[] bikeName = new String[]{
            "Standard Bullet 350",
            "Bullet Electra 350",
            "Bullet 500",
            "Classic 350",
            "Classic 500",
            "Classic Chrome",
            "Classic Battle Green 500",
            "Classic Desert Storm 500",
            "Thunderbird 350",
            "Thunderbird 500",
            "Continental GT 535"
    };

    String[] bikeCategory = new String[]{
            "Standard Street",
            "Standard Street",
            "Standard Street",
            "Retro Street",
            "Retro Street",
            "Retro Street",
            "Retro Street",
            "Retro Street",
            "Cruiser",
            "Cruiser",
            "Cafe Racer"
    };

    int[] bikeImage = new int[]{
            R.drawable.stb_img,
            R.drawable.elec_img,
            R.drawable.stb_img,
            R.drawable.class_img,
            R.drawable.classfive_img,
            R.drawable.classchr_img,
            R.drawable.classbg_img,
            R.drawable.classds_img,
            R.drawable.tb_img,
            R.drawable.tbfivr_img,
            R.drawable.gt_img
    };

    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikelist);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < bikeName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put(Constants.TAG_TXT, bikeName[i]);
            hm.put(Constants.TAG_IMAGE, Integer.toString(bikeImage[i]));
            hm.put(Constants.TAG_CAT, "Category: " + bikeCategory[i]);
            aList.add(hm);
        }
        String[] from = {Constants.TAG_IMAGE, Constants.TAG_TXT, Constants.TAG_CAT};

        int[] to = {R.id.imageView_img, R.id.textView_bikeName, R.id.textView_category};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.simple_list_item, from, to);

        listView = (ListView) findViewById(R.id.listview_bike);

        listView.setAdapter(adapter);

        listViewItemSelect();
    }


    public void listViewItemSelect() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String bikeName = map.get(Constants.TAG_TXT);
                Toast.makeText(MotorCycleList.this, bikeName, Toast.LENGTH_LONG).show();
            }
        });
    }
}
