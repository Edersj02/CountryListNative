package com.example.countrylistnative.componets.listadapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
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
    private RecyclerStartFavClick startFavClick;

    public CountryListAdapter(Context context, ArrayList<Country> countries,
                              RecyclerItemClick itemClick, RecyclerStartFavClick startFavClick) {
        this.context = context;
        this.items = countries;
        this.originalItems = new ArrayList<>();
        this.itemClick = itemClick;
        this.startFavClick = startFavClick;
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

        Drawable startRate = context.getDrawable(R.drawable.ic_star_rate_24);
        Drawable startOutline = context.getDrawable(R.drawable.ic_star_outline_24);

        if (item.isFavorite()) {
            holder.getStartRate().setImageDrawable(startRate);
        }

        holder.getStartRate().setOnClickListener(v -> {
            holder.getStartRate().setImageDrawable(
                    !item.isFavorite() ?
                            startRate :
                            startOutline
            );
            startFavClick.startFavClick(item);
        });

        holder.itemView.setOnClickListener(v -> itemClick.itemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        } else {
            items.clear();
            for (Country country : originalItems) {
                if (country.getName().toLowerCase().contains(strSearch)) {
                    items.add(country);
                }
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
        private final ImageButton startRate;

        public ViewHolder(View view) {
            super(view);
            nameCountry = view.findViewById(R.id.countryName);
            capital = view.findViewById(R.id.capital);
            flag = (ImageView) view.findViewById(R.id.flag);
            startRate = (ImageButton) view.findViewById(R.id.imageButton);
        }

        public TextView getNameCountry() {
            return nameCountry;
        }

        public TextView getCapital() {
            return capital;
        }

        public ImageView getFlag() {
            return flag;
        }

        public ImageButton getStartRate() {
            return startRate;
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Country item);
    }

    public interface RecyclerStartFavClick {
        void startFavClick(Country item);
    }

}
