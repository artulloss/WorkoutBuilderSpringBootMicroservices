package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;

import java.io.IOException;
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
    List<Exercise> findAll() throws IOException;

    List<Exercise> findByName(String name) throws IOException;

    /**
     * Find and return an exercise by its unique ID.
     *
     * @param id The ID of the exercise to retrieve.
     * @return The exercise with the given ID or null if not found.
     */
    StoredExercise findById(long id);

    /**
     * Retrieve exercises associated with a specific workout, identified by its ID.
     *
     * @param workoutId The unique ID of the workout.
     * @return A list of exercises associated with the given workout ID.
     */
    List<StoredExercise> findExercisesByWorkoutId(long workoutId);

    /**
     * Find and return exercises of a specific type.
     *
     * @param type The type of exercises to retrieve.
     * @return A list of exercises of the specified type.
     */
    List<StoredExercise> findByType(ExerciseType type);
}
