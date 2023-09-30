package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;

/**
 * Interface defining a stub for service-level operations related to workouts.
 * For testing purposes.
 */
public interface IWorkoutServiceStub {

    /**
     * find a workout by its ID.
     *
     * @param id The ID of the workout to be found.
     * @return The workout with the given ID.
     */
    Workout findById(int id);
}
