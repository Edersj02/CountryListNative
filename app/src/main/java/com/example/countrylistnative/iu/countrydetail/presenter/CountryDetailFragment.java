package com.example.countrylistnative.iu.countrydetail.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.countrylistnative.R;
import com.example.countrylistnative.entities.Country;

import java.util.ArrayList;
import java.util.Locale;

public class CountryDetailFragment extends Fragment implements CountryDetailMvp.View {

    private Context context;

    private Country country;
    private CountryDetailMvp.Presenter presenter;

    public CountryDetailFragment() {
    }

    public static CountryDetailFragment newInstance() {
        return new CountryDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        Bundle extras = getActivity().getIntent().getExtras();
        country = extras.getParcelable(getString(R.string.key_country_det));

    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_country_detail, container, false);

        // Country Name
        TextView countryName = view.findViewById(R.id.countryName);
        countryName.setText(country.getName());
        // Flag
        ImageView imageView = view.findViewById(R.id.flag);
        Glide.with(view)
                .load(country.getFlag())
                .error(R.drawable.ic_map_128)
                .into(imageView);
        // Region
        TextView regionValue = view.findViewById(R.id.regionValue);
        regionValue.setText(country.getRegion());
        // Population
        TextView populationValue = view.findViewById(R.id.populationValue);
        Double pop = Double.parseDouble(country.getPopulation());
        String popString = String.format(Locale.getDefault(), "%1$,.0f", pop);
        populationValue.setText(popString);
        // Capital
        TextView capitalValue = view.findViewById(R.id.capitalValue);
        capitalValue.setText(country.getCapital());
        // Currency
        TextView currencyValue = view.findViewById(R.id.currencyValue);
        currencyValue.setText(country.getCurrencies().get(0).getName());
        // Language
        TextView languageValue = view.findViewById(R.id.languageValue);
        languageValue.setText(country.getLanguages().get(0).getName());
        // Borders
        TextView bordersValue = view.findViewById(R.id.bordersValue);
        StringBuilder borders = new StringBuilder();
        if (country.getBorders() != null && country.getBorders().size() > 0) {
            for (String cod : country.getBorders()) {
                ArrayList<Country> listPa = getCountryActPage();
                for (Country country : listPa) {
                    if (country.getAlpha3Code().equals(cod)) {
                        borders.append(country.getName()).append(",");
                    }
                }
            }
        }
        String bors = borders.toString();
        if (country.getBorders().size() > 1) {
            bordersValue.setText(bors.substring(0, bors.length() - 1));
        } else {
            bordersValue.setText(bors);
        }
        ImageButton startRate = view.findViewById(R.id.startRate);
        ImageButton startOutline = view.findViewById(R.id.startOutline);

        startRate.setVisibility(View.GONE);
        startOutline.setVisibility(View.VISIBLE);

        if (country.isFavorite()) {
            startRate.setVisibility(View.VISIBLE);
            startOutline.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public ArrayList<Country> getCountryActPage() {
        return this.presenter.getCountriesActPagePref();
    }

    @Override
    public void setPresenter(CountryDetailMvp.Presenter presenter) {
        this.presenter = presenter;
    }
}
