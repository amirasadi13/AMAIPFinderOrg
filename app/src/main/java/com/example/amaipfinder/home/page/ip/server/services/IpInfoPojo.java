package com.example.amaipfinder.home.page.ip.server.services;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IpInfoPojo {

    @SerializedName("ip")
    private String ip;
    @SerializedName("country_name")
    private String countryName;
    @SerializedName("city")
    private String city;
    @SerializedName("country_flag")
    private String imageFlag;
    @SerializedName("country_capital")
    private String country_capital;
    @SerializedName("isp")
    private String isp;
    @SerializedName("organization")
    private String organization;
    @SerializedName("asn")
    private String asNumber;
    @SerializedName("languages")
    private String languages;
    @SerializedName("time_zone")
    private TimeZonePojo timeZonePojo;

    public TimeZonePojo getTimeZonePojo() {
        return timeZonePojo;
    }

    public String getLanguages() {
        return languages;
    }

    public String getAsNumber() {
        return asNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public String getIsp() {
        return isp;
    }

    public String getCountry_capital() {
        return country_capital;
    }

    public String getImageFlag() {
        return imageFlag;
    }

    public String getIp() {
        return ip;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCity() {
        return city;
    }
}
