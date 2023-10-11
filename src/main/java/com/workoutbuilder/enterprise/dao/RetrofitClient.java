package com.workoutbuilder.enterprise.dao;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Singleton class to provide a single instance of a Retrofit object.
 * This class ensures that only one instance of the Retrofit object exists throughout the app's lifecycle.
 */
public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.api-ninjas.com/api/";

    /**
     * Provides the Retrofit object instance.
     * If no instance exists, it creates a new one. Otherwise, the existing instance is returned.
     *
     * @return an instance of {@link Retrofit}
     */
    public static Retrofit getInstance() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + "YOUR_API_KEY")
                    .build();
            return chain.proceed(newRequest);
        }).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
