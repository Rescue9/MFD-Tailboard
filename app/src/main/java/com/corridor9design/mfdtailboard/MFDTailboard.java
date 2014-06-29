package com.corridor9design.mfdtailboard;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.corridor9design.mfdtailboard.calculator.Calculator;
import com.corridor9design.mfdtailboard.calendar.Calendar;

public class MFDTailboard extends Activity implements
                    Calendar.OnFragmentInteractionListener,
                    Calculator.OnFragmentInteractionListener {

    // debug tag for logging
    public static final String TAG = "MFDTailboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfdtailboard);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.MFDTailboardContainer, new FragmentHandler())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mfdtailboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri){
        Toast toast = Toast.makeText(this, "HIT TEXT", Toast.LENGTH_LONG);
        toast.show();
    }
}
