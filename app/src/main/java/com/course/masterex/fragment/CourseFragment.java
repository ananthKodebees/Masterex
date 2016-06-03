package com.course.masterex.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.course.masterex.R;

/**
 * Created by DELL on 5/6/2016.
 */
public class CourseFragment extends Fragment{

    ListView courseListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.course_fragment, container, false);


    }
}
