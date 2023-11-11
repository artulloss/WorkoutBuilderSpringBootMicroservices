package com.workoutbuilder.enterprise.config;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class to provide a single instance of a Retrofit object.
 * This class ensures that only one instance of the Retrofit object exists throughout the app's lifecycle.
 */
@Configuration
public class RetrofitClient {

    private static final String BASE_URL = "https://api.api-ninjas.com/";


    @Autowired
    private Gson gson;

    @Value("${api-ninjas.api-key}")
    private String key;

    @Bean
    public Retrofit retrofit() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("X-Api-Key", key)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        return new retrofit2.Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
