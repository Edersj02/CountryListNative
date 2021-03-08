package com.example.countrylistnative.iu.main.presenter;

import com.example.countrylistnative.entities.Country;

import java.util.ArrayList;
import java.util.List;

public interface MainMvp {

    interface View {
        void showLoading(boolean show);

        void getCountries();

        void setCountries(List<Country> countries);

        void showMessage(String message);

        void setViewPagerAdapter();

        void onBackPressed();

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void getCountries();

        ArrayList<Country> getCountriesFromLocalStorage();

        void saveCountriesActualPage(ArrayList<Country> countries);
    }

}
