package com.cherkashyn.citieslist.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cherkashyn.citieslist.utils.GsonDeserializer.createCountryGsonDeserializer;

public class Service {

    public static Retrofit getCountryClient() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create(createCountryGsonDeserializer()))
                .build();
    }

    public static Retrofit getCityClient() {
        return new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
