package com.course.masterex.common;

import android.content.Context;
import android.widget.Toast;


public class Utils {

    public static void Toast(Context context, String text){

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
