package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.Workout;

import java.util.List;

public interface IWorkoutDAO {

    void saveWorkout(Workout exercise) throws Exception;
    void updateWorkout(Workout exercise) throws Exception;
    void deleteWorkout(Workout exercise) throws Exception;
    List<Workout> fetchAll();
}
