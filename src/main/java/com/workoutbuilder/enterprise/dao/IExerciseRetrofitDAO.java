package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

public interface IExerciseRetrofitDAO {


    @GET("/v1/exercises")
    Call<List<Exercise>> getExercises(@Header("x-api-key") String apiKey);

    @GET("/v1/exercises")
    Call<List<Exercise>> getExercisesByName(@Header("x-api-key") String apiKey, @Query("name") String name);
}
