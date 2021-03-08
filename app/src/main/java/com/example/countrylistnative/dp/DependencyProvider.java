package com.example.countrylistnative.dp;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.countrylistnative.external.data.cloud.CountryDataSource;
import com.example.countrylistnative.external.data.interactors.CountryInteractor;
import com.example.countrylistnative.external.data.repository.CountryRepository;
import com.example.countrylistnative.external.preferences.CountriesPreferences;

public class DependencyProvider {

    public DependencyProvider() {
    }

    public static CountryRepository provideCountryRepository(@NonNull Context context) {
        return CountryRepository.getInstance(
                CountryDataSource.getInstance(), CountriesPreferences.getInstance(context), context
        );
    }

    public static CountryInteractor provideCountryInteractor(@NonNull Context context) {
        return new CountryInteractor(provideCountryRepository(context));
    }

}
