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
import com.course.masterex.adapter.SaveAdapter;
import com.course.masterex.common.Constants;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.model.ContentSaved;
import com.course.masterex.service.RequestId;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;
import com.course.masterex.service.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SavedFragment  extends Fragment  implements ServerResponse {

    View view;
    public static String url = Constants.SaveUrL;
    ArrayList<ContentSaved> list = new ArrayList<>();
    private ListAdapter adapter;
    ListView listview;
    JSONArray savedlist = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saved_list, container, false);

        listview = (ListView) view.findViewById(R.id.lvsaved);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("Id", "");
        Log.e("ID", id);

        List<NameValuePair> value = new ArrayList<NameValuePair>();
        value.add(new BasicNameValuePair("_id", id));
        ServerRequest serverRequest = new ServerRequest(getActivity(), url, ServiceHandler.POST, SavedFragment.this, null, value);
        serverRequest.execute("");
        return view;
    }


    @Override
    public void onResponse(RequestId requestId, String response) {
        if (response != null) {
            try {

                JSONObject jsonObj = new JSONObject(response);
                savedlist = jsonObj.getJSONArray("courselist");

                for (int i = 0; i < savedlist.length(); i++) {
                    JSONObject obj = savedlist.getJSONObject(i);

                    JSONArray saved = obj.getJSONArray("courseList");
                for (int j = 0; j <saved.length();j++){
                        JSONObject object = saved.getJSONObject(j);

                        String courseName = object.getString("courseName");
                        Log.e("saved", courseName);
                        String courseType = object.getString("courseType");
                        ContentSaved save = new ContentSaved(courseName, courseType, courseName);
                        list.add(save);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {

            Log.e("ServiceHandler", "coudnt get data");
        }
        adapter = new SaveAdapter(list,getActivity());
        listview.setAdapter(adapter);
    }
}