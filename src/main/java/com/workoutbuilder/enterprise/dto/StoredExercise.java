package com.workoutbuilder.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an individual exercise that is linked to a workout.
 * This DTO extends the {@link Exercise} DTO and adds fields for sets, reps, weight, and duration.
 * These will be different for each exercise, depending on the workout.
 *
 * Business logic will be applied to require that cardio exercises have a duration, and
 * everything else has sets, reps, and optionally weight.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Disregard null fields when serializing to JSON
@Entity
public class StoredExercise extends Exercise {

    /**
     * The unique identifier for this exercise.
     */
    @Generated
    @Id
    @Column(nullable = false)
    private long id;

    private Integer sets = null;

    private Integer reps = null;

    private Integer weight = null;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private int duration;

}
