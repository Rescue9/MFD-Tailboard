package com.corridor9design.mfdtailboard;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.corridor9design.mfdtailboard.calendar.Calendar;
import com.corridor9design.mfdtailboard.calculator.Calculator;

public class FragmentHandler extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cal_calc, container, false) ;
    }
}