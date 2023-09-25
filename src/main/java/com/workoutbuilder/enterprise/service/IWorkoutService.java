package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;

public interface IWorkoutService {


    /**
     * Fetch workout by a given ID
     * @return a workout
     */
    Workout fetchById(int id);
}
