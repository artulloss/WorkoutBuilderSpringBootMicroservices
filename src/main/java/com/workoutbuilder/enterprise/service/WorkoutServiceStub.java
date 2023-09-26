package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutServiceStub implements IWorkoutService {


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
        workout.setWorkoutType("Cardio");

        return workout;
    }
}
