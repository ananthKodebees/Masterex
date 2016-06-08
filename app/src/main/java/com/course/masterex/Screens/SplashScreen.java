package com.course.masterex.Screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.course.masterex.R;
import com.course.masterex.base.BaseActivity;
import com.course.masterex.preference.AppPreference;


public class SplashScreen extends BaseActivity {
    private boolean loggedIn = false;


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(AppPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(AppPreference.LOGGEDIN_SHARED_PREF, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }finally {

                    if(loggedIn){

                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        finish();
                    }else {
                        startActivity(new Intent(getApplicationContext(), LoginRequestScreen.class));
                        finish();
                    }
                }
            }
        };
        timer.start();
    }
    @Override
    protected void onPause(){
        super.onPause();

        finish();
    }
}
