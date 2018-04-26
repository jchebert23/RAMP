package com.example.gabrielsaruhashi.ramp.models;

/**
 * Created by Masayuki on 4/19/18.
 */

public class Section {
    private String name;
    private String contents;

    public Section(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
