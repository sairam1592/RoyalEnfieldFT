package com.example.admin.royalenfield.BikeList;

/**
 * Created by Admin on 10/20/2015.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.admin.royalenfield.R;
import com.example.admin.royalenfield.misc.Constants;

public class BikeViewActivity extends FragmentActivity {
    MyPageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        int[] imageArray = b.getIntArray(Constants.TAG_BIKEIMAGES);
        String name = b.getString(Constants.TAG_BIKENAME);
        List<Fragment> fragments = getFragments(name, imageArray);
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments(String name, int[] imageArray) {
        List<Fragment> fList = new ArrayList<Fragment>();

        for (int i = 0; i < imageArray.length; i++) {
            fList.add(BikeFragment.newInstance(name, imageArray[i]));
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