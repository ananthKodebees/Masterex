package com.course.masterex.fragment;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.course.masterex.common.Constants;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.preference.AppPreference;
import com.course.masterex.service.RequestId;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;
import com.course.masterex.service.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment implements ServerResponse{

    View view;

    public static String url = Constants.CourseURL;




    ArrayList<ContentDiscover> list = new ArrayList<>();
    private ListAdapter adap;
    JSONArray courselist = null;
    ListView listview;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view =inflater.inflate(R.layout.discover_list, container, false);

        listview = (ListView) view.findViewById(R.id.lv_users);

        ServerRequest serverRequest = new ServerRequest(getActivity(),url,ServiceHandler.GET,DiscoverFragment.this, RequestId.COURSE_REQUEST,null);
        serverRequest.execute("");
        return view;
    }



    @Override
    public void onResponse(RequestId requestId, String response) {
        if (response != null) {
            try {

                JSONObject jsonObj = new JSONObject(response);

                courselist = jsonObj.getJSONArray("courselist");

                for (int i = 0; i < courselist.length(); i++) {
                    JSONObject obj = courselist.getJSONObject(i);

                    String courseId = obj.getString("_id");
                    Log.e("courseId", courseId);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(AppPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.commit();

                    String courseName = obj.getString("courseName");
                    Log.e("courseName",courseName );
                    String courseType = obj.getString("courseType");
                    Log.e("courseType",courseType );

                    ContentDiscover course = new ContentDiscover(courseName,courseType,courseId);
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
    }
}


