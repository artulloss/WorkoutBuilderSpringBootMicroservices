package com.workoutbuilder.enterprise.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public @Data class WorkoutDTO {

    private long id;

    @NotEmpty(message = "Workout name is required.")
    @Size(max = 255, message = "Workout name should not exceed 255 characters.")
    private String name;

    @NotEmpty(message = "Workout description is required.")
    @Size(max = 500, message = "Description should not exceed 500 characters.")
    private String description;

    @NotNull(message = "Workout duration is required.")
    @Min(value = 1, message = "Duration should be a positive value.")
    private int duration;

    private List<LocalDate> datesCompleted = new ArrayList<>();

    @NotEmpty(message = "At least one exercise is required.")
    private List<ExerciseDTO> exercises = new ArrayList<>();

    @NotNull(message = "User ID is required.")
    private long createdByUserId;

}