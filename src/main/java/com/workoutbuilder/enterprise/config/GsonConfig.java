package com.workoutbuilder.enterprise.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workoutbuilder.enterprise.adapter.LowercaseEnumTypeAdapter;
import com.workoutbuilder.enterprise.dto.ExerciseDifficulty;
import com.workoutbuilder.enterprise.dto.ExerciseMuscle;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(ExerciseDifficulty.class, new LowercaseEnumTypeAdapter<>(ExerciseDifficulty.class))
                .registerTypeAdapter(ExerciseMuscle.class, new LowercaseEnumTypeAdapter<>(ExerciseMuscle.class))
                .registerTypeAdapter(ExerciseType.class, new LowercaseEnumTypeAdapter<>(ExerciseType.class))
                // Register other custom deserializers if needed
                .create();
    }
}
