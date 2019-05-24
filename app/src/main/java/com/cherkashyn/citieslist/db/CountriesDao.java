package com.cherkashyn.citieslist.db;

import com.cherkashyn.citieslist.model.Country;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CountriesDao {

    @Query("SELECT * FROM country")
    List<Country> getCountries();

    @Insert
    void insert(Country country);
}
