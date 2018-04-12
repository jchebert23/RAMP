package com.example.gabrielsaruhashi.ramp.models;

import java.util.ArrayList;

/**
 * Created by Masayuki on 4/5/18.
 */

public class SubCategory {
    String title;
    String catchPhrase;
    ArrayList<String> guides;


    public SubCategory(String title, String catchPhrase, ArrayList<String> guides) {
        this.title = title;
        this.catchPhrase = catchPhrase;
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
}

