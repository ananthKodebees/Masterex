package com.course.masterex.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by DELL on 6/4/2016.
 */
public class Utils {

    public static void Toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
