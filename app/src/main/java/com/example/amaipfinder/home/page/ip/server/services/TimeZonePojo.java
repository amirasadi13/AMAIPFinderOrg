package com.example.amaipfinder.home.page.ip.server.services;

import com.google.gson.annotations.SerializedName;

public class TimeZonePojo {

    @SerializedName("name")
    private String name;

    @SerializedName("current_time")
    private String currentTime;

    public String getName() {
        return name;
    }

    public String getCurrentTime() {
        return currentTime;
    }
}
