package com.example.countrylistnative.iu.main.presenter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Africa";
            case 1:
                return "America";
            case 2:
                return "Asia";
            case 3:
                return "Europe";
            case 4:
                return "Oceania";
            default:
                return "Antarctica";
        }
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

}
