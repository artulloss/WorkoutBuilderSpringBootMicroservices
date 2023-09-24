package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;

import java.util.List;

public interface IExerciseDAO {

    void saveExercise(Exercise exercise) throws Exception;
    void updateExercise(Exercise exercise) throws Exception;
    void deleteExercise(Exercise exercise) throws Exception;
    List<Exercise> fetchAll();
}
