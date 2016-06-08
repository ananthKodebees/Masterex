package com.course.masterex.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.course.masterex.R;
import com.course.masterex.adapter.CourseAdapter;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment implements ServerResponse{

    View view;

    public static String url ="http://192.168.1.8:3000/courselist";

    ArrayList<ContentDiscover> list = new ArrayList<>();
    private ListAdapter adap;
    JSONArray courselist = null;
    ListView listview;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view =inflater.inflate(R.layout.discover_list, container, false);

        listview = (ListView) view.findViewById(R.id.lv_users);

        ServerRequest serverRequest = new ServerRequest(getActivity(),url,DiscoverFragment.this);
        serverRequest.execute("");
        return view;
    }

    @Override
    public void onResponse(String response) {
        if (response != null) {
            try {

                JSONObject jsonObj = new JSONObject(response);

                courselist = jsonObj.getJSONArray("courselist");

                for (int i = 0; i < courselist.length(); i++) {
                    JSONObject obj = courselist.getJSONObject(i);

                    String courseId = obj.getString("courseId");
                    Log.e("courseId", courseId);
                    String courseName = obj.getString("courseName");
                    Log.e("courseName",courseName );
                    String courseType = obj.getString("courseType");
                    Log.e("courseType",courseType );

                    ContentDiscover course = new ContentDiscover(courseName,courseType,courseName);
                    list.add(course);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {

            Log.e("ServiceHandler", "coudnt get data");
        }
        adap= new CourseAdapter(list,getActivity());
        listview.setAdapter(adap);
    }}


