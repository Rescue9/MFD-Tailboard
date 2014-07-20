package com.corridor9design.mfdtailboard.settings;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Debug;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corridor9design.mfdtailboard.MFDTailboard;
import com.corridor9design.mfdtailboard.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Settings extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // SharedPreference string instantiation
    public static final String THEME = "setting_theme";
    public static final String CURRENT_RANK = "setting_rank";
    public static final String CURRENT_RANK_INT = "setting_rank_int";
    public static final String MARITAL_STATUS = "setting_marital_status";
    public static final String EXEMPTIONS = "setting_exemptions";
    public static final String ADD_FEDERAL_EXEMPTIONS = "setting_add_federal_exemptions";
    public static final String ADD_STATE_EXEMPTIONS = "setting_add_state_exemptions";

    // DecimalFormation instantiation
    DecimalFormat df = new DecimalFormat("$##0.00");

    // setup sharedPreferences & preferences editor
    //SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MFDTailboard.getContext());
    //SharedPreferences.Editor mEditor = mPrefs.edit();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public Settings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MFDTailboard.DEBUG) {
            Log.d(MFDTailboard.TAG, "Starting Settings");
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        addPreferencesFromResource(R.xml.settings_layout);
    }

    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
                this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if (MFDTailboard.DEBUG) {
            Log.d(MFDTailboard.TAG, "Preference changed: " + s);
        }

    }
}
