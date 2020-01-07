package br.com.module.exchange.model;

import androidx.annotation.InspectableProperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRateObject {

    @SerializedName("EUR")
    @Expose
    private double EUR;

    @SerializedName("USD")
    @Expose
    private double USD;

    @SerializedName("GBP")
    @Expose
    private double GBP;

    @SerializedName("CAD")
    @Expose
    private double CAD;

    @SerializedName("CHF")
    @Expose
    private double CHF;

    @SerializedName("BRL")
    @Expose
    private double BRL;

    @SerializedName("SEK")
    @Expose
    private double SEK;

    public double getRateFor(String currency) {
        if (currency.equals("EUR")) return EUR;
        if (currency.equals("USD")) return USD;
        if (currency.equals("GBP")) return GBP;
        if (currency.equals("CAD")) return CAD;
        if (currency.equals("GHF")) return CHF;
        if (currency.equals("BRL")) return BRL;
        if (currency.equals("SEK")) return SEK;
        return 1;
    }
}

