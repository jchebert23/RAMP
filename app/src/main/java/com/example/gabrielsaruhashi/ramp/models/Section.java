package com.example.gabrielsaruhashi.ramp.models;

/**
 * Created by Masayuki on 4/19/18.
 */

public class Section {
    private String title;
    private String contents;

    public Section(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
