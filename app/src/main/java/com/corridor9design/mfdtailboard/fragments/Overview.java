package com.corridor9design.mfdtailboard.fragments;



import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Overview extends Fragment {


    public Overview() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View overview_container =  inflater.inflate(R.layout.fragment_overview, container, false);
        overview_container.setId(R.id.overview_container);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(overview_container.getId(), CalendarCallbackContainer.newInstance("Calendar Callback Container", "CalCallCont"));
        ft.add(overview_container.getId(), CalculatorContainer.newInstance("Calculator Container", "CalcCont"));
        ft.add(overview_container.getId(), AccruedTimeTodoContainer.newInstance("Accrued Time Todo Container", "AccTodoCont"));

        ft.commit();

        return overview_container;
    }


}
