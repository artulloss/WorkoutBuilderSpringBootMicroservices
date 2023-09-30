package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;

import java.util.List;

/**
 * IExerciseDAO provides the contract for managing and retrieving exercises in the system.
 */
public interface IExerciseDAO {

    /**
     * Retrieve all exercises stored in the system.
     *
     * @return A list of all exercises.
     */
    List<Exercise> findAll();

    /**
     * Find and return an exercise by its unique ID.
     *
     * @param id The ID of the exercise to retrieve.
     * @return The exercise with the given ID or null if not found.
     */
    Exercise findById(int id);

    /**
     * Retrieve exercises associated with a specific workout, identified by its ID.
     *
     * @param workoutId The unique ID of the workout.
     * @return A list of exercises associated with the given workout ID.
     */
    List<Exercise> findExercisesByWorkoutId(int workoutId);

    /**
     * Find and return exercises of a specific type.
     *
     * @param type The type of exercises to retrieve.
     * @return A list of exercises of the specified type.
     */
    List<Exercise> findByExerciseType(ExerciseType type);

    /**
     * Save or update a given exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     * @throws Exception If there is any issue in saving the exercise.
     */
    Exercise saveExercise(Exercise exercise) throws Exception;

    /**
     * Delete an exercise identified by its unique ID.
     *
     * @param id The ID of the exercise to be deleted.
     */
    void deleteExercise(int id) throws Exception;
}
