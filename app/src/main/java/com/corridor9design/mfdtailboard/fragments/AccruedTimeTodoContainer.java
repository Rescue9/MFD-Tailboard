package com.corridor9design.mfdtailboard.fragments;



import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.R;
import com.corridor9design.mfdtailboard.accruedTime.AccruedTime;
import com.corridor9design.mfdtailboard.todo.TodoFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AccruedTimeTodoContainer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AccruedTimeTodoContainer() {
        // Required empty public constructor
    }

    public static AccruedTimeTodoContainer newInstance(String param1, String param2) {
        AccruedTimeTodoContainer fragment = new AccruedTimeTodoContainer();
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
        View accrued_time_todo_container = inflater.inflate(R.layout.fragment_accrued_time_todo_container, container, false);
        accrued_time_todo_container.setId(R.id.accrued_time_todo_container);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.add(accrued_time_todo_container.getId(), AccruedTime.newInstance("Accrued Time Fragment", "AccrFrag"));
        ft.add(accrued_time_todo_container.getId(), TodoFragment.newInstance("Todo Fragment", "TodoFrag"));

        ft.commit();

        return  accrued_time_todo_container;
    }


}
