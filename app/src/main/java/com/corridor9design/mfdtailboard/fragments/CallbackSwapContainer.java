package com.corridor9design.mfdtailboard.fragments;



import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corridor9design.mfdtailboard.R;
import com.corridor9design.mfdtailboard.callbackSwap.Callback;
import com.corridor9design.mfdtailboard.callbackSwap.Swap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallbackSwapContainer#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CallbackSwapContainer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CallbackSwapContainer() {
        // Required empty public constructor
    }

    public static CallbackSwapContainer newInstance(String param1, String param2) {
        CallbackSwapContainer fragment = new CallbackSwapContainer();
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

        View callback_swap_container = inflater.inflate(R.layout.fragment_callback_swap_container, container, false);
        callback_swap_container.setId(R.id.callback_swap_container);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.add(callback_swap_container.getId(), Callback.newInstance("Callback Fragment", "CallFrag"));
        ft.add(callback_swap_container.getId(), Swap.newInstance("Swap Fragment", "SwapFrag"));

        ft.commit();

        return callback_swap_container;
    }


}
