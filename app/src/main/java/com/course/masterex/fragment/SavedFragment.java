package com.course.masterex.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.course.masterex.R;
import com.course.masterex.adapter.SaveAdapter;
import com.course.masterex.model.ContentSaved;

import java.util.ArrayList;


public class SavedFragment  extends Fragment {

    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saved_list, container, false);


        return view;
    }


}
