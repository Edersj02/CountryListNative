package com.example.countrylistnative.external.data.interactors;

import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.external.data.repository.ICountryRepository;

import java.util.ArrayList;
import java.util.List;

public class CountryInteractor implements ICountryInteractor {

    private final ICountryRepository repository;

    public CountryInteractor(ICountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getCountries(final getCountriesServiceCallback callback) {
        this.repository.getCountries(
                new ICountryRepository.getCountriesServiceCallback() {
                    @Override
                    public void onSuccess(List<Country> countries) {
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
        return repository.getCountriesFromLocalStorage();
    }

    @Override
    public void saveCountriesActualPage(ArrayList<Country> countries) {
        repository.saveCountriesActualPage(countries);
    }

    @Override
    public ArrayList<Country> getCountriesActPagePref() {
        return repository.getCountriesFromLocalStorage();
    }
}
