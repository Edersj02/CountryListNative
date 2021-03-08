package com.example.countrylistnative.iu.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.countrylistnative.R;
import com.example.countrylistnative.dp.DependencyProvider;
import com.example.countrylistnative.iu.main.presenter.MainFragment;
import com.example.countrylistnative.iu.main.presenter.MainPresenter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_CountryListNative);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        fragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_container);

        getSupportFragmentManager();

        if (fragment == null) {
            fragment = MainFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }
        new MainPresenter(fragment, DependencyProvider.provideCountryInteractor(this));
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Countrys");
    }

    @Override
    public void onBackPressed() {
        fragment.onBackPressed();
    }

}