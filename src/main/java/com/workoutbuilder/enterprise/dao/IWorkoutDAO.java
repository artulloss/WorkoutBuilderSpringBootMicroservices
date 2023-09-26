package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.WorkoutDTO;

public interface IWorkoutDAO {

    void saveWorkout(WorkoutDTO workoutDTO);
    void updateWorkout(WorkoutDTO workoutDTO);
    void deleteWorkout(WorkoutDTO workout);
    WorkoutDTO fetchWorkout();
}
