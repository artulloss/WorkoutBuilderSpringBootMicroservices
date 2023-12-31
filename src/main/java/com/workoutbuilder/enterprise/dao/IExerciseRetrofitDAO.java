package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface IExerciseRetrofitDAO {

    @GET("/v1/exercises")
    Call<List<Exercise>> getExercises();

    @GET("/v1/exercises")
    Call<List<Exercise>> getExercisesByName(@Query("name") String name);

    @GET("/v1/exercises")
    Call<List<Exercise>> findExercise(@Query("name") String name);
}
