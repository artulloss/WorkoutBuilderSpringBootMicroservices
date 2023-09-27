package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.entity.Workout;
import com.workoutbuilder.enterprise.entity.User;

import java.util.List;

public interface IWorkoutService {


    /**
     * Save or update a workout.
     *
     * @param workout The workout to be saved or updated.
     * @return The saved or updated workout.
     */
    Workout saveWorkout(Workout workout);

    /**
     * Delete a workout.
     *
     * @param workout The workout to be deleted.
     */
    void deleteWorkout(Workout workout);

    /**
     * Update a workout.
     *
     * @param workout The workout to be updated.
     * @return The updated workout.
     */
    Workout updateWorkout(Workout workout);

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
     * Find workouts created by a specific user.
     *
     * @param user The user whose workouts are to be found.
     * @return A list of workouts created by the given user.
     */
    List<Workout> findByUser(User user);

    /**
     * Count all workouts.
     *
     * @return The total number of workouts.
     */
    long count();
}