package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.entity.Workout;

public interface IWorkoutServiceStub {
    /**
     * fetch a workout by its ID.
     *
     * @param id The ID of the workout to be found.
     * @return The workout with the given ID, or null if not found.
     */
    Workout fetchById(int id);
}
