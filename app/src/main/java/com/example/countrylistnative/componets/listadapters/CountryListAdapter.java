package com.example.countrylistnative.componets.listadapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.countrylistnative.R;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.helpers.Helper;

import java.util.ArrayList;

public class CountryListAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<Country> countries;
    private Helper helper;

    public CountryListAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
        this.helper = new Helper(context);
    }

    @Override
    public int getCount() {
        return this.countries.size();
    }

    @Override
    public Object getItem(int position) {
        return this.countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.item_list, null);
        }

        Country country = this.countries.get(position);

        TextView nameCountry = view.findViewById(R.id.countryName);
        nameCountry.setText(country.getName());
        TextView capital = view.findViewById(R.id.capital);
        capital.setText(country.getCapital());
        ImageView flag = (ImageView) view.findViewById(R.id.flag);
        String flagUrl = country.getFlag();
        Glide.with(view)
                .load(country.getFlag())
                .error(R.drawable.ic_map_128)
                .into(flag);
        flag.setOnClickListener(v -> {
            helper.showMaps(country.getLatLng());
        });

        return view;
    }
}
