package com.course.masterex.Screens;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.course.masterex.R;
import com.course.masterex.account.LoginScreen;
import com.course.masterex.adapter.PagerAdapter;
import com.course.masterex.preference.AppPreference;
import com.viewpagerindicator.TabPageIndicator;


public class HomeScreen extends ActionBarActivity {

    ViewPager Tab;
    PagerAdapter TabAdapter;
    TabPageIndicator tabPageIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        TabAdapter = new PagerAdapter(getSupportFragmentManager());

        tabPageIndicator = (TabPageIndicator)findViewById(R.id.indicator);

        Tab = (ViewPager)findViewById(R.id.pager);

        Tab.setAdapter(TabAdapter);

        tabPageIndicator.setViewPager(Tab);

    }


    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPreferences preferences = getSharedPreferences(AppPreference.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putBoolean(AppPreference.LOGGEDIN_SHARED_PREF, false);
                        editor.putString(AppPreference.USER_NAME, "");
                        editor.commit();

                        Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
