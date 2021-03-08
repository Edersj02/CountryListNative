package com.example.countrylistnative.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Country implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("alpha2Code")
    private String alpha2Code;
    @SerializedName("alpha3Code")
    private String alpha3Code;
    @SerializedName("capital")
    private String capital;
    @SerializedName("region")
    private String region;
    @SerializedName("population")
    private String population;
    @SerializedName("latlng")
    private ArrayList<Double> latLng;
    @SerializedName("borders")
    private ArrayList<String> borders;
    @SerializedName("currencies")
    private ArrayList<Currency> currencies;
    @SerializedName("languages")
    private ArrayList<Language> languages;
    @SerializedName("translations")
    private Translation Translations;
    @SerializedName("flag")
    private String flag;

    public Country(
            String name, String alpha2Code, String alpha3Code, String capital, String region,
            String population, ArrayList<Double> latLng, ArrayList<String> borders,
            ArrayList<Currency> currencies, ArrayList<Language> languages,
            Translation translations, String flag
    ) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.latLng = latLng;
        this.borders = borders;
        this.currencies = currencies;
        this.languages = languages;
        Translations = translations;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public ArrayList<Double> getLatLng() {
        return latLng;
    }

    public void setLatLng(ArrayList<Double> latLng) {
        this.latLng = latLng;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    public Translation getTranslations() {
        return Translations;
    }

    public void setTranslations(Translation translations) {
        Translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    protected Country(Parcel in) {
        name = in.readString();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
        capital = in.readString();
        region = in.readString();
        population = in.readString();
        if (in.readByte() == 0x01) {
            latLng = new ArrayList<Double>();
            in.readList(latLng, Double.class.getClassLoader());
        } else {
            latLng = null;
        }
        if (in.readByte() == 0x01) {
            borders = new ArrayList<String>();
            in.readList(borders, String.class.getClassLoader());
        } else {
            borders = null;
        }
        if (in.readByte() == 0x01) {
            currencies = new ArrayList<Currency>();
            in.readList(currencies, Currency.class.getClassLoader());
        } else {
            currencies = null;
        }
        if (in.readByte() == 0x01) {
            languages = new ArrayList<Language>();
            in.readList(languages, Language.class.getClassLoader());
        } else {
            languages = null;
        }
        Translations = (Translation) in.readValue(Translation.class.getClassLoader());
        flag = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(alpha2Code);
        dest.writeString(alpha3Code);
        dest.writeString(capital);
        dest.writeString(region);
        dest.writeString(population);
        if (latLng == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(latLng);
        }
        if (borders == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(borders);
        }
        if (currencies == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(currencies);
        }
        if (languages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(languages);
        }
        dest.writeValue(Translations);
        dest.writeString(flag);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
