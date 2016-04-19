package com.reft.admin.ridersdelight.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.misc.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by arun on 2/2/2016.
 */
public class HimalayanFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableList;
    List expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;
    RelativeLayout rel1, rel2;
    ImageView img1,img2;

    public HimalayanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_himalayan, container, false);

        img1=(ImageView) rootView.findViewById(R.id.imageview1);
        img2=(ImageView) rootView.findViewById(R.id.imageview2);

        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(img1);

        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(img2);

        rel1 = (RelativeLayout) rootView.findViewById(R.id.relativeLayout1);
        rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.TAG_RELREVEAL));
                startActivity(intent);
            }
        });

        rel2 = (RelativeLayout) rootView.findViewById(R.id.relativeLayout2);
        rel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.TAG_RELFIRSTLOOK));
                startActivity(intent);
            }
        });


        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        expandableListDetail = HimalayanExpandable.getData();
        expandableListTitle = new ArrayList(expandableListDetail.keySet());
        expandableList = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableList);
        return rootView;
    }
}
