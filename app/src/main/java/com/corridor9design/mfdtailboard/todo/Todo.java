package com.corridor9design.mfdtailboard.todo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.corridor9design.mfdtailboard.R;
import com.leocardz.aelv.library.Aelv;
import com.leocardz.aelv.library.AelvCustomAction;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Todo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Todo#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Todo extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // declare todo item list
    private ArrayList<ListItem> mListItems;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Todo.
     */
    // TODO: Rename and change types and number of parameters
    public static Todo newInstance(String param1, String param2) {
        Todo fragment = new Todo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public Todo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        if (mParam2 != "TodoFull") {
            view.setOnClickListener(this);
        }

        ListView mListView = (ListView) view.findViewById(R.id.todo_list);

        mListItems = new ArrayList<ListItem>();
        mockItems();

        ListAdapter adapter = new ListAdapter(getActivity(), R.layout.todo_list_item, mListItems);

        mListView.setAdapter(adapter);

        // Setup
        // Aelv aelv = new Aelv(true, 200, listItems, listView, adapter);
        final Aelv aelv = new Aelv(true, 200, mListItems, mListView, adapter, new AelvCustomAction() {
            @Override
            public void onEndAnimation(int position) {
                mListItems.get(position).setDrawable(mListItems.get(position)
                        .isOpen() ? R.drawable.ic_list_chevron_up_light : R.drawable.ic_list_chevron_down_light);
            }
        });

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aelv.toggle(view, position);
            }
        });

        return view;
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTodoFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) { mListener.onTodoFragmentInteraction(null);}

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onTodoFragmentInteraction(Uri uri);
    }

    // mock list items for testing
    // TODO: replace with working implementation for user input items.
    private void mockItems() {
        final int COLLAPSED_HEIGHT_1 = 50, COLLAPSED_HEIGHT_2 = 100, COLLAPSED_HEIGHT_3 = 150;
        final int EXPANDED_HEIGHT_1 = 150, EXPANDED_HEIGHT_2 = 250, EXPANDED_HEIGHT_3 = 350, EXPANDED_HEIGHT_4 = 400;

        ListItem listItem = new ListItem("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_1, EXPANDED_HEIGHT_1, false);
        mListItems.add(listItem);

        listItem = new ListItem("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_2, EXPANDED_HEIGHT_2, false);
        mListItems.add(listItem);

        listItem = new ListItem("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_3, EXPANDED_HEIGHT_3, false);
        mListItems.add(listItem);

        listItem = new ListItem("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_2, EXPANDED_HEIGHT_4, false);
        mListItems.add(listItem);

        listItem = new ListItem("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_1, EXPANDED_HEIGHT_4, false);
        mListItems.add(listItem);

        listItem = new ListItem("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_2, EXPANDED_HEIGHT_4, false);
        mListItems.add(listItem);

        listItem = new ListItem("Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_3, EXPANDED_HEIGHT_3, false);
        mListItems.add(listItem);

        listItem = new ListItem("Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.");
        // setUp IS REQUIRED
        listItem.setUp(COLLAPSED_HEIGHT_1, EXPANDED_HEIGHT_4, false);
        mListItems.add(listItem);
    }
}
