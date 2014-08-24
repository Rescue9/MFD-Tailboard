package com.corridor9design.mfdtailboard.fragments;



import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.R;
import com.corridor9design.mfdtailboard.calendar.Calendar;
import com.corridor9design.mfdtailboard.callbackSwap.Callback;
import com.corridor9design.mfdtailboard.callbackSwap.Swap;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalendarCallbackContainer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarCallbackContainer() {
        // Required empty public constructor
    }

    public static CalendarCallbackContainer newInstance(String param1, String param2) {
        CalendarCallbackContainer fragment = new CalendarCallbackContainer();
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
        // Inflate the layout for this fragment
        View calendar_callback_container = inflater.inflate(R.layout.fragment_calendar_callback_container, container, false);
        calendar_callback_container.setId(R.id.calendar_callback_container);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.add(calendar_callback_container.getId(), Calendar.newInstance("Calendar Fragment", "CalFrag"));
        ft.add(calendar_callback_container.getId(), CallbackSwapContainer.newInstance("CallbackSwap Fragment", "CallSwapFrag"));


        ft.commit();

        return calendar_callback_container;
    }


}
