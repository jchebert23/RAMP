package com.example.gabrielsaruhashi.ramp.models;

/**
 * Created by Masayuki on 4/19/18.
 */

public class Section {
    private String title;
    private String contents;
    private int number;

    public Section(String title, String contents, int number) {
        this.title = title;
        this.contents = contents;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setNumber(int n) { this.number = n; }

    public int getNumber() { return number; }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
