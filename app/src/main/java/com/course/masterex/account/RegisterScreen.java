package com.course.masterex.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterScreen extends AppCompatActivity {
    EditText registeruser, registerpass, firstname, lastname;
    Button register;

    private RequestQueue requestQueue;


    private String URL =  Constants.baseURL+Constants.registerURL;
    private StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registeruser = (EditText) findViewById(R.id.etregisteruser);
        registerpass = (EditText) findViewById(R.id.etregisterpass);
        firstname = (EditText) findViewById(R.id.etfirstname);
        lastname = (EditText)findViewById(R.id.etlastname);
        register = (Button) findViewById(R.id.bregister);
        requestQueue = Volley.newRequestQueue(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String reguser = registeruser.getText().toString();
                final String regpass = registerpass.getText().toString();
                final String fname = firstname.getText().toString();
                final String lname = lastname.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Log.e("register", response);
                                    Log.e("URL",URL );
                                    boolean status = jsonObject.getBoolean("status");
                                    if (status) {
                                        Utils.Toast(getApplicationContext(),jsonObject.getString("message"));
                                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                        finish();
                                    } else {
                                        Utils.Toast(getApplicationContext(), jsonObject.getString("message"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error", "" + error.getMessage());

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("username", reguser);
                        hashMap.put("password", regpass);
                        hashMap.put("firstname", fname);
                        hashMap.put("lastname", lname);
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
         return true;
        }

        return super.onOptionsItemSelected(item);
    }


    }
