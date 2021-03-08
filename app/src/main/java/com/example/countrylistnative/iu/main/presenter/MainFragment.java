package com.example.countrylistnative.iu.main.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.countrylistnative.R;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.helpers.Helper;
import com.example.countrylistnative.iu.continets.presenter.ContinentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainMvp.View {

    private FragmentActivity activity;
    private MainMvp.Presenter presenter;
    private ViewPager pager;
    private ProgressBar progressBar;

    private Helper helper;
    private String[] continents;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        helper = new Helper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        pager = view.findViewById(R.id.pager);
        progressBar = view.findViewById(R.id.progressBar);
        getCountries();

        return view;
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void getCountries() {
        ArrayList<Country>  countries = this.presenter.getCountriesFromLocalStorage();
        if (countries == null) {
            this.presenter.getCountries();
        } else {
            setViewPagerAdapter();
        }
    }

    @Override
    public void setCountries(List<Country> countries) {
        setViewPagerAdapter();
    }

    @Override
    public void showMessage(String message) {
        this.helper.showToast(message);
    }

    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            activity.finish();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    @Override
    public void setViewPagerAdapter() {
        String[] continents = getResources().getStringArray(R.array.continents);
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getFragmentManager());
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[0]));
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[1]));
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[2]));
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[3]));
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[4]));
        pagerAdapter.addFragment(ContinentFragment.newInstance(presenter, continents[5]));
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void setPresenter(MainMvp.Presenter presenter) {
        this.presenter = presenter;
    }

}
