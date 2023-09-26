package com.workoutbuilder.enterprise.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class ExerciseDTO {
    private long id;

    @NotEmpty(message = "Exercise name is required.")
    @Size(max = 255, message = "Exercise name should not exceed 255 characters.")
    private String name;

    @NotEmpty(message = "Exercise description is required.")
    @Size(max = 500, message = "Description should not exceed 500 characters.")
    private String description;

    @NotEmpty(message = "Exercise type is required.")
    private ExerciseType exerciseType;

    private String createdBy;

}
