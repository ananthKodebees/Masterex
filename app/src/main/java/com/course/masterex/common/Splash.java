package com.course.masterex.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.course.masterex.Home.Main2Activity;
import com.course.masterex.Home.MainActivity;
import com.course.masterex.R;


public class Splash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }finally {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
