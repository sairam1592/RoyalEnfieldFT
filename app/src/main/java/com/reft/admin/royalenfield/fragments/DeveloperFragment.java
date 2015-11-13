package com.reft.admin.royalenfield.fragments;

/**
 * Created by arun on 10/20/2015.
 */

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.reft.admin.royalenfield.R;
import com.reft.admin.royalenfield.misc.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DeveloperFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableList;
    List expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;

    public DeveloperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_developer, container, false);

        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListItem.getData();
        expandableListTitle = new ArrayList(expandableListDetail.keySet());
        expandableList = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableList);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
               /* Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show(); */
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show(); */

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (expandableListTitle.get(groupPosition).toString().equalsIgnoreCase(Constants.TAG_ITEMGITHUB)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)));
                    startActivity(intent);
                    // Toast.makeText(getActivity(), expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                } else if (expandableListTitle.get(groupPosition).toString().equalsIgnoreCase(Constants.TAG_ITEMSENDMAIL)) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"sairam.a1592@gmail.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Reg: RoyalEnfield App");
                    email.putExtra(Intent.EXTRA_TEXT, "your message...");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                }
                return false;
            }
        });


        return rootView;
    }
}