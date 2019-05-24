package com.cherkashyn.citieslist.model;

import com.cherkashyn.citieslist.db.CountryConverter;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity()
@TypeConverters({CountryConverter.class})
public class Country {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private List<String> cities;

    public Country(String name, List<String> cities) {
        this.name = name;
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
