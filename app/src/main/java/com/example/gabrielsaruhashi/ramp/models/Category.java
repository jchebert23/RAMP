package com.example.gabrielsaruhashi.ramp.models;

import android.content.res.Resources;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Masayuki on 3/8/18.
 */
@Parcel
public class Category {
    public String status;
    private String statusImagePath;
    public ArrayList<SubCategory> subcategories;
    private ArrayList<Resources> icons;
    private int category_image;
    private String iconURL;

    public Category() {

    }

    public Category(String status, String statusImagePath, ArrayList<SubCategory> subcategories, ArrayList<Resources> icons, int category_image) {
        this.status = status;
        this.statusImagePath = statusImagePath;
        this.subcategories = subcategories;
        this.icons = icons;
        this.category_image = category_image;
    }

    public Category(String status, int category_image) {
        this.status = status;
        this.category_image = category_image;
    }

    public Category(String status, String iconURL, ArrayList<SubCategory> subcategories) {
        this.status = status;
        this.iconURL = iconURL;
        this.subcategories = subcategories;
    }

    public String getStatus() {
        return status;
    }

    public String getIconURL() { return iconURL; }

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

    public int getCategory_image() {
        return category_image;
    }

    public void setCategory_image(int category_image) {
        this.category_image = category_image;
    }
}

