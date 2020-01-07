package br.com.module.exchange.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRatesWrapperObject {

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("rates")
    @Expose

    private ExchangeRateObject rates;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public ExchangeRateObject getRates() {
        return rates;
    }
}
