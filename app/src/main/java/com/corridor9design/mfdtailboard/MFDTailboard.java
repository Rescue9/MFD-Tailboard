package com.corridor9design.mfdtailboard;

import android.app.Activity;
import android.app.FragmentManager;
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
import com.corridor9design.mfdtailboard.callbackSwap.Callback;
import com.corridor9design.mfdtailboard.callbackSwap.Swap;
import com.corridor9design.mfdtailboard.fragments.CallbackSwapContainer;
import com.corridor9design.mfdtailboard.fragments.Overview;
import com.corridor9design.mfdtailboard.settings.Settings;
import com.corridor9design.mfdtailboard.todo.TodoFragment;

import org.arasthel.googlenavdrawermenu.views.GoogleNavigationDrawer;

import java.sql.Array;
import java.util.Arrays;


public class MFDTailboard extends Activity implements
        Calendar.OnFragmentInteractionListener,
        Callback.OnFragmentInteractionListener,
        Calculator.OnFragmentInteractionListener,
        AccruedTime.OnFragmentInteractionListener,
        TodoFragment.OnFragmentInteractionListener,
        TodoFragment.OnTodoItemSelectedListener,
        Swap.OnFragmentInteractionListener {

    // debug tag for logging
    public static final String TAG = "MFDTailboard";
    public static final boolean DEBUG = true;

    // declarations GoogleNavMenuDrawer
    private static Context mContext;
    private ActionBarDrawerToggle mDrawerToggle;
    private GoogleNavigationDrawer mDrawer;

    // string array for fragment tag - number conversion
    String[] fragmentTag = { "Overview", "Calculator", "Calendar", "CallbackSwap", "AccruedTime", "Todo", "Settings", "Info" };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //logcat for initial start
        if (DEBUG) Log.d(TAG, "Starting...");
        mContext = this;

        googleNavMenuDrawer();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // start overview fragment initially
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.MFDTailboardContainer, new Overview(), "Overview")
                    .addToBackStack("Overview")
                    .commit();
        }
        // highlight overview in google nav menu on initial launch
        selectProperDrawerMenu(1);

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
        //getMenuInflater().inflate(R.menu.mfdtailboard, menu); // disable to remove 3-dot menu
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

        /*// setup typical 3-dot menu for more permanent items
        // settings, about, contact, etc.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mDrawer.openDrawerMenu();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getFragmentManager();
        String stackName = null;
        int backStackCount;
        for (backStackCount = 0; backStackCount < fm.getBackStackEntryCount(); backStackCount++) {
            stackName = fm.getBackStackEntryAt(backStackCount).getName();
        }
        Log.d("StackName", "" + stackName);
        Log.d("BackStack Count", "" + backStackCount);
        selectProperDrawerMenu(Arrays.asList(fragmentTag).indexOf(stackName) + 1);

        if (backStackCount < 1) {
            finish();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    // TODO implement dark / light themeing via settings
    private int[] getMainDrawables(boolean holoDark) {
        TypedArray mImgs;

        if (holoDark) {
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

        if (holoDark) {
            mImgs = getResources().obtainTypedArray(R.array.drawable_ids_secondary_dark);

        } else {
            mImgs = getResources().obtainTypedArray(R.array.drawable_ids_secondary_light);

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
            public void onSectionSelected(View v, int position, long id) {

                switch (position) {
                    case 1:
                        Overview oviewVisible = (Overview) getFragmentManager().findFragmentByTag("Overview");
                        if (oviewVisible == null || !oviewVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, Overview.newInstance("Overview", "OverFrag"), "Overview")
                                    .addToBackStack("Overview")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();

                            break;
                        }

                    case 2:
                        Calculator calcVisible = (Calculator) getFragmentManager().findFragmentByTag("Calculator");
                        if (calcVisible == null || !calcVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, Calculator.newInstance("CalcFrag", "CalcFull"), "Calculator")
                                    .addToBackStack("Calculator")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 3:
                        Calendar calVisible = (Calendar) getFragmentManager().findFragmentByTag("Calendar");
                        if (calVisible == null || !calVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, Calendar.newInstance("CalFrag", "CalFull"), "Calendar")
                                    .addToBackStack("Calendar")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 4:
                        Callback callVisible = (Callback) getFragmentManager().findFragmentByTag("CallbackSwap");
                        if (callVisible == null || !callVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, CallbackSwapContainer.newInstance("CallFrag", "CallFull"), "CallbackSwap")
                                    .addToBackStack("CallbackSwap")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 5:
                        AccruedTime accrVisible = (AccruedTime) getFragmentManager().findFragmentByTag("AccruedTime");
                        if (accrVisible == null || !accrVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, AccruedTime.newInstance("AccrFrag", "AccrFull"), "AccruedTime")
                                    .addToBackStack("AccruedTime")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 6:
                        TodoFragment todoVisible = (TodoFragment) getFragmentManager().findFragmentByTag("Todo");
                        if (todoVisible == null || !todoVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, TodoFragment.newInstance(getContext(), "TodoFrag", "TodoFull"), "Todo")
                                    .addToBackStack("Todo")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 7:
                        Settings settVisible = (Settings) getFragmentManager().findFragmentByTag("Settings");
                        if (settVisible == null || !settVisible.isVisible()) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.MFDTailboardContainer, Settings.newInstance("Settings Fragment", "SetFrag"), "Settings")
                                    .addToBackStack("Settings")
                                    .commit();
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "The " + mAllSections[position - 1] + " is currently in view", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 8:
                        break;

                    default:
                        break;
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

    public void selectProperDrawerMenu(int position) {
        mDrawer.check(position);
    }

    public void onCalculatorFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, Calculator.newInstance("CalcFrag", "CalcFull"), "Calculator")
                .addToBackStack("Calculator")
                .commit();
    }

    public void onCalendarFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, Calendar.newInstance("CalFrag", "CalFull"), "Calendar")
                .addToBackStack("Calendar")
                .commit();
    }

    public void onCallbackFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, CallbackSwapContainer.newInstance("CallFrag", "CallFull"), "CallbackSwap")
                .addToBackStack("CallbackSwap")
                .commit();
    }

    public void onSwapFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, CallbackSwapContainer.newInstance("SwapFrag", "SwapFull"), "CallbackSwap")
                .addToBackStack("CallbackSwap")
                .commit();
    }

    public void onAccruedTimeFragmentInteraction(Uri uri) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, AccruedTime.newInstance("AccrFrag", "AccrFull"), "AccruedTime")
                .addToBackStack("AccruedTime")
                .commit();
    }

    @Override
    public void onTodoFragmentInteraction(String id) {
        getFragmentManager().beginTransaction()
                .replace(R.id.MFDTailboardContainer, TodoFragment.newInstance(this, "TodoFrag", "TodoFull"), "Todo")
                .addToBackStack("Todo")
                .commit();

    }

    @Override
    public void onTodoItemSelected(int id) {
        Toast.makeText(this, "TOASTED! " + id, Toast.LENGTH_SHORT).show();
    }
}
