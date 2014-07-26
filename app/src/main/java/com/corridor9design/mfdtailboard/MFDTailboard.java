package com.corridor9design.mfdtailboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.corridor9design.mfdtailboard.accruedTime.AccruedTime;
import com.corridor9design.mfdtailboard.calculator.Calculator;
import com.corridor9design.mfdtailboard.calendar.Calendar;
import com.corridor9design.mfdtailboard.callback.Callback;
import com.corridor9design.mfdtailboard.fragments.Overview;
import com.corridor9design.mfdtailboard.settings.Settings;
import com.corridor9design.mfdtailboard.todo.Todo;

import org.arasthel.googlenavdrawermenu.views.GoogleNavigationDrawer;


public class MFDTailboard extends Activity implements
        Calendar.OnFragmentInteractionListener,
        Callback.OnFragmentInteractionListener,
        Calculator.OnFragmentInteractionListener,
        AccruedTime.OnFragmentInteractionListener,
        Todo.OnFragmentInteractionListener {

    // debug tag for logging
    public static final String TAG = "MFDTailboard";
    public static final boolean DEBUG = true;

    // declarations GoogleNavMenuDrawer
    private static Context mContext;
    private ActionBarDrawerToggle mDrawerToggle;
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
                    .add(R.id.MFDTailboardContainer, new Overview())
                    .commit();
        }

        // set default setting values. necessary for initial run
        PreferenceManager.setDefaultValues(this, R.xml.settings_layout, false);

        //TODO: Configure screen orientation for tablets, individual fragments, and menu operations
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
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

    public static Context getContext() {
        return mContext;
    }

    // TODO implement dark / light themeing via settings
    private int[] getMainDrawables(boolean holoDark) {
        TypedArray mImgs;

        if (holoDark){
            mImgs = getResources().obtainTypedArray(R.array.drawable_ids_main_dark);

        } else {
            mImgs = getResources().obtainTypedArray(R.array.drawable_ids_main_light);

        }
        int[] mainSectionDrawables = new int[mImgs.length()];

        for (int i = 0; i < mImgs.length(); i++) {
            mainSectionDrawables[i] = mImgs.getResourceId(i, 0);
        }

        return mainSectionDrawables;
    }

    // TODO implement dark / light themeing via settings
    private int[] getSecondaryDrawables(boolean holoDark) {
        TypedArray mImgs;

        if (holoDark){
            mImgs = getResources().obtainTypedArray(R.array.drawable_ids_secondary_dark);

        } else {
            mImgs  = getResources().obtainTypedArray(R.array.drawable_ids_secondary_light);

        }

        int[] secondarySectionDrawables = new int[mImgs.length()];

        for (int i = 0; i < mImgs.length(); i++) {
            secondarySectionDrawables[i] = mImgs.getResourceId(i, 0);
        }

        return secondarySectionDrawables;
    }

    public void googleNavMenuDrawer() {
        //Instance the GoogleNavigationDrawer and set the LayoutParams
        mDrawer = new GoogleNavigationDrawer(mContext);

        // Here we are providing data to the adapter of the ListView
        final String[] mMainSections = getResources().getStringArray(R.array.navigation_main_sections);
        String[] mSecondarySections = getResources().getStringArray(R.array.navigation_secondary_sections);

        final String[] mAllSections = new String[mMainSections.length + mSecondarySections.length];
        System.arraycopy(mMainSections, 0, mAllSections, 0, mMainSections.length);
        System.arraycopy(mSecondarySections, 0, mAllSections, mMainSections.length, mSecondarySections.length);


        int[] mMainSectionDrawables = getMainDrawables(false);  //TODO get holoDark boolean from settings
        int[] mSecondarySectionDrawables = getSecondaryDrawables(false); //TODO get holoDark boolean from settings

        mDrawer.setListViewSections(mMainSections, // Main sections
                mSecondarySections, // Secondary sections
                mMainSectionDrawables, // Main sections icon ids
                mSecondarySectionDrawables); // Secondary sections icon ids

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_mfdtailboard, null);
        mDrawer.addView(contentView, 0);

        float density = getResources().getDisplayMetrics().density;
        int padding = (int) (8 * density);

        // Now we add a clickable header to the menu
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

                if (i == 7){
                    getFragmentManager().beginTransaction()
                            .replace(R.id.MFDTailboardContainer, Settings.newInstance("Settings Fragment", "SetFrag"), TAG + " switched Settings to fullscreen")
                            .addToBackStack("Fullscreen Settings")
                            .commit();

                } else {
                    Toast.makeText(getBaseContext(), "Selected section: " + mAllSections[i - 1], Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Prepare the mDrawerToggle in order to be able to open/close the drawer
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawer,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name);

        //Attach the DrawerListener
        mDrawer.setDrawerListener(mDrawerToggle);
    }

    public void onCalendarFragmentInteraction(Uri uri) {
        String callendarViewParent = findViewById(R.id.calendar_fragment).getParent().toString();

        if (callendarViewParent.contains("id/calendar_callback_container")) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.MFDTailboardContainer, Calendar.newInstance("CalFrag", "CalFull"))
                    .addToBackStack("Fullscreen Calendar")
                    .commit();

        }
    }

    public void onCallbackFragmentInteraction(Uri uri) {
        String callbackViewParent = findViewById(R.id.callback_fragment).getParent().toString();

        if (callbackViewParent.contains("id/calendar_callback_container")) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.MFDTailboardContainer, Callback.newInstance("CallFrag", "CallFull"))
                    .addToBackStack("Fullscreen Callback")
                    .commit();
        }
    }

    public void onCalculatorFragmentInteraction(Uri uri) {
        String calculatorViewParent = findViewById(R.id.calculator_fragment).getParent().toString();

        if (calculatorViewParent.contains("id/calculator_container")) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.MFDTailboardContainer, Calculator.newInstance("CalcFrag", "CalcFull"))
                    .addToBackStack("Fullscreen Calculator")
                    .commit();
        }
    }

    public void onAccruedTimeFragmentInteraction(Uri uri) {
        String accruedTimeViewParent = findViewById(R.id.accrued_time_fragment).getParent().toString();

        if (accruedTimeViewParent.contains("id/accrued_time_todo_container")) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.MFDTailboardContainer, AccruedTime.newInstance("AccrFrag", "AccrFull"))
                    .addToBackStack("Fullscreen AccruedTime")
                    .commit();
        }
    }

    public void onTodoFragmentInteraction(Uri uri) {
        String todoViewParent = findViewById(R.id.todo_fragment).getParent().toString();

        if (todoViewParent.contains("accrued_time_todo_container")) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.MFDTailboardContainer, Todo.newInstance("TodoFrag", "TodoFull"))
                    .addToBackStack("Fullscreen Todo")
                    .commit();
        }
    }
}
