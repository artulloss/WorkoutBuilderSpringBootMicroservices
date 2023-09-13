package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;

public interface IExerciseDAO {

    void saveExercise(Exercise exercise);
    void updateExercise(Exercise exercise);
    void deleteExercise(Exercise exercise);
    Exercise fetchExercise();
}
