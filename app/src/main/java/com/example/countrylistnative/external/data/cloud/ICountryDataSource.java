package com.example.countrylistnative.external.data.cloud;

import com.example.countrylistnative.entities.Country;

import java.util.List;

public interface ICountryDataSource {

    void getCountries(getCountriesServiceCallback callback);

    interface getCountriesServiceCallback {
        void onSuccess(List<Country> countries);
        void onFailed(String error);
    }

}
