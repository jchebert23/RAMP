package com.example.gabrielsaruhashi.ramp.models;

import java.util.ArrayList;

/**
 * Created by tylershen on 4/12/18.
 */

public class SubCategory {
    String title;
    String catchPhrase;
    int r, g, b;
    ArrayList<String> guides;


    public SubCategory(String title, String catchPhrase, int r, int g, int b, ArrayList<String> guides) {
        this.title = title;
        this.catchPhrase = catchPhrase;
        this.r = r ;
        this.g = g;
        this.b = b;
        this.guides = guides;
    }

    public String getTitle() {
        return title;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public ArrayList<String> getGuides() {
        return guides;
    }

    public int getR() { return r; }

    public int getG() { return g; }

    public int getB() { return b; }
}

