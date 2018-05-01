package com.example.gabrielsaruhashi.ramp.models;

import java.util.ArrayList;

/**
 * Created by Masayuki on 4/5/18.
 * Created by tylershen on 4/12/18.
 */

public class SubCategory {
    String title;
    String catchPhrase;
    ArrayList<Guide> guides;


    int r, g, b;


    public SubCategory(String title, String catchPhrase, int r, int g, int b, ArrayList<Guide> guides) {
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

    public ArrayList<Guide> getGuides() {
        return guides;
    }

    public int getR() { return r; }

    public int getG() { return g; }

    public int getB() { return b; }
}

