package com.corridor9design.mfdtailboard;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.corridor9design.mfdtailboard.calculator.Calculator;
import com.corridor9design.mfdtailboard.calendar.Calendar;

import org.arasthel.googlenavdrawermenu.views.GoogleNavigationDrawer;

public class MFDTailboard extends Activity implements
        Calendar.OnFragmentInteractionListener,
        Calculator.OnFragmentInteractionListener {

    // debug tag for logging
    public static final String TAG = "MFDTailboard";

    // declarations
    private Context mContext;
    private ActionBarDrawerToggle drawerToggle;
    private GoogleNavigationDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        //Instance the GoogleNavigationDrawer and set the LayoutParams
        mDrawer = new GoogleNavigationDrawer(mContext);

        // Here we are providing data to the adapter of the ListView
        String[] mainSections = getResources().getStringArray(R.array.navigation_main_sections);
        String[] secondarySections = getResources().getStringArray(R.array.navigation_secondary_sections);
        int[] mainSectionDrawables = getDrawables();

        mDrawer.setListViewSections(mainSections, // Main sections
                secondarySections, // Secondary sections
                mainSectionDrawables, // Main sections icon ids
                null); // Secondary sections icon ids

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_mfdtailboard, null);
        mDrawer.addView(contentView, 0);

        float density = getResources().getDisplayMetrics().density;
        int padding = (int) (8 * density);

        // Now we add a clickable header and an unclickable footer to the menu
        TextView header = new TextView(this);
        header.setTextColor(Color.WHITE);
        header.setGravity(Gravity.CENTER_HORIZONTAL);
        //header.setBackgroundResource();
        header.setText("The clickable header");
        header.setPadding(padding, padding, padding, padding);

        TextView footer = new TextView(this);
        footer.setText("The footer");
        footer.setTextColor(Color.WHITE);
        footer.setPadding(padding, padding, padding, padding);
        footer.setGravity(Gravity.CENTER_HORIZONTAL);
        footer.setBackgroundColor(Color.DKGRAY);
        mDrawer.setMenuHeaderAndFooter(header, footer, true, false);
        setContentView(mDrawer);

        mDrawer.setOnNavigationSectionSelected(new GoogleNavigationDrawer.OnNavigationSectionSelected() {
            @Override
            public void onSectionSelected(View v, int i, long l) {
                Toast.makeText(getBaseContext(), "Selected section: " + i, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, item + "", Toast.LENGTH_LONG).show();
        if (item.getItemId() == android.R.id.home) {
            if (mDrawer != null) {
                if (mDrawer.isDrawerMenuOpen()) {
                    mDrawer.closeDrawerMenu();
                } else {
                    mDrawer.isDrawerMenuOpen();
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

    private int[] getDrawables() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.drawable_ids);

        int[] mainSectionDrawables = new int[imgs.length()];

        for (int i = 0; i < imgs.length(); i++) {
            mainSectionDrawables[i] = imgs.getResourceId(i, 0);
        }

        return mainSectionDrawables;
    }

    public void onCalendarFragmentInteraction(Uri uri) {
        Toast toast = Toast.makeText(this, "HIT TEXT", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onCalculatorFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, Calculator.newInstance("Calculator Fragment", "CalcFrag"), TAG + " switched Calculator to fullscreen")
                .addToBackStack("Fullscreen Calculator")
                .commit();

    }

}
