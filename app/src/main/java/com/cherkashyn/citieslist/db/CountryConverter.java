package com.cherkashyn.citieslist.db;

import java.util.Arrays;
import java.util.List;

import androidx.room.TypeConverter;

public class CountryConverter {

    @TypeConverter
    public String fromCities(List<String> cities) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cities.size(); i++) {
            result.append(cities.get(i));
            if (i < cities.size() - 1)
                result.append(",");
        }
        return String.valueOf(result);
    }

    @TypeConverter
    public List<String> toCities(String data) {
        return Arrays.asList(data.split(","));
    }
}
