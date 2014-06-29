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

        //View calendar_calculator_container = inflater.inflate(R.layout.fragment_cal_calc, container, false);
        //calendar_calculator_container.setId(R.id.calendar_calculator_container);
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();

        //ft.add(calendar_calculator_container.getId(), Calendar.newInstance("Calendar Fragment", "CalFrag"));
        ft.add(calendar_calculator_container.getId(), Calculator.newInstance("Calculator Fragment", "CalcFrag"));
        ft.commit();*/

        return inflater.inflate(R.layout.fragment_cal_calc, container, false) ;
    }
}