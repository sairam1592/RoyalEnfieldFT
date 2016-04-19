package com.reft.admin.ridersdelight.BikeList;

/**
 * Created by Admin on 10/20/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.reft.admin.ridersdelight.R;
import com.reft.admin.ridersdelight.misc.Constants;

import java.util.ArrayList;
import java.util.List;

public class BikeViewActivity extends FragmentActivity {
    MyPageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        int[] himImg=b.getIntArray(Constants.TAG_BIKEIMAGES);
        String[] strArray = b.getStringArray(Constants.TAG_BIKEIMAGESURL);
        String name = b.getString(Constants.TAG_BIKENAME);
        List<Fragment> fragments = getFragments(name,strArray,himImg);
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments(String name,String[] strArray,int[] intArr) {
        List<Fragment> fList = new ArrayList<Fragment>();

        for (int i = 0; i < strArray.length; i++) {
            fList.add(BikeFragment.newInstance(name,strArray[i],intArr[i]));
        }
        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}