package com.course.masterex.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;


public class LoginScreen extends Activity {
    EditText username, password;
    Button signin;
    private RequestQueue requestQueue;

    private StringRequest request;
    private boolean loggedIn = false;

    private String URL = Constants.baseURL+Constants.loginURL;

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

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("ananth", response );
                                Log.e("url", URL);
                          boolean status =  jsonObject.getBoolean("status");
                            String id = jsonObject.getString("id");
                            Log.e("id", id);

                            if(status){

                          sharedPreferences = getSharedPreferences(AppPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean(AppPreference.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(AppPreference.USER_NAME, username.getText().toString());

                                editor.putString("Id",id);
                                editor.commit();

                                Utils.Toast(getApplicationContext(),jsonObject.getString("message"));

                                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                finish();
                            }
                            else {
                                Utils.Toast(getApplicationContext(), jsonObject.getString("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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
                        hashMap.put("username",username.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return  hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });

    }

    private void method() {
    }
}


