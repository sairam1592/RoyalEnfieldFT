package com.reft.admin.ridersdelight.BikeList;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.fragments.SpecsActivity;
import com.reft.admin.ridersdelight.misc.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 10/20/2015.
 */
public class MotorCycleList extends Fragment {


    String[] stdBull;
    String[] bullElec;
    String[] Bull500;
    String[] Cls350;
    String[] Cls_DS_BG;
    String[] Cls_500;
    String[] Thunder_350;
    String[] Thunder_500;
    String[] Continental;
    String[] Himalayan;
    String stdB350Url[];
    String stdB500Url[];
    String BElectraUrl[];
    String cls350Url[];
    String cls500Url[];
    String clsDSUrl[];
    String clsChrUrl[];
    String clsBGUrl[];
    String tb350Url[];
    String tb500Url[];
    String conGTUrl[];
    String himUrl[];


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
            Constants.TAG_CAFERACER,
            Constants.TAG_HIMALAYAN
    };

    String bikePrice[];

    int himImages[]=new int[]{R.drawable.himalayan1,R.drawable.himalayan2};

    ListView listView;
    Intent i;
    Bundle b;

    public MotorCycleList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_bikelist, container, false);

        String[] bikeName = getResources().getStringArray(R.array.bikeName);

        stdBull = getResources().getStringArray(R.array.StandardBullet350);
        bullElec = getResources().getStringArray(R.array.BulletElectra350);
        Bull500 = getResources().getStringArray(R.array.Bullet500);
        Cls350 = getResources().getStringArray(R.array.Classic_350);
        Cls_DS_BG = getResources().getStringArray(R.array.Classic_DS_BG_500);
        Cls_500 = getResources().getStringArray(R.array.Classic_350);
        Thunder_350 = getResources().getStringArray(R.array.Thunderbird_350);
        Thunder_500 = getResources().getStringArray(R.array.Thunderbird_500);
        Continental = getResources().getStringArray(R.array.Continental_GT);
        Himalayan = getResources().getStringArray(R.array.Himalayan);

        stdB350Url=getResources().getStringArray(R.array.stdBulletLinks);
        BElectraUrl=getResources().getStringArray(R.array.BulletElectraLinks);
        stdB500Url=getResources().getStringArray(R.array.Bullet500Links);
        cls350Url=getResources().getStringArray(R.array.Classic350Links);
        cls500Url=getResources().getStringArray(R.array.Classic500Links);
        clsDSUrl=getResources().getStringArray(R.array.ClassicDSLinks);
        clsBGUrl=getResources().getStringArray(R.array.ClassicBGLinks);
        clsChrUrl=getResources().getStringArray(R.array.ClassicChrLinks);
        tb350Url=getResources().getStringArray(R.array.Tb350Links);
        tb500Url=getResources().getStringArray(R.array.tb500Links);
        conGTUrl=getResources().getStringArray(R.array.ContinentalLinks);
        himUrl=getResources().getStringArray(R.array.HimalayanLinks);

        TypedArray bikeImage = getResources().obtainTypedArray(R.array.bikeImage);
        bikePrice=getResources().getStringArray(R.array.BikePrice);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < bikeName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put(Constants.TAG_TXT, bikeName[i]);
            hm.put(Constants.TAG_IMAGE, Integer.toString(bikeImage.getResourceId(i, -1)));
            hm.put(Constants.TAG_CAT, "Category: " + bikeCategory[i]);
            hm.put(Constants.TAG_PRICE, bikePrice[i]);
            aList.add(hm);
        }
        String[] from = {Constants.TAG_IMAGE, Constants.TAG_TXT, Constants.TAG_CAT,Constants.TAG_PRICE};
        int[] to = {R.id.imageView_img, R.id.textView_bikeName, R.id.textView_category,R.id.textView_price};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.simple_list_item, from, to);
        listView = (ListView) rootView.findViewById(R.id.listview_bike);
        listView.setAdapter(adapter);

        listViewItemSelect();
        listViewOnLongClick();

        Toast.makeText(getActivity(),Constants.TAG_LONG_PRESS_MSG,Toast.LENGTH_SHORT).show();
        return rootView;
    }

    public void listViewOnLongClick() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(pos);
                String bikeName = map.get(Constants.TAG_TXT);
                i = new Intent(getActivity(), SpecsActivity.class);
                Bundle bn = new Bundle();
                bn.putString(Constants.TAG_BIKENAME, bikeName);

                switch (bikeName) {
                    case Constants.TAG_STDBUL:
                        bn.putStringArray(Constants.TAG_BIKESPECS, stdBull);
                        break;
                    case Constants.TAG_BUL500:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Bull500);
                        break;
                    case Constants.TAG_ELECTRA:
                        bn.putStringArray(Constants.TAG_BIKESPECS, bullElec);
                        break;
                    case Constants.TAG_CLS350:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Cls350);
                        break;
                    case Constants.TAG_CLS500:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Cls_500);
                        break;
                    case Constants.TAG_CLSCHROME:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Cls350);
                        break;
                    case Constants.TAG_CLSBG:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Cls_DS_BG);
                        break;
                    case Constants.TAG_CLSDS:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Cls_DS_BG);
                        break;
                    case Constants.TAG_TB350:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Thunder_350);
                        break;
                    case Constants.TAG_TB500:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Thunder_500);
                        break;
                    case Constants.TAG_CGT:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Continental);
                        break;
                    case Constants.TAG_HIM:
                        bn.putStringArray(Constants.TAG_BIKESPECS, Himalayan);
                        break;
                    default:
                        Log.e(Constants.TAG_REFT, "MessageType: UNKNOWN");
                        break;
                }
                i.putExtras(bn);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                return true;
            }
        });


    }


    public void listViewItemSelect() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String bikeName = map.get(Constants.TAG_TXT);
                Toast.makeText(getActivity(), bikeName, Toast.LENGTH_SHORT).show();
                b = new Bundle();
                i = new Intent(getActivity(), BikeViewActivity.class);
                b.putString(Constants.TAG_BIKENAME, bikeName);
                b.putIntArray(Constants.TAG_BIKEIMAGES,himImages);
                switch (bikeName) {
                    case Constants.TAG_STDBUL:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL,stdB350Url);
                        break;
                    case Constants.TAG_BUL500:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, stdB500Url);
                        break;
                    case Constants.TAG_ELECTRA:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, BElectraUrl);
                        break;
                    case Constants.TAG_CLS350:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, cls350Url);
                        break;
                    case Constants.TAG_CLS500:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, cls500Url);
                        break;
                    case Constants.TAG_CLSCHROME:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, clsChrUrl);
                        break;
                    case Constants.TAG_CLSBG:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, clsBGUrl);
                        break;
                    case Constants.TAG_CLSDS:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, clsDSUrl);
                        break;
                    case Constants.TAG_TB350:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, tb350Url);
                        break;
                    case Constants.TAG_TB500:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, tb500Url);
                        break;
                    case Constants.TAG_CGT:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, conGTUrl);
                        break;
                    case Constants.TAG_HIM:
                        b.putStringArray(Constants.TAG_BIKEIMAGESURL, himUrl);
                        break;
                    default:
                        Log.e(Constants.TAG_REFT, "MessageType: UNKNOWN");
                        break;
                }
                i.putExtras(b);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            }
        });
    }
}