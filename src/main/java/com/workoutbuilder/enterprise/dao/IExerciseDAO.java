package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.ExerciseDTO;

public interface IExerciseDAO {

    void saveExercise(ExerciseDTO exercise);
    void updateExercise(ExerciseDTO exercise);
    void deleteExercise(ExerciseDTO exercise);
    ExerciseDTO fetchExercise();
}
