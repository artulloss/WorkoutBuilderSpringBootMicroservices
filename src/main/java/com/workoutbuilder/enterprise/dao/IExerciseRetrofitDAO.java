package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface IExerciseRetrofitDAO {
    @GET("exercises")
    Call<List<Exercise>> getExercises();
}
