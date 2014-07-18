package com.corridor9design.mfdtailboard;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.callback.Callback;
import com.corridor9design.mfdtailboard.calendar.Calendar;

public class FragmentHandler extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View calendar_callback_container = inflater.inflate(R.layout.fragment_calendar_callback, container, false);
        calendar_callback_container.setId(R.id.calendar_callback_container);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(calendar_callback_container.getId(), Calendar.newInstance("Calendar Fragment", "CalFrag"));
        ft.add(calendar_callback_container.getId(), Callback.newInstance("Callback Fragment", "CallFrag"));
        ft.commit();

        return calendar_callback_container;
    }
}