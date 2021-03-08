package com.example.countrylistnative.external.api;

import com.example.countrylistnative.entities.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    String COUNTRY_SERVICE_BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<List<Country>> getCountries();
}
