package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Exercise;

import java.util.List;

public interface IExerciseService {

    List<Exercise> fetchAll();

    /**
     * Fetches an exercise by a given id
     * @param id unique identifier for an exercise
     * @return The matching exercise or null if not found
     */
    Exercise fetchById(int id);

    List<Exercise> fetchByWorkoutId(int id);


    Exercise save(Exercise exercise) throws Exception;

    void delete(Exercise exercise) throws Exception;

    void update(Exercise exercise) throws Exception;

}
