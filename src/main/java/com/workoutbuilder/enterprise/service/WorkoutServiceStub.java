package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.stereotype.Component;

/**
 * A stub implementation of the IWorkoutServiceStub interface.
 * For testing purposes.
 */
@Component
public class WorkoutServiceStub implements IWorkoutServiceStub {

    /**
     * Find workout by a given ID
     *
     * @param id id of workout object
     * @return a workout
     */
    @Override
    public Workout findById(int id) {
        Workout workout = new Workout();
        workout.setId(12);
        workout.setDuration(45);

        return workout;
    }
}
