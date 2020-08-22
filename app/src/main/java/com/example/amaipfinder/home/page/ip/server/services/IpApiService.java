package com.example.amaipfinder.home.page.ip.server.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpApiService {

    @GET("ipgeo")
    Call<IpInfoPojo>
    getIp(@Query("apiKey") String apiKey , @Query("ip") String keyWord);
}
