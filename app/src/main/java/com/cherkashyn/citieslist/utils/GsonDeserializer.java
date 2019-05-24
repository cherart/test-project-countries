package com.cherkashyn.citieslist.utils;

import com.cherkashyn.citieslist.model.Countries;
import com.cherkashyn.citieslist.model.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonDeserializer {

    public static Gson createCountryGsonDeserializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Countries> deserializer = new JsonDeserializer<Countries>() {
            @Override
            public Countries deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Countries countries = new Countries();
                JsonObject jsonObject = json.getAsJsonObject();
                for (String key : jsonObject.keySet()) {
                    Type listType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> cities = new Gson().fromJson(jsonObject.get(key), listType);
                    countries.getCountries().add(new Country(key, cities));
                }
                return countries;
            }
        };

        gsonBuilder.registerTypeAdapter(Countries.class, deserializer);
        return gsonBuilder.create();
    }
}
