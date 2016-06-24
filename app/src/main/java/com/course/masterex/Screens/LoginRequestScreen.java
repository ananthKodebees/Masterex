package com.course.masterex.Screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.course.masterex.R;
import com.course.masterex.account.LoginScreen;
import com.course.masterex.account.RegisterScreen;
import com.course.masterex.base.BaseActivity;

public class LoginRequestScreen extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void next_activity(View view)
    {
        Intent intent = new Intent(LoginRequestScreen.this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
    public void register_activity(View view)
    {
        Intent intent = new Intent(LoginRequestScreen.this, RegisterScreen.class);
        startActivity(intent);
        finish();
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
