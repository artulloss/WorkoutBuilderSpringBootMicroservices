package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;

import java.util.List;

/**
 * Interface defining service-level operations for managing workouts.
 */
public interface IWorkoutService {

    /**
     * Save a workout.
     *
     * @param workout The workout to be saved.
     * @return The saved workout.
     */
    Workout saveWorkout(Workout workout);

    /**
     * Delete a workout.
     *
     * @param id The workout to be deleted.
     */
    void deleteWorkout(int id);

    /**
     * Find a workout by its ID.
     *
     * @param id The ID of the workout to be found.
     * @return The workout with the given ID.
     */
    Workout findById(int id);

    /**
     * Find all workouts.
     *
     * @return A list of all workouts.
     */
    List<Workout> findAll();
}