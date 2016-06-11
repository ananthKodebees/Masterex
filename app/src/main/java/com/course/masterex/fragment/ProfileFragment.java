package com.course.masterex.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.course.masterex.R;
import com.course.masterex.account.LoginScreen;
import com.course.masterex.common.Constants;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.model.ContentProfile;
import com.course.masterex.preference.AppPreference;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProfileFragment  extends Fragment  {

    public static String url = Constants.baseURL+Constants.profileURL;

    JSONArray profile = null;

    private RequestQueue requestQueue;

    private StringRequest request;
    ImageView imageView;
    EditText username, pass, firstname, lastname;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_activity, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());

        username = (EditText) view.findViewById(R.id.etuser);
        pass= (EditText) view.findViewById(R.id.etpass);
        firstname = (EditText) view.findViewById(R.id.etfirst);
        lastname = (EditText) view.findViewById(R.id.etlast);

//         imageView.setClickable(true);
  //    imageView.setOnClickListener(new View.OnClickListener() {
   //       @Override
   //       public void onClick(View v) {
   //           editImage();
    //      }
   //    });
        SharedPreferences   sharedPreferences = getActivity().getSharedPreferences(AppPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("Id", "");
        Log.e("ID",id );

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("ananth", response);
                    Log.e("url",url);
                    boolean status =  jsonObject.getBoolean("status");
                    Log.e("Status", String.valueOf(status));

        if (status) {
JSONObject profile = (new JSONObject(response)).getJSONObject("profile");

        String userName = profile.getString("username");
              Log.e("username", userName);
          String password = profile.getString("password");
    Log.e("password", password);
    String firstName = profile.getString("firstname");
    Log.e("firstname", firstName);
    String lastName = profile.getString("lastname");
    Log.e("lastname", lastName);


    username.setText(userName);
    firstname.setText(firstName);
    lastname.setText(lastName);
      pass.setText(password);

}
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",""+error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("_id",id);

                return  hashMap;
            }
        };
        requestQueue.add(request);
        return view;
    }

    public void editImage() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(R.string.edit_alert);
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();
    }
}