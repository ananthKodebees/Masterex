package com.course.masterex.model;

import java.util.ArrayList;

/**
 * Created by DELL on 5/6/2016.
 */
public class ContentSaved {

    public String name;
    public String description;
    public String participants;
    public String course;


    public ContentSaved(String name, String description, String participants, String course ){

        this.name = name;
        this.description = description;
        this.participants = participants;
        this.course = course;
    }

    public static ArrayList<ContentSaved> getUsers(){
        ArrayList<ContentSaved> user = new ArrayList<ContentSaved>();
        user.add(new ContentSaved("A Course on C", "Course Start date", "200 Participants","A course on C"));
        user.add(new ContentSaved("A Course on IOS", "Course Start date", "200 Participants","A course on IOS"));
        user.add(new ContentSaved("A Course on PHP", "Course Start date", "200 Participants","A course on PHP"));
        user.add(new ContentSaved("A Course on ANDROID", "Course Start date", "200 Participants","A course on ANDROID"));
        user.add(new ContentSaved("A Course on C++", "Course Start date", "200 Participants","A course on C++"));
        user.add(new ContentSaved("A Course on JAVA", "Course Start date", "200 Participants", "A course on JAVA"));
        user.add(new ContentSaved("A Course on WEB", "Course Start date", "200 Participants","A course on WEB"));
        user.add(new ContentSaved("A Course on C#", "Course Start date", "200 Participants","A course on C#"));
        return user;
    }
}


