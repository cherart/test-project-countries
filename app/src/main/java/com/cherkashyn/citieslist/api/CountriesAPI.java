package com.cherkashyn.citieslist.api;

import com.cherkashyn.citieslist.model.Countries;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesAPI {

    @GET("/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Call<Countries> getCountriesList();
}
