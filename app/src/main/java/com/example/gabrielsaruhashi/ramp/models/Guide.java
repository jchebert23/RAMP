package com.example.gabrielsaruhashi.ramp.models;

import java.util.ArrayList;

/**
 * Created by Masayuki on 4/19/18.
 */

public class Guide {
    private String name;
    private String content;
    private ArrayList<Section> sections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Guide(String name, String content, ArrayList<Section> sections){
        this.name = name;
        this.content = content;
        this.sections = sections;
    }
}
