package com.workoutbuilder.enterprise.dto;

import lombok.Data;

/**
 * Represents an individual exercise that is brought in from an external API.
 *
 * This DTO captures the specific details of an exercise, such as its name, description,
 * type, muscle group, equipment, difficulty level, and instructions.
 *
 * Various enums represent different muscles, types, and difficulty levels.
 * {@link ExerciseMuscle}, {@link ExerciseType}, and {@link ExerciseDifficulty}
 */
@Data
public class Exercise {

    /**
     * The name of the exercise (e.g., "Incline Hammer Curls").
     */
    private String name;

    /**
     * The categorization of the exercise, as defined in the {@link ExerciseType} enum.
     * This helps in grouping similar exercises (e.g., "strength").
     */
    private ExerciseType type;

    /**
     * The muscle group targeted by the exercise, represented using the {@link ExerciseMuscle} enum (e.g., "biceps").
     */
    private ExerciseMuscle muscle;

    /**
     * The equipment required for the exercise (e.g., "dumbbell").
     */
    private String equipment;

    /**
     * The difficulty level of the exercise, represented using the {@link ExerciseDifficulty} enum (e.g., "beginner").
     */
    private ExerciseDifficulty difficulty;

    /**
     * A detailed description of the exercise, including instructions on how to perform it.
     */
    private String instructions;
}
