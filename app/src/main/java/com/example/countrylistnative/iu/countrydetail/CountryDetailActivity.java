package com.example.countrylistnative.iu.countrydetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.StrictMode;

import com.example.countrylistnative.R;
import com.example.countrylistnative.dp.DependencyProvider;
import com.example.countrylistnative.iu.countrydetail.presenter.CountryDetailFragment;
import com.example.countrylistnative.iu.countrydetail.presenter.CountryDetailPresenter;

import java.util.Objects;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        setToolBar();
        CountryDetailFragment fragment = (CountryDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.country_detail_content);

        getSupportFragmentManager();

        if (fragment == null) {
            fragment = CountryDetailFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.country_detail_content, fragment)
                    .commit();
        }

        new CountryDetailPresenter(fragment, DependencyProvider.provideCountryInteractor(this));
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detailed information");
    }
}