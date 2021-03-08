package com.example.countrylistnative.iu.continets.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylistnative.R;
import com.example.countrylistnative.componets.listadapters.CountryListAdapter;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.helpers.Helper;
import com.example.countrylistnative.iu.countrydetail.CountryDetailActivity;
import com.example.countrylistnative.iu.main.presenter.MainMvp;

import java.util.ArrayList;

public class ContinentFragment extends Fragment implements ContinentMvp,
        CountryListAdapter.RecyclerItemClick,
        CountryListAdapter.RecyclerStartFavClick,
        SearchView.OnQueryTextListener {

    private Context context;
    private Helper helper;
    private CountryListAdapter adapter;
    private SearchView search;

    private final MainMvp.Presenter presenter;
    private final String continent;

    private ArrayList<Country> countriesOfContinent;
    private RecyclerView countryList;

    public ContinentFragment(MainMvp.Presenter presenter, String continent) {
        this.presenter = presenter;
        this.continent = continent;
    }

    public static ContinentFragment newInstance(
            MainMvp.Presenter presenter, String continent
    ) {
        return new ContinentFragment(presenter, continent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        this.helper = new Helper(context);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_continent, container, false);
        countryList = view.findViewById(R.id.countryList);
        search = view.findViewById(R.id.search);
        getCountries();
        setListView();
        initSearchListener();
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
            adapter = new CountryListAdapter(context, countriesOfContinent, this, this);
            countryList.setAdapter(adapter);
        }
    }

    @Override
    public void itemClick(Country item) {
        showDetailScreen(item);
    }

    @Override
    public void startFavClick(Country item) {
        String cod = item.getAlpha3Code();
        boolean fav = !item.isFavorite();
        this.presenter.setCountryFav(cod, fav);
    }

    @Override
    public void initSearchListener() {
        search.setOnQueryTextListener(this);
    }

    @Override
    public void showDetailScreen(Country country) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(getString(R.string.key_country_det), country);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
