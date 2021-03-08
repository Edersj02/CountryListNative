package com.example.countrylistnative.external.data.cloud;

import com.example.countrylistnative.entities.Country;
import com.example.countrylistnative.entities.ErrorResponse;
import com.example.countrylistnative.external.api.CountryService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryDataSource implements ICountryDataSource {

    private static CountryDataSource INSTANCE;

    private Retrofit restAdapter;
    private CountryService restClient;

    public CountryDataSource() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(1,    TimeUnit.MINUTES);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.addInterceptor(loggingInterceptor);
        restAdapter = new Retrofit.Builder()
                .baseUrl(CountryService.COUNTRY_SERVICE_BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restClient = restAdapter.create(CountryService.class);
    }

    public static CountryDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CountryDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getCountries(final getCountriesServiceCallback callback) {
        Call<List<Country>> call = restClient.getCountries();

        call.enqueue(
                new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                            return;
                        }

                        ResponseBody errorBody = response.errorBody();

                        if (errorBody.contentType().subtype().equals("json")) {
                            ErrorResponse errorResponse = ErrorResponse.fromErrorBody(errorBody);
                            callback.onFailed(errorResponse.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                        callback.onFailed(t.getMessage());
                    }
                }
        );
    }
}
