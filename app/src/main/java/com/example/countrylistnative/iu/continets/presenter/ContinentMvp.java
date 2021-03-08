package com.example.countrylistnative.iu.continets.presenter;

import com.example.countrylistnative.entities.Country;

public interface ContinentMvp {
    void showMessage(String message);

    void getCountries();

    void setListView();

    void setItemClickListener();

    void showDetailScreen(Country country);
}
