package com.course.masterex.model;

import java.util.ArrayList;


public class ContentDiscover {

    public String name;
    public String type;
    public String id;

    public ContentDiscover(String name, String type, String id){

        this.name = name;
        this.type = type;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
