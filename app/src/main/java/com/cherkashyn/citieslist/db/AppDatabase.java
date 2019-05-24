package com.cherkashyn.citieslist.db;

import com.cherkashyn.citieslist.model.Country;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountriesDao countriesDao();
}
