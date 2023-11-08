package com.workoutbuilder.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * Represents an individual exercise that is brought in from an external API.
 *
 * This DTO captures the specific details of an exercise, such as its name, description,
 * type, muscle group, equipment, difficulty level, and instructions.
 *
 * Various enums represent different muscles, types, and difficulty levels.
 * {@link ExerciseMuscle}, {@link ExerciseType}, and {@link ExerciseDifficulty}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class Exercise {

    /**
     * The name of the exercise (e.g., "Incline Hammer Curls").
     */
    @SerializedName("name")
    private String name;

    /**
     * The categorization of the exercise, as defined in the {@link ExerciseType} enum.
     * This helps in grouping similar exercises (e.g., "strength").
     */
    @SerializedName("type")
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ExerciseType type;

    /**
     * The muscle group targeted by the exercise, represented using the {@link ExerciseMuscle} enum (e.g., "biceps").
     */
    @SerializedName("muscle")
    private ExerciseMuscle muscle;

    /**
     * The equipment required for the exercise (e.g., "dumbbell").
     */
    @SerializedName("equipment")
    private String equipment;

    /**
     * The difficulty level of the exercise, represented using the {@link ExerciseDifficulty} enum (e.g., "beginner").
     */
    @SerializedName("difficulty")
    private ExerciseDifficulty difficulty;

    /**
     * A detailed description of the exercise, including instructions on how to perform it.
     */
    @SerializedName("instructions")
    private String instructions;
}
