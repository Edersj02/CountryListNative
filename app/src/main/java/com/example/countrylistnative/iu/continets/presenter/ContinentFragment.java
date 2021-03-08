package com.example.countrylistnative.iu.continets.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.countrylistnative.R;
import com.example.countrylistnative.componets.listadapters.CountryListAdapter;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.iu.countrydetail.CountryDetailActivity;
import com.example.countrylistnative.iu.main.presenter.MainMvp;

import java.util.ArrayList;

public class ContinentFragment extends Fragment implements ContinentMvp {

    private Context context;

    private final MainMvp.Presenter presenter;
    private final String continent;

    private ArrayList<Country> countriesOfContinent;
    private ListView countryList;

    public ContinentFragment(MainMvp.Presenter presenter, String continent) {
        this.presenter = presenter;
        this.continent = continent;
    }

    public static ContinentFragment newInstance(MainMvp.Presenter presenter, String continent) {
        return new ContinentFragment(presenter, continent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_continent, container, false);
        countryList = view.findViewById(R.id.countryList);
        getCountries();
        setListView();
        setItemClickListener();
        return view;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void getCountries() {
        ArrayList<Country> countries = this.presenter.getCountriesFromLocalStorage();
        if (countries != null) {
            countriesOfContinent = new ArrayList<>();
            for (Country country: countries) {
                if (country.getRegion().equals(continent)) {
                    countriesOfContinent.add(country);
                }
            }
            this.presenter.saveCountriesActualPage(countriesOfContinent);
        }
    }

    @Override
    public void setListView() {
        if (countriesOfContinent != null) {
            CountryListAdapter adapter = new CountryListAdapter(context, countriesOfContinent);
            countryList.setAdapter(adapter);
        }
    }

    @Override
    public void setItemClickListener() {
        countryList.setOnItemClickListener(
                (parent, view1, position, id) -> {
                    showDetailScreen(countriesOfContinent.get(position));
                }
        );
    }

    @Override
    public void showDetailScreen(Country country) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(getString(R.string.key_country_det), country);
        startActivity(intent);
    }

}
