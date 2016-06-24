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
import android.widget.Button;
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
import com.course.masterex.common.Utils;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.model.ContentProfile;
import com.course.masterex.preference.AppPreference;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileFragment  extends Fragment implements ServerResponse  {

    public static String url = Constants.ProfileURL;

    JSONArray profile = null;

    private RequestQueue requestQueue;

    private StringRequest request;
    ImageView imageView;
    EditText username, pass, firstname, lastname;
    Button update;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_activity, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());

        username = (EditText) view.findViewById(R.id.etuser);
        pass= (EditText) view.findViewById(R.id.etpass);
        firstname = (EditText) view.findViewById(R.id.etfirst);
        lastname = (EditText) view.findViewById(R.id.etlast);
        imageView = (ImageView) view.findViewById(R.id.edit_image);
        update = (Button) view.findViewById(R.id.bupdate);

        pass.setClickable(false);
        pass.setFocusable(false);
        firstname.setClickable(false);
        firstname.setFocusable(false);
        lastname.setClickable(false);
        lastname.setFocusable(false);
        update.setVisibility(View.INVISIBLE);




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password  = pass.getText().toString();
                final String first  = firstname.getText().toString();
                final String last  = lastname.getText().toString();

                String url = Constants.UpdateUrL;
                Log.e("updateURL",url );


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                final String id = sharedPreferences.getString("Id", "");
                Log.e("ID", id);

                List<NameValuePair> update = new ArrayList<NameValuePair>();
                update.add(new BasicNameValuePair("_id",id));
                update.add( new BasicNameValuePair("password",password));
                update.add( new BasicNameValuePair("firstname",first));
                update.add( new BasicNameValuePair("lastname",last));

                ServerRequest serverRequest = new ServerRequest(getActivity(),url, ServiceHandler.POST, ProfileFragment.this, null,update);
                serverRequest.execute("");
            }
        });

       imageView.setClickable(false);
     imageView.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             editImage();
          }
       });
        SharedPreferences   sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("Id", "");
        Log.e("ID", id);

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

                pass.setClickable(true);
                pass.setFocusable(true);
                pass.setFocusableInTouchMode(true);
                firstname.setClickable(true);
                firstname.setFocusableInTouchMode(true);
                firstname.setFocusable(true);
                lastname.setClickable(true);
                lastname.setFocusable(true);
                lastname.setFocusableInTouchMode(true);
                update.setVisibility(View.VISIBLE);

            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();
    }

    @Override
    public void onResponse(RequestId requestId, String response) {
        try {
            JSONObject obj  = new JSONObject(response);

            boolean status = obj.getBoolean("status");
            if(status){
                String message = obj.getString("message");
                Utils.Toast(getActivity(), message);
                Log.e("message", message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}