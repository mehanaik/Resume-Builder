package com.depstar.resumebuilder;

import android.app.Application;

public class GlobalClass extends Application {

    private String name;

    public String getName() {

        return name;
    }

    public void setName(String aName) {

        this.name = aName;

    }
}