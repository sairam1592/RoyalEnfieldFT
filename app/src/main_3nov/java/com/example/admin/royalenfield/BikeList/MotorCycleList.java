package com.example.admin.royalenfield.BikeList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 10/20/2015.
 */
public class MotorCycleList extends Fragment {


    String[] bikeCategory = new String[]{
            Constants.TAG_STDSTREET,
            Constants.TAG_STDSTREET,
            Constants.TAG_STDSTREET,
            Constants.TAG_RETROSTREET,
            Constants.TAG_RETROSTREET,
            Constants.TAG_RETROSTREET,
            Constants.TAG_RETROSTREET,
            Constants.TAG_RETROSTREET,
            Constants.TAG_CRUISER,
            Constants.TAG_CRUISER,
            Constants.TAG_CAFERACER
    };


    int[] standardBullet = new int[]{
            R.drawable.bullet_1,
            R.drawable.bullet_2
    };

    int[] bulletElectra = new int[]{
            R.drawable.electra_1,
            R.drawable.electra_2
    };

    int[] bullet500 = new int[]{
            R.drawable.bulletfive_1,
            R.drawable.bulletfive_2
    };

    int[] classic350 = new int[]{
            R.drawable.classicblue_3,
            R.drawable.classicblue_4
    };

    int[] classic500 = new int[]{
            R.drawable.classicfive_1,
            R.drawable.classicfive_2,
            R.drawable.classicfive_5
    };

    int[] classicChrome = new int[]{
            R.drawable.classicchrome_2,
            R.drawable.classicchrome_3
    };

    int[] classicDS = new int[]{
            R.drawable.desertstorm_1,
            R.drawable.desertstorm_3
    };

    int[] classicBG = new int[]{
            R.drawable.military_2,
            R.drawable.military_3
    };

    int[] tb350 = new int[]{
            R.drawable.tb_1,
            R.drawable.tb_3,
            R.drawable.tb_5
    };

    int[] tb500 = new int[]{
            R.drawable.tbfive_2,
            R.drawable.tbfive_4
    };

    int[] continental = new int[]{
            R.drawable.gt_1,
            R.drawable.gt_3
    };


    ListView listView;
    Intent i;
    Bundle b;

    public MotorCycleList(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_bikelist, container, false);

        String[] bikeName = getResources().getStringArray(R.array.bikeName);
        TypedArray bikeImage = getResources().obtainTypedArray(R.array.bikeImage);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        b = new Bundle();
        for (int i = 0; i < bikeName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put(Constants.TAG_TXT, bikeName[i]);
            hm.put(Constants.TAG_IMAGE, Integer.toString(bikeImage.getResourceId(i, -1)));
            hm.put(Constants.TAG_CAT, "Category: " + bikeCategory[i]);
            aList.add(hm);
        }
        String[] from = {Constants.TAG_IMAGE, Constants.TAG_TXT, Constants.TAG_CAT};
        int[] to = {R.id.imageView_img, R.id.textView_bikeName, R.id.textView_category};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.simple_list_item, from, to);
        listView = (ListView) rootView.findViewById(R.id.listview_bike);
        listView.setAdapter(adapter);

        listViewItemSelect();
        return rootView;
    }


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikelist);
        String[] bikeName = getResources().getStringArray(R.array.bikeName);
        TypedArray bikeImage = getResources().obtainTypedArray(R.array.bikeImage);
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        b = new Bundle();
        for (int i = 0; i < bikeName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put(Constants.TAG_TXT, bikeName[i]);
            hm.put(Constants.TAG_IMAGE, Integer.toString(bikeImage.getResourceId(i, -1)));
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
*/

    public void listViewItemSelect() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String bikeName = map.get(Constants.TAG_TXT);
                Toast.makeText(getActivity(), bikeName, Toast.LENGTH_SHORT).show();
                i = new Intent(getActivity(), BikeViewActivity.class);
                b.putString(Constants.TAG_BIKENAME, bikeName);

                switch (bikeName) {
                    case Constants.TAG_STDBUL:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, standardBullet);
                        break;
                    case Constants.TAG_BUL500:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, bullet500);
                        break;
                    case Constants.TAG_ELECTRA:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, bulletElectra);
                        break;
                    case Constants.TAG_CLS350:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, classic350);
                        break;
                    case Constants.TAG_CLS500:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, classic500);
                        break;
                    case Constants.TAG_CLSCHROME:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, classicChrome);
                        break;
                    case Constants.TAG_CLSBG:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, classicBG);
                        break;
                    case Constants.TAG_CLSDS:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, classicDS);
                        break;
                    case Constants.TAG_TB350:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, tb350);
                        break;
                    case Constants.TAG_TB500:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, tb500);
                        break;
                    case Constants.TAG_CGT:
                        b.putIntArray(Constants.TAG_BIKEIMAGES, continental);
                        break;
                    default:
                        Log.e(Constants.TAG_REFT, "MessageType: UNKNOWN");
                        break;
                }
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}