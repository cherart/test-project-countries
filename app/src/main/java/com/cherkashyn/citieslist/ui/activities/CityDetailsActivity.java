package com.cherkashyn.citieslist.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cherkashyn.citieslist.R;
import com.cherkashyn.citieslist.api.CityAPI;
import com.cherkashyn.citieslist.api.Service;
import com.cherkashyn.citieslist.model.City;
import com.cherkashyn.citieslist.model.CityData;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityDetailsActivity extends AppCompatActivity {

    private String cityName;

    private TextView textViewTitle;
    private TextView textViewSummary;
    private TextView textViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        cityName = (String) getIntent().getExtras().get("CityName");

        initViews();
        apiCall();
    }

    private void initViews() {
        textViewTitle = findViewById(R.id.text_view_title);
        textViewSummary = findViewById(R.id.text_view_summary);
        textViewUrl = findViewById(R.id.text_view_url);
    }

    private void apiCall() {
        CityAPI cityAPI = Service.getCityClient().create(CityAPI.class);
        cityAPI.getCity(cityName).enqueue(new Callback<CityData>() {
            @Override
            public void onResponse(Call<CityData> call, Response<CityData> response) {
                City city = response.body().getCityList().get(0);
                textViewTitle.setText(city.getTitle());
                textViewSummary.setText(city.getSummary());
                textViewUrl.setText(city.getWikipediaUrl());
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {
                textViewTitle.setText("Problems with connection");
            }
        });
    }
}
