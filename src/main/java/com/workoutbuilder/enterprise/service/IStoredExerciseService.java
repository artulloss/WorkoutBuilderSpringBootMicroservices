package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.StoredExercise;

import java.util.Optional;

public interface IStoredExerciseService {

    void deleteStoredExercise(int id) throws Exception;
    Optional<StoredExercise> findById(int id);

    StoredExercise saveStoredExercise(StoredExercise exercise);
}
