package br.com.module.exchange.apis;

import br.com.module.exchange.model.ExchangeRatesWrapperObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRatesApiService {

    @GET("/latest")
    Call<ExchangeRatesWrapperObject> getExchangeRates(@Query("base") String base, @Query("symbols") String symbols);

}
