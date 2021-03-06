package com.reft.admin.ridersdelight.fragments;

import com.reft.admin.ridersdelight.misc.Constants;

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
        googleMaps.add("Distance Matrix API to fetch distance details.");
        googleMaps.add("Places API to get nearby gas_station.");
        googleMaps.add("Google Location service to get lat and long.");
        googleMaps.add("Listview populated based on above details.");


        List sqLiteDB = new ArrayList();
        sqLiteDB.add("SQLite database to store user and travel details.");
        sqLiteDB.add("Details are saved and retrieved.");
        sqLiteDB.add("View My Trip tab-trips planned are fetched and populated in listview.");

        List androidConcepts = new ArrayList();
        androidConcepts.add("Navigation App Drawer - Friendly UI.");
        androidConcepts.add("ViewPager - RE-History.");
        androidConcepts.add("ViewPager supports swipe-able tab views.");
        androidConcepts.add("Retrofit library for easy json parsing and loading the view");
        androidConcepts.add("Picasso image loading library to load images from url");
        androidConcepts.add("Robojucie dependency injection");
        androidConcepts.add("Fragments for re-usability, to support multiple screens.");
        androidConcepts.add("Scale image view library for zooming images");
        androidConcepts.add("Webview to view maps inside app");

        List appTips = new ArrayList();
        appTips.add("In Motorcycle tab Long press bike name to view specifications.");
        appTips.add("Users can Zoom-in and Zoom-out motorcycle images.");
        appTips.add("Total cost of fuel to fill for one-way/two-way travel is calculated.");
        appTips.add("Total litre of fuel to put is calculated.");
        appTips.add("Kindly provide approximate fuel cost and mileage for calculation.");
        appTips.add("Users can check current fuel price and update in My Details tab frequently.");
        appTips.add("Users can share trip plan with others across different applications.");
        appTips.add("Users can set reminder of trip plan in their Calendar application.");
        appTips.add("Promote this app by Tell A Friend (Sharing) with others.");

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