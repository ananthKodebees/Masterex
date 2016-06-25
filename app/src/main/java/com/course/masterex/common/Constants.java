package com.course.masterex.common;

import android.content.Context;
import android.content.Intent;

import java.net.URL;


public class Constants {


    public  static final String baseURL = "http://192.168.1.7:3000/";

 public static  final String LoginURL = baseURL+"login";

    public static final String RegisterURL = baseURL+"register";

    public static final String CourseURL = baseURL + "courselist";

    public  static  final String ProfileURL = baseURL + "profile";

    public  static  final String SavedUrL = baseURL + "saveCourse";

    public  static  final String UpdateUrL = baseURL + "updateProfile";

    public static final String SaveUrL = baseURL + "savedCourseList";

    public static final String SHARED_PREF_NAME = "Masterex";

    public static final String USER_NAME = "user";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

   public  static final String SENDER_ID = "94719058979";

    static final String TAG = "Masterex GCM";


    static final String EXTRA_MESSAGE = "message";

}

