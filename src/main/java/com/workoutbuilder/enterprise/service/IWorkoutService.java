package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;

public interface IWorkoutService {

    /**
     * Fetches an exercise by a given id
     * @param id unique identifier for an exercise
     * @return The matching exercise or null if not found
     */
    Workout fetchById(int id);

    Workout save(Workout workout) throws Exception;

}
