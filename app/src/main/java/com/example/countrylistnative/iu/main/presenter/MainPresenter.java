package com.example.countrylistnative.iu.main.presenter;

import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.external.data.interactors.ICountryInteractor;
import com.example.countrylistnative.external.data.repository.ICountryRepository;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainMvp.Presenter, ICountryInteractor.getCountriesServiceCallback {

    private final MainMvp.View view;
    private final ICountryInteractor interactor;

    public MainPresenter(MainMvp.View view, ICountryInteractor interactor) {
        this.view = view;
        this.view.setPresenter(this);
        this.interactor = interactor;
    }

    @Override
    public void getCountries() {
        this.view.showLoading(true);
        this.interactor.getCountries(this);
    }

    @Override
    public ArrayList<Country> getCountriesFromLocalStorage() {
        return this.interactor.getCountriesFromLocalStorage();
    }

    @Override
    public void saveCountriesActualPage(ArrayList<Country> countries) {
        this.interactor.saveCountriesActualPage(countries);
    }

    @Override
    public void setCountryFav(String cod, boolean fav) {
        this.interactor.setCountryFavorite(cod, fav);
        this.view.getCountries();
    }

    @Override
    public void onSuccess(List<Country> countries) {
        this.view.showLoading(false);
        this.view.setCountries(countries);
    }

    @Override
    public void onFailed(String error) {
        this.view.showLoading(false);
        this.view.showMessage(error);
    }
}
