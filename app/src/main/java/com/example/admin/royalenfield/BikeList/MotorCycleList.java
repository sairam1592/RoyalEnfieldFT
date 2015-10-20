package com.example.admin.royalenfield.BikeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.royalenfield.R;

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
            "Standard Street", "Standard Street", "Standard Street", "Retro Street", "Retro Street", "Retro Street", "Retro Street", "Retro Street", "Cruiser", "Cruiser", "Cafe Racer"

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
            hm.put("txt", bikeName[i]);
            hm.put("image", Integer.toString(bikeImage[i]));
            hm.put("cat", "Category: "+bikeCategory[i]);
            aList.add(hm);
        }
        String[] from = {"image", "txt","cat"};

        int[] to = {R.id.imageView_img, R.id.textView_bikeName,R.id.textView_category};

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
                //Toast.makeText(MotorCycleList.this, position, Toast.LENGTH_LONG).show(); -NOTWORKING
            }
        });
    }
}
