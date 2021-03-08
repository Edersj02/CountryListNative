package com.example.countrylistnative.external.data.interactors;

import com.example.countrylistnative.entities.Country;

import java.util.ArrayList;
import java.util.List;

public interface ICountryInteractor {

    void getCountries(getCountriesServiceCallback callback);

    interface getCountriesServiceCallback {
        void onSuccess(List<Country> countries);
        void onFailed(String error);
    }

    ArrayList<Country> getCountriesFromLocalStorage();

    void saveCountriesActualPage(ArrayList<Country> countries);

    ArrayList<Country> getCountriesActPagePref();

    void setCountryFavorite(String cod, boolean fav);

}
