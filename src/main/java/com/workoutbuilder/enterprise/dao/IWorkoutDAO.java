package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.Workout;

public interface IWorkoutDAO {

    void saveWorkout(Workout exercise);
    void updateWorkout(Workout exercise);
    void deleteWorkout(Workout exercise);
    Workout fetchWorkout();

}
