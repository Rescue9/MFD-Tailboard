package com.corridor9design.mfdtailboard;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.corridor9design.mfdtailboard.callback.Callback;
import com.corridor9design.mfdtailboard.calendar.Calendar;

import org.arasthel.googlenavdrawermenu.views.GoogleNavigationDrawer;


public class MFDTailboard extends Activity implements
        Calendar.OnFragmentInteractionListener,
        Callback.OnFragmentInteractionListener {

    // debug tag for logging
    public static final String TAG = "MFDTailboard";
    public static final boolean DEBUG = true;

    // declarations GoogleNavMenuDrawer
    private Context mContext;
    private ActionBarDrawerToggle drawerToggle;
    private GoogleNavigationDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //logcat for initial start
        if (DEBUG) Log.d(TAG, "Starting...");
        mContext = this;

        googleNavMenuDrawer();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.MFDTailboardContainer, new FragmentHandler())
                    .commit();
        }
        //TODO: Configure screen orientation for tablets, individual fragments, and menu operations
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mfdtailboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
         * Declare the behaviour of clicking at the
         * application icon, opening and closing the drawer
         */
        // TESTING Toast.makeText(this, item + "Clicked", Toast.LENGTH_LONG).show();
        if (item.getItemId() == android.R.id.home) {
            if (mDrawer != null) {
                if (mDrawer.isDrawerMenuOpen()) {
                    mDrawer.closeDrawerMenu();
                } else {
                    mDrawer.openDrawerMenu();
                }
            }
        }

        // setup typical right menu for more permanent items
        // settings, about, contact, etc.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mDrawer.openDrawerMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO implement dark / light themeing via settings
    private int[] getMainDrawables(boolean holoDark) {
        TypedArray imgs;

        if (holoDark){
            imgs = getResources().obtainTypedArray(R.array.drawable_ids_main_dark);

        } else {
            imgs = getResources().obtainTypedArray(R.array.drawable_ids_main_light);

        }
        int[] mainSectionDrawables = new int[imgs.length()];

        for (int i = 0; i < imgs.length(); i++) {
            mainSectionDrawables[i] = imgs.getResourceId(i, 0);
        }

        return mainSectionDrawables;
    }

    // TODO implement dark / light themeing via settings
    private int[] getSecondaryDrawables(boolean holoDark) {
        TypedArray imgs;

        if (holoDark){
            imgs = getResources().obtainTypedArray(R.array.drawable_ids_secondary_dark);

        } else {
            imgs  = getResources().obtainTypedArray(R.array.drawable_ids_secondary_light);

        }

        int[] secondarySectionDrawables = new int[imgs.length()];

        for (int i = 0; i < imgs.length(); i++) {
            secondarySectionDrawables[i] = imgs.getResourceId(i, 0);
        }

        return secondarySectionDrawables;
    }

    public void onCalendarFragmentInteraction(Uri uri) {
        Toast toast = Toast.makeText(this, "HIT TEXT", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onCallbackFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, Callback.newInstance("Callback Fragment", "CallFrag"), TAG + " switched Callback to fullscreen")
                .addToBackStack("Fullscreen Callback")
                .commit();

    }

    public void googleNavMenuDrawer() {
        //Instance the GoogleNavigationDrawer and set the LayoutParams
        mDrawer = new GoogleNavigationDrawer(mContext);

        // Here we are providing data to the adapter of the ListView
        final String[] mainSections = getResources().getStringArray(R.array.navigation_main_sections);
        String[] secondarySections = getResources().getStringArray(R.array.navigation_secondary_sections);

        final String[] allSections = new String[mainSections.length + secondarySections.length];
        System.arraycopy(mainSections, 0, allSections, 0, mainSections.length);
        System.arraycopy(secondarySections, 0, allSections, mainSections.length, secondarySections.length);


        int[] mainSectionDrawables = getMainDrawables(false);  //TODO get holoDark boolean from settings
        int[] secondarySectionDrawables = getSecondaryDrawables(false); //TODO get holoDark boolean from settings

        mDrawer.setListViewSections(mainSections, // Main sections
                secondarySections, // Secondary sections
                mainSectionDrawables, // Main sections icon ids
                secondarySectionDrawables); // Secondary sections icon ids

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_mfdtailboard, null);
        mDrawer.addView(contentView, 0);

        float density = getResources().getDisplayMetrics().density;
        int padding = (int) (8 * density);

        // Now we add a clickable header and an unclickable footer to the menu
        TextView header = new TextView(this);
        header.setTextColor(Color.WHITE);
        header.setGravity(Gravity.CENTER_HORIZONTAL);
        header.setBackgroundColor(Color.DKGRAY);
        header.setText("Menu");
        header.setPadding(padding, padding, padding, padding);

        mDrawer.setMenuHeader(header, false);
        setContentView(mDrawer);

        mDrawer.setOnNavigationSectionSelected(new GoogleNavigationDrawer.OnNavigationSectionSelected() {
            @Override
            public void onSectionSelected(View v, int i, long l) {
                Toast.makeText(getBaseContext(), "Selected section: " + allSections[i-1], Toast.LENGTH_SHORT).show();
            }
        });

        //Prepare the drawerToggle in order to be able to open/close the drawer
        drawerToggle = new ActionBarDrawerToggle(this,
                mDrawer,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name);

        //Attach the DrawerListener
        mDrawer.setDrawerListener(drawerToggle);
    }
}
