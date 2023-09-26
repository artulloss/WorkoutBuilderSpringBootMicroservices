package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.entity.Exercise;
import com.workoutbuilder.enterprise.entity.User;

import java.util.List;

public interface IExerciseService {

    /**
     * Save or update an exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     */
    Exercise saveExercise(Exercise exercise);

    /**
     * Delete an exercise.
     *
     * @param exercise The exercise to be deleted.
     */
    void deleteExercise(Exercise exercise);

    /**
     * Update an exercise.
     *
     * @param exercise The exercise to be updated.
     * @return The updated exercise.
     */
    Exercise updateExercise(Exercise exercise);

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID, or null if not found.
     */
    Exercise findById(long id);

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
     * Find exercises created by a specific user.
     *
     * @param user The user whose exercises are to be found.
     * @return A list of exercises created by the given user.
     */
    List<Exercise> findByUser(User user);

    /**
     * Count all exercises.
     *
     * @return The total number of exercises.
     */
    long count();
}