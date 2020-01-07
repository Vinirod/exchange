package br.com.module.exchange.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigService {

    private static final String URL_BASE_SERVICE = "https://api.exchangeratesapi.io";

    private static Retrofit mRetrofit = null;

    private static Gson mGson = new GsonBuilder().create();

    private static HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(mHttpLoggingInterceptor);

    private static OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

    /**
     * Method for creating service integration with backend
     * @param classService Class of services that will be injected
     * @param <T> Generic call sign for the method
     * @return Return retrofit configuration for integration
     */
    public static <T> T createService(Class<T> classService){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(URL_BASE_SERVICE)
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .build();
        }
        return mRetrofit.create(classService);
    }
}
