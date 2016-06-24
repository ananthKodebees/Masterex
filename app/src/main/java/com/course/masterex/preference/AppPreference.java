package com.course.masterex.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.course.masterex.account.LoginScreen;
import com.course.masterex.base.BaseActivity;
import com.course.masterex.common.Constants;
import com.course.masterex.service.ServerResponse;

/**
 * Created by DELL on 6/4/2016.
 */
public class AppPreference extends BaseActivity{




    public AppPreference( SharedPreferences sharedPreferences) {

        sharedPreferences=getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE) ;
    }


}
