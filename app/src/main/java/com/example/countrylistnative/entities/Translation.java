package com.example.countrylistnative.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Translation implements Parcelable {
    @SerializedName("de")
    private String de;
    @SerializedName("es")
    private String es;
    @SerializedName("fr")
    private String fr;
    @SerializedName("ja")
    private String ja;
    @SerializedName("it")
    private String it;
    @SerializedName("br")
    private String pt;
    @SerializedName("nl")
    private String nl;
    @SerializedName("hr")
    private String hr;
    @SerializedName("fa")
    private String fa;

    public Translation(String de, String es, String fr, String ja, String it, String pt, String nl, String hr, String fa) {
        this.de = de;
        this.es = es;
        this.fr = fr;
        this.ja = ja;
        this.it = it;
        this.pt = pt;
        this.nl = nl;
        this.hr = hr;
        this.fa = fa;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    protected Translation(Parcel in) {
        de = in.readString();
        es = in.readString();
        fr = in.readString();
        ja = in.readString();
        it = in.readString();
        pt = in.readString();
        nl = in.readString();
        hr = in.readString();
        fa = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(de);
        dest.writeString(es);
        dest.writeString(fr);
        dest.writeString(ja);
        dest.writeString(it);
        dest.writeString(pt);
        dest.writeString(nl);
        dest.writeString(hr);
        dest.writeString(fa);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Translation> CREATOR = new Parcelable.Creator<Translation>() {
        @Override
        public Translation createFromParcel(Parcel in) {
            return new Translation(in);
        }

        @Override
        public Translation[] newArray(int size) {
            return new Translation[size];
        }
    };
}
