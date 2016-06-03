package com.course.masterex.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.course.masterex.fragment.DiscoverFragment;
import com.course.masterex.fragment.NotificationFragment;
import com.course.masterex.fragment.ProfileFragment;
import com.course.masterex.fragment.SavedFragment;

/**
 * Created by DELL on 5/6/2016.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter{

    String[] title = {"Discover","Saved","Notification","Profile"};


    public PagerAdapter(FragmentManager fm){

        super(fm);


    }

    @Override
    public Fragment getItem(int position){

        switch (position) {
            case 0:
                DiscoverFragment tab1 = new DiscoverFragment();
                return tab1;
            case 1:
                SavedFragment tab2 = new SavedFragment();
                return tab2;
            case 2:
                NotificationFragment tab3 = new NotificationFragment();

                return tab3;

            case 3:
                ProfileFragment tab4 = new ProfileFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return title[position];
    }


    @Override
    public int getCount(){

        return title.length;
    }

}
