package com.example.countrylistnative.iu.countrydetail.presenter;

import com.example.countrylistnative.entities.Country;

import java.util.ArrayList;

public interface CountryDetailMvp {
    interface View {
        ArrayList<Country> getCountryActPage();

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        ArrayList<Country> getCountriesActPagePref();
    }
}
