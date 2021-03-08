package com.example.countrylistnative.componets.listadapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.countrylistnative.R;
import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.helpers.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    protected Context context;
    private Helper helper;

    private ArrayList<Country> items;
    private ArrayList<Country> originalItems;
    private RecyclerItemClick itemClick;

    public CountryListAdapter(Context context, ArrayList<Country> countries, RecyclerItemClick itemClick) {
        this.context = context;
        this.items = countries;
        this.originalItems = new ArrayList<>();
        this.itemClick = itemClick;
        originalItems.addAll(items);
        this.helper = new Helper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Country item = items.get(position);
        holder.getNameCountry().setText(item.getName());
        holder.getCapital().setText(item.getCapital());
        Glide.with(holder.getFlag())
                .load(item.getFlag())
                .error(R.drawable.ic_map_128)
                .into(holder.getFlag());
        holder.getFlag().setOnClickListener(v -> {
            helper.showMaps(item.getLatLng());
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            items.clear();
            ArrayList<Country> count = new ArrayList<>();
            for (Country country : originalItems) {
                if (country.getName().toLowerCase().contains(strSearch)) {
                    count.add(country);
                }
                items.addAll(count);
            }
        }
        this.notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameCountry;
        private final TextView capital;
        private final ImageView flag;

        public ViewHolder(View view) {
            super(view);
            nameCountry = view.findViewById(R.id.countryName);
            capital = view.findViewById(R.id.capital);
            flag = (ImageView) view.findViewById(R.id.flag);
        }

        public TextView getNameCountry() {
            return  nameCountry;
        }

        public TextView getCapital() {
            return  capital;
        }

        public ImageView getFlag() {
            return  flag;
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Country item);
    }

}
