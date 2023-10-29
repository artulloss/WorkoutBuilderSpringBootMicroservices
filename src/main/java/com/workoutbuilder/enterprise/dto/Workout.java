package com.workoutbuilder.enterprise.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a workout that is essentially a collection of exercises.
 * <p>
 * This DTO captures details such as the workout's name, description, duration,
 * completion dates, and the individual exercises that make up the workout.
 * </p>
 */
@Data
public class Workout {

    /**
     * The unique identifier for this workout.
     */
    private int id;

    /**
     * The name of the workout (e.g., "Full Body Strength Routine").
     */
    private String name;

    /**
     * A detailed description of the workout, providing insights or instructions on
     * the purpose and benefits of the workout.
     */
    private String description;

    /**
     * The date when the workout took place
     */
    private Date date;


    /**
     * A list of stored exercises that constitute this workout.
     */
    private List<StoredExercise> exercises = new ArrayList<>();
}
