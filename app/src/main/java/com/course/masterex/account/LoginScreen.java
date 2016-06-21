package com.course.masterex.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.course.masterex.Screens.HomeScreen;
import com.course.masterex.R;
import com.course.masterex.common.Constants;
import com.course.masterex.common.Utils;
import com.course.masterex.fragment.ProfileFragment;
import com.course.masterex.preference.AppPreference;
import com.course.masterex.service.RequestId;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;
import com.course.masterex.service.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginScreen extends ActionBarActivity implements ServerResponse {
    EditText username, password;
    Button signin;
    private RequestQueue requestQueue;

    private StringRequest request;
    private boolean loggedIn = false;

    private String url = Constants.LoginURL;

SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        signin = (Button) findViewById(R.id.blogin);
        requestQueue = Volley.newRequestQueue(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<NameValuePair> loginValues = new ArrayList<NameValuePair>();
                loginValues.add(new BasicNameValuePair("username", username.getText().toString()));
                loginValues.add(new BasicNameValuePair("password", password.getText().toString()));

                ServerRequest serverRequest = new ServerRequest(LoginScreen.this, url, ServiceHandler.POST, LoginScreen.this, null, loginValues);
                serverRequest.execute("");


            }
        });
    }

    @Override
    public void onResponse(RequestId requestId, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.e("ananth", response );
            Log.e("url", url);
            boolean status =  jsonObject.getBoolean("status");
            String id = jsonObject.getString("id");
            Log.e("id", id);
            String message = jsonObject.getString("message");
            Log.e("message",message );

            if(status){

                sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean(Constants.LOGGEDIN_SHARED_PREF, true);
                editor.putString(Constants.USER_NAME, username.getText().toString());

                editor.putString("Id", id);
                editor.commit();

                Utils.Toast(getApplicationContext(),message);
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                finish();
            }
            else {
                Utils.Toast(getApplicationContext(),message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}


