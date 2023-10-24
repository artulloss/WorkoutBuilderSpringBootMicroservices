package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;

import java.io.IOException;
import java.util.List;

/**
 * Interface defining service-level operations for managing exercises.
 */
public interface IExerciseService {

    /**
     * Save an exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     */
    StoredExercise saveExercise(StoredExercise exercise) throws Exception;

    /**
     * Delete an exercise.
     *
     * @param id The exercise of the exercise to be deleted.
     */
    void deleteExercise(int id) throws Exception;

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID.
     */
    StoredExercise findById(int id);

    /**
     * Find all exercises.
     *
     * @return A list of all exercises.
     */
    List<Exercise> findAll() throws IOException;

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    List<StoredExercise> findByExerciseType(ExerciseType type);

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    List<StoredExercise> findExercisesByWorkoutId(int workoutId);
}