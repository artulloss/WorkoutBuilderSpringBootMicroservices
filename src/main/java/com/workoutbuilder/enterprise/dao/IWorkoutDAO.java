package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Workout;

import java.util.List;
public interface IWorkoutDAO {

    /**
     * Find a workout by its ID.
     *
     * @param id The ID of the workout to be found.
     * @return The workout with the given ID, or null if not found.
     */
    Workout findById(long id);

    /**
     * Find all workouts.
     *
     * @return A list of all workouts.
     */
    List<Workout> findAll();

    /**
     * Save a workout.
     *
     * @param workout The workout to be saved.
     */
    Workout saveWorkout(Workout workout);

    /**
     * Delete a workout.
     *
     * @param id The workout to be deleted.
     */
    void deleteWorkout(long id);
}
