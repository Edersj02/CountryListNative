package com.example.countrylistnative.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Language implements Parcelable {
    @SerializedName("iso639_1")
    private String iso639_1;
    @SerializedName("iso639_2")
    private String iso639_2;
    @SerializedName("name")
    private String name;
    @SerializedName("nativeName")
    private String nativeName;

    public Language(String iso639_1, String iso639_2, String name, String nativeName) {
        this.iso639_1 = iso639_1;
        this.iso639_2 = iso639_2;
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getIso639_2() {
        return iso639_2;
    }

    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    protected Language(Parcel in) {
        iso639_1 = in.readString();
        iso639_2 = in.readString();
        name = in.readString();
        nativeName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso639_1);
        dest.writeString(iso639_2);
        dest.writeString(name);
        dest.writeString(nativeName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}
