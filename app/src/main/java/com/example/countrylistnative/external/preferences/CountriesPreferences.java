package com.example.countrylistnative.external.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.countrylistnative.entities.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CountriesPreferences implements ICountriesPreferences {

    private static final String COUNTRIES_FILE_NAME = "countries";
    private static final String PREF_DATA = "data";
    private static final String PREF_DATA_ACT_PAG = "data.actual.page";

    private static CountriesPreferences INSTANCE;

    private SharedPreferences preferences;

    private CountriesPreferences(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(
                        context.getPackageName() + "." + COUNTRIES_FILE_NAME,
                        Context.MODE_PRIVATE
                );
    }

    public static CountriesPreferences getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new CountriesPreferences(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveCountries(ArrayList<Country> countries) {
        Gson gson = new Gson();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(PREF_DATA, gson.toJson(countries));
        edit.apply();
    }

    @Override
    public ArrayList<Country> getCountriesPreferences() {
        Gson gson = new Gson();
        String countriesJson = preferences.getString(PREF_DATA, null);
        if (countriesJson != null) {
            Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
            return gson.fromJson(countriesJson, listType);
        }
        return null;
    }

    @Override
    public void saveCountriesActualPage(ArrayList<Country> countries) {
        Gson gson = new Gson();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(PREF_DATA_ACT_PAG, gson.toJson(countries));
        edit.apply();
    }

    @Override
    public ArrayList<Country> getCountriesActPagePref() {
        Gson gson = new Gson();
        String countriesJson = preferences.getString(PREF_DATA_ACT_PAG, null);
        if (countriesJson != null) {
            Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
            return gson.fromJson(countriesJson, listType);
        }
        return null;
    }
}
