package com.reft.admin.ridersdelight;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.reft.admin.ridersdelight.BikeList.MotorCycleList;
import com.reft.admin.ridersdelight.History.HistoryActivity;
import com.reft.admin.ridersdelight.fragments.DeveloperFragment;
import com.reft.admin.ridersdelight.fragments.HimalayanFragment;
import com.reft.admin.ridersdelight.fragments.MyDetailsFragment;
import com.reft.admin.ridersdelight.fragments.MyTripFragment;
import com.reft.admin.ridersdelight.fragments.NearbyGasStatFragment;
import com.reft.admin.ridersdelight.misc.Constants;


public class NavMainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE); //code written to block screenshot android
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        setFragment(new MotorCycleList());
        //  new GcmRegistrationAsyncTask(this).execute();
    }


    //To set default fragment on load of app
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MotorCycleList();
                break;
            case 1:
                Intent i = new Intent(NavMainActivity.this, HistoryActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                break;
            case 2:
                fragment = new MyDetailsFragment();
                break;
            case 3:
                fragment = new MyTripFragment();
                break;

            case 4:
                fragment = new NearbyGasStatFragment();
                break;
            case 5:
                rateApp();
                break;
            case 6:
                fragment = new HimalayanFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section2);
                break;
            case 1:
                mTitle = getString(R.string.title_section3);
                break;
            case 2:
                mTitle = getString(R.string.title_section4);
                break;
            case 3:
                mTitle = getString(R.string.title_section5);
                break;
            case 4:
                mTitle = getString(R.string.title_section6);
                break;
            case 5:
                mTitle = getString(R.string.title_section7);
                break;
            case 6:
                mTitle = getString(R.string.title_section8);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.nav_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_checkfuel) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.TAG_FUELURL));
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_share) {
            onShareClick();
            return true;
        }

        if (id == R.id.action_developer) {
            onDevClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        finish();
        return;
    }

    public void rateApp() {
        Uri uri = Uri.parse(Constants.TAG_PLAYSTORE);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.TAG_PLAYSTOREURL)));
        }
    }

    public void onShareClick() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hey there!!..\nCheck out the new Riders Delight app\n");
        sb.append(Uri.parse(Constants.TAG_PLAYSTOREURL));
        sb.append("\nYou can plan trips, view bike gallery & its specifications,share trip plan with buddies,set reminder and find the nearest gas station too.\n Its totally cool,check it out!\n");
        Intent sendIntent = new Intent();
        sendIntent.setType("text/plain");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        startActivity(sendIntent);
    }

    public void onDevClick() {
        Fragment fragment = new DeveloperFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }
}