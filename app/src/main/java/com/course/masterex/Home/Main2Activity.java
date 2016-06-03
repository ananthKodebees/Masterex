package com.course.masterex.Home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.course.masterex.R;
import com.course.masterex.adapter.PagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by DELL on 5/6/2016.
 */
public class Main2Activity extends FragmentActivity {

    ViewPager Tab;
    PagerAdapter TabAdapter;
    TabPageIndicator tabPageIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);



        TabAdapter = new PagerAdapter(getSupportFragmentManager());

        tabPageIndicator = (TabPageIndicator)findViewById(R.id.indicator);

        Tab = (ViewPager)findViewById(R.id.pager);

        Tab.setAdapter(TabAdapter);

        tabPageIndicator.setViewPager(Tab);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
