package com.course.masterex.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.course.masterex.R;
import com.course.masterex.adapter.CourseAdapter;
import com.course.masterex.model.ContentDiscover;

import java.util.ArrayList;


public class Discover_list extends Activity{

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discover_list);
        populateUsersList();

    }
    private void populateUsersList(){

        ArrayList<ContentDiscover> arrayofUsers = ContentDiscover.getUsers();
        CourseAdapter adapter = new CourseAdapter(this, arrayofUsers);

        ListView listView = (ListView) findViewById(R.id.lv_users);
        listView.setAdapter(adapter);
    }
}
