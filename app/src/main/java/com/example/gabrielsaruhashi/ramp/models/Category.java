package com.example.gabrielsaruhashi.ramp.models;

/**
 * Created by Masayuki on 3/8/18.
 */

public class Category {
    private String status;
    private String statusImagePath;

    public Category(){
        super();
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
}
