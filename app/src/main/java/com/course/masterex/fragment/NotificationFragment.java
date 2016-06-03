package com.course.masterex.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.masterex.R;

/**
 * Created by DELL on 5/6/2016.
 */
public class NotificationFragment extends Fragment {


    View view;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =   inflater.inflate(R.layout.saved_list, container, false);
        return view;
    }


}
