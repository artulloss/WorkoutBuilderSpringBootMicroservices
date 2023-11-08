package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;

import java.io.IOException;
import java.util.List;

/**
 * Interface defining service-level operations for managing exercises.
 */
public interface IExerciseService {

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID.
     */
    Exercise findById(int id);

    /**
     * Find all exercises.
     *
     * @return A list of all exercises.
     */
    List<Exercise> findAll() throws IOException;

    /**
     * Finds a list of exercises with a certain name
     *
     * @param name name of the exercises to be found
     * @return list of exercises with a certain name
     * @throws IOException
     */
    List<Exercise> findByName(String name) throws IOException;

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    List<Exercise> findByType(ExerciseType type);

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    List<Exercise> findExercisesByWorkoutId(long workoutId);
}