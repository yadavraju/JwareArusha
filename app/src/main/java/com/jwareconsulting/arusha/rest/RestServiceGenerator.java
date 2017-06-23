package com.jwareconsulting.arusha.rest;


import com.jwareconsulting.arusha.config.ApiConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anjul Khanal on 2/25/16.
 */
public class RestServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    public RestServiceGenerator() {

    }

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());     //toget json response

    /**
     * Without dynamic baseurl
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = builder
                .client(httpClient
                        .addInterceptor(interceptor)
                        .connectTimeout(ApiConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(ApiConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                        .build())

                .build();
        return retrofit.create(serviceClass);
    }

    /**
     * With dynamic base url
     * @param serviceClass
     * @param baseUrl
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.baseUrl(baseUrl);
        Retrofit retrofit = builder
                .client(httpClient
                        .addInterceptor(interceptor)
                        .connectTimeout(ApiConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(ApiConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                        .build())

                .build();
        return retrofit.create(serviceClass);
    }

    public static OkHttpClient.Builder getHttpClient() {
        return httpClient;
    }
}
