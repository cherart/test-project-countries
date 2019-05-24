package com.cherkashyn.citieslist.api;

import com.cherkashyn.citieslist.model.CityData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityAPI {

    @GET("/wikipediaSearchJSON?maxRows=5&username=ukssuss&")
    Call<CityData> getCity(@Query("q") String name);
}
