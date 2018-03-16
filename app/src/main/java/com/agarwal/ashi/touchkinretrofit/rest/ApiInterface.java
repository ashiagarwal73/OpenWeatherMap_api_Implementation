package com.agarwal.ashi.touchkinretrofit.rest;

import com.agarwal.ashi.touchkinretrofit.pojo.WeatherMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ashi on 05-02-2018.
 */

public interface ApiInterface {
    @GET("weather")
    Call<WeatherMap> getWeatherReport(@Query("q") String city, @Query("appid") String appid,@Query("units") String metric );
}