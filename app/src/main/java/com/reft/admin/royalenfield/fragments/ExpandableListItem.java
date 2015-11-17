package com.reft.admin.royalenfield.fragments;

import com.reft.admin.royalenfield.misc.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by arun on 11/13/2015.
 */
public class ExpandableListItem {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        List googleMaps = new ArrayList();
        googleMaps.add("Distance Matrix API to fetch distance details");
        googleMaps.add("Places API to get nearby gas_station");
        googleMaps.add("Google Location service to get lat and long");
        googleMaps.add("Listview populated based on above details");


        List sqLiteDB = new ArrayList();
        sqLiteDB.add("SQLite to store user and travel details");
        sqLiteDB.add("Details can be saved and retrieved");
        sqLiteDB.add("View My Trip tab-planned trips are fetched and populated in listview");

        List androidConcepts = new ArrayList();
        androidConcepts.add("Navigation App Drawer - Friendly UI");
        androidConcepts.add("ViewPager - RE-History");
        androidConcepts.add("ViewPager supports swipe-able tab views");
        androidConcepts.add("ViewFlipper - About page");
        androidConcepts.add("ViewFlipper supports auto flipping of images");
        androidConcepts.add("Fragments for re-usability, to support multiple screens");

        List appTips = new ArrayList();
        appTips.add("In Motorcycle tab Long press bike name to view specifications");
        appTips.add("Total cost of fuel to put for one way/two way calculated");
        appTips.add("Total litre of fuel to put computed for user viewing");
        appTips.add("Kindly provide approximate fuel cost and mileage for approx calculation");
        appTips.add("Users can check current fuel price and update in My Details tab frequently");
        appTips.add("Share trip plan with different applications");
        appTips.add("Promote this app by performing Tell A Friend (Sharing) with other");

        List githubLink = new ArrayList();
        githubLink.add("https://github.com/sairam1592/RoyalEnfieldFT");

        List contact = new ArrayList();
        contact.add("Click to send mail (Gmail)");

        expandableListDetail.put(Constants.TAG_ITEMANDROIDDEV, androidConcepts);
        expandableListDetail.put(Constants.TAG_ITEMGOOGLEMAPS, googleMaps);
        expandableListDetail.put(Constants.TAG_ITEMDATABASE, sqLiteDB);
        expandableListDetail.put(Constants.TAG_ITEMGITHUB, githubLink);
        expandableListDetail.put(Constants.TAG_ITEMTIPS, appTips);
        expandableListDetail.put(Constants.TAG_ITEMSENDMAIL, contact);
        return expandableListDetail;
    }
}