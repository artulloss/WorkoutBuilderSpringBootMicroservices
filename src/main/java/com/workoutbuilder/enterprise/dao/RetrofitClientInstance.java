package com.workoutbuilder.enterprise.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class to provide a single instance of a Retrofit object.
 * This class ensures that only one instance of the Retrofit object exists throughout the app's lifecycle.
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.api-ninjas.com";

    /**
     * Provides the Retrofit object instance.
     * If no instance exists, it creates a new one. Otherwise, the existing instance is returned.
     *
     * @return an instance of {@link Retrofit}
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
