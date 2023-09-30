package com.workoutbuilder.enterprise.dto;

import lombok.Data;

/**
 * Represents an individual exercise that can be included in a workout.
 * This DTO captures the specific details of an exercise, such as its name, description, and type.
 * Different types of exercises are categorized using the {@link ExerciseType} enum.
 */
@Data
public class Exercise {

    /**
     * The unique identifier for this exercise.
     */
    private int id;

    /**
     * The name of the exercise (e.g., "Push-up", "Squat").
     */
    private String name;

    /**
     * A detailed description of the exercise, potentially including instructions on
     * how to perform it, the muscles it targets, etc.
     */
    private String description;

    /**
     * The categorization of the exercise, as defined in the {@link ExerciseType} enum.
     * This helps in grouping similar exercises.
     */
    private ExerciseType exerciseType;
}
