package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.entity.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutServiceStub implements IWorkoutServiceStub {

    /**
     * Fetch workout by a given ID
     *
     * @param id id of workout object
     * @return a workout
     */
    @Override
    public Workout fetchById(int id) {
        Workout workout = new Workout();
        workout.setId(12);
        workout.setDuration(45);

        return workout;
    }
}
