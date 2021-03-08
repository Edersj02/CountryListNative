package com.example.countrylistnative.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Helper extends AppCompatActivity {

    private Context context;

    public Helper(Context context) {
        this.context = context;
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show();
    }

    public static class IsNetworkAvailable {

        private final android.net.ConnectivityManager ConnectivityManager;

        public IsNetworkAvailable(ConnectivityManager connectivityManager) {
            ConnectivityManager = connectivityManager;
        }

        public boolean isNetworkAvailable() {

            ConnectivityManager cm = ConnectivityManager;

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }
    }

    public void showMaps(ArrayList<Double> latLng) {
        String uri = String.format(
                Locale.ENGLISH, "geo:%1$f,%2$f?z=16&q=%1$f,%2$f", latLng.get(0), latLng.get(1)
        );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        context.startActivity(intent);
    }
}
