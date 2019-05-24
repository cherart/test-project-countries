package com.cherkashyn.citieslist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cherkashyn.citieslist.App;
import com.cherkashyn.citieslist.R;
import com.cherkashyn.citieslist.api.CountriesAPI;
import com.cherkashyn.citieslist.api.Service;
import com.cherkashyn.citieslist.db.AppDatabase;
import com.cherkashyn.citieslist.db.CountriesDao;
import com.cherkashyn.citieslist.model.Countries;
import com.cherkashyn.citieslist.model.Country;
import com.cherkashyn.citieslist.ui.adapters.CitiesListAdapter;
import com.cherkashyn.citieslist.utils.OnCityClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Country> countriesList;
    private CitiesListAdapter citiesListAdapter;

    private AppDatabase database;
    private CountriesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        countriesList = dao.getCountries();

        if (countriesList.size() == 0)
            apiCall();
        else
            initViews();
    }

    private void initDatabase() {
        database = App.getInstance().getDatabase();
        dao = database.countriesDao();
    }

    private void apiCall() {
        CountriesAPI countriesAPI = Service.getCountryClient().create(CountriesAPI.class);
        countriesAPI.getCountriesList().enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                countriesList = response.body().getCountries();
                for (Country country : countriesList)
                    dao.insert(country);
                initViews();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                TextView textViewConnection = findViewById(R.id.text_view_connection);
                textViewConnection.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        initSpinner();
        initRecyclerView();
    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getNamesList());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citiesListAdapter.setCitiesList(countriesList.get((int) id).getCities());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public List<String> getNamesList() {
        List<String> names = new ArrayList<>();

        for (Country country : countriesList)
            names.add(country.getName());

        return names;
    }

    private void initRecyclerView() {
        citiesListAdapter = new CitiesListAdapter();
        citiesListAdapter.setOnCityClickListener(new OnCityClickListener() {
            @Override
            public void onCityClick(String cityName) {
                startIntent(cityName);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(citiesListAdapter);
    }

    private void startIntent(String cityName) {
        Intent intent = new Intent(this, CityDetailsActivity.class);
        intent.putExtra("CityName", cityName);
        startActivity(intent);
    }
}
