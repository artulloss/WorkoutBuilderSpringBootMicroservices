package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;

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
    Exercise saveExercise(Exercise exercise) throws Exception;

    /**
     * Delete an exercise.
     *
     * @param exercise The exercise to be deleted.
     */
    void deleteExercise(Exercise exercise);

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
    List<Exercise> findAll();

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    List<Exercise> findByExerciseType(ExerciseType type);

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    List<Exercise> findExercisesByWorkoutId(int workoutId);
}