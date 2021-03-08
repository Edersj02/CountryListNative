package com.example.countrylistnative.iu.continets.presenter;

import androidx.appcompat.widget.SearchView;

import com.example.countrylistnative.entities.Country;

public interface ContinentMvp {
    void showMessage(String message);

    void getCountries();

    void setListView();

    void initSearchListener();

    void showDetailScreen(Country country);
}
