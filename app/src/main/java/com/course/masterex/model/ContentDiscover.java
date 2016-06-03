package com.course.masterex.model;

import java.util.ArrayList;


public class ContentDiscover {

    public String name;
    public String description;
    public String participants;





    public ContentDiscover(String name, String description, String participants){

        this.name = name;
        this.description = description;
        this.participants = participants;

    }

    public static ArrayList<ContentDiscover> getUsers(){
        ArrayList<ContentDiscover> users = new ArrayList<ContentDiscover>();
        users.add(new ContentDiscover("A Course on C", "Course Start date", "200 Participants"));
        users.add(new ContentDiscover("A Course on IOS", "Course Start date", "200 Participants"));
        users.add(new ContentDiscover("A Course on PHP", "Course Start date", "200 Participants"));
        users.add(new ContentDiscover("A Course on ANDROID", "Course Start date", "200 Participants"));
        users.add(new ContentDiscover("A Course on C++", "Course Start date", "200 Participants"));
        return users;
    }
}
