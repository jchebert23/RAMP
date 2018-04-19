package com.example.gabrielsaruhashi.ramp.models;

import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by Masayuki on 3/8/18.
 */

public class Category {
    private String status;
    private String statusImagePath;
    private ArrayList<SubCategory> subcategories;
    private ArrayList<Resources> icons;

    public Category(){
        super();
    }

    public Category(String status, String statusImagePath, ArrayList<SubCategory> subcategories, ArrayList<Resources> icons) {
        this.status = status;
        this.statusImagePath = statusImagePath;
        this.subcategories = subcategories;
        this.icons = icons;
    }

    public Category(String status, String statusImagePath) {
        this.status = status;
        this.statusImagePath = statusImagePath;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusImagePath() {
        return statusImagePath;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusImagePath(String statusImagePath) {
        this.statusImagePath = statusImagePath;
    }

    public ArrayList<SubCategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<SubCategory> subcategories) {
        this.subcategories = subcategories;
    }

    public ArrayList<Resources> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<Resources> icons) {
        this.icons = icons;
    }
}
