package com.example.countrylistnative.iu.countrydetail.presenter;

import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.external.data.interactors.ICountryInteractor;

import java.util.ArrayList;

public class CountryDetailPresenter implements CountryDetailMvp.Presenter {

    private final CountryDetailMvp.View view;
    private final ICountryInteractor interactor;

    public CountryDetailPresenter(CountryDetailMvp.View view, ICountryInteractor interactor) {
        this.view = view;
        this.view.setPresenter(this);
        this.interactor = interactor;
    }

    @Override
    public ArrayList<Country> getCountriesActPagePref() {
        return this.interactor.getCountriesActPagePref();
    }
}
