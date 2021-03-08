package com.example.countrylistnative.external.preferences;

import com.example.countrylistnative.entities.Country;

import java.util.ArrayList;

public interface ICountriesPreferences {

    void saveCountries(ArrayList<Country> countries);

    ArrayList<Country> getCountriesPreferences();

    void saveCountriesActualPage(ArrayList<Country> countries);

    ArrayList<Country> getCountriesActPagePref();

    void setCountryFavorite(String cod, boolean fav);

}
