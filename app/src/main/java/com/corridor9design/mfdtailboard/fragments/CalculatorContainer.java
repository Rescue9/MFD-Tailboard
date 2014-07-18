package com.corridor9design.mfdtailboard.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.R;
import com.corridor9design.mfdtailboard.calculator.Calculator;

public class CalculatorContainer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorContainer() {
        //Required empty public constructor
    }

    public static CalculatorContainer newInstance(String param1, String param2) {
        CalculatorContainer fragment = new CalculatorContainer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View calculator_container = inflater.inflate(R.layout.fragment_calculator_container, container, false);
        calculator_container.setId(R.id.calculator_container);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.add(calculator_container.getId(), Calculator.newInstance("Calculator Fragment", "CalcFrag"));

        ft.commit();

        return calculator_container;
    }
}