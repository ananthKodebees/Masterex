package com.course.masterex.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.course.masterex.R;
import com.course.masterex.adapter.SaveAdapter;
import com.course.masterex.model.ContentSaved;

import java.util.ArrayList;

/**
 * Created by DELL on 5/6/2016.
 */
public class Saved_list extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_list);
        populateUsersList();

    }
    private void populateUsersList(){

        ArrayList<ContentSaved> arrayofUsers = ContentSaved.getUsers();
        SaveAdapter adapter = new SaveAdapter(this, arrayofUsers);

        ListView listView = (ListView) findViewById(R.id.lvsaved);
        listView.setAdapter(adapter);
    }

}
