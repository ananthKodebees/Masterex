package com.course.masterex.model;

import java.util.ArrayList;

/**
 * Created by DELL on 5/6/2016.
 */
public class ContentSaved {

    public String name;
    public String type;
    public String id;

    public ContentSaved(String name, String type, String id){

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


