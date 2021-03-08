package com.example.countrylistnative.external.data.repository;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import com.example.countrylistnative.R;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.external.data.cloud.ICountryDataSource;
import com.example.countrylistnative.external.preferences.ICountriesPreferences;
import com.example.countrylistnative.helpers.Helper;

import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements ICountryRepository {

    private static CountryRepository INSTANCE;
    private final ICountryDataSource dataSource;
    private final ICountriesPreferences preferences;
    private final Helper.IsNetworkAvailable nAvailable;

    private final Resources resources;

    public CountryRepository(ICountryDataSource dataSource, ICountriesPreferences preferences, Context context) {
        this.dataSource = dataSource;
        this.preferences = preferences;
        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.resources = context.getResources();
        nAvailable = new Helper.IsNetworkAvailable(cManager);
    }

    public static CountryRepository getInstance(
            ICountryDataSource dataSource, ICountriesPreferences preferences, Context context
    ) {
        if (INSTANCE == null) {
            INSTANCE = new CountryRepository(dataSource, preferences, context);
        }
        return INSTANCE;
    }

    @Override
    public void getCountries(getCountriesServiceCallback callback) {
        if(!nAvailable.isNetworkAvailable()){
            callback.onFailed(this.resources.getString(R.string.network_unavailable));
            return;
        }

        dataSource.getCountries(
                new ICountryDataSource.getCountriesServiceCallback() {
                    @Override
                    public void onSuccess(List<Country> countries) {
                        preferences.saveCountries((ArrayList<Country>) countries);
                        callback.onSuccess(countries);
                    }

                    @Override
                    public void onFailed(String error) {
                        callback.onFailed(error);
                    }
                }
        );
    }

    @Override
    public ArrayList<Country> getCountriesFromLocalStorage() {
        return preferences.getCountriesPreferences();
    }

    @Override
    public void saveCountriesActualPage(ArrayList<Country> countries) {
        preferences.saveCountriesActualPage(countries);
    }

    @Override
    public ArrayList<Country> getCountriesActPagePref() {
        return preferences.getCountriesActPagePref();
    }

    @Override
    public void setCountryFavorite(String cod, boolean fav) {
        preferences.setCountryFavorite(cod, fav);
    }
}
