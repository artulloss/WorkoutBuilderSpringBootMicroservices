package com.workoutbuilder.enterprise.dto;

import lombok.Data;
import java.util.ArrayList;
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
     * Duration of the workout in minutes.
     */
    private int duration;


    /**
     * A list of exercises that constitute this workout.
     */
    private List<Exercise> exercises = new ArrayList<>();
}
