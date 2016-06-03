package com.course.masterex.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.course.masterex.R;
import com.course.masterex.adapter.CourseAdapter;
import com.course.masterex.model.ContentDiscover;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment {

    View view;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view =inflater.inflate(R.layout.discover_list, container, false);



        populateUsersList();

        return view;




    }



    private void populateUsersList(){

        ArrayList<ContentDiscover> arrayofUsers = ContentDiscover.getUsers();
        CourseAdapter adapter = new CourseAdapter(getActivity(), arrayofUsers);

        ListView listView = (ListView) view.findViewById(R.id.lv_users);
        listView.setAdapter(adapter);
    }



}
