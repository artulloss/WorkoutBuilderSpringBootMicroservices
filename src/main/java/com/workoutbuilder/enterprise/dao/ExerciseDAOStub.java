package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExerciseDAOStub implements IExerciseDAO{

    List<Exercise> exercises = new ArrayList<>();

    /**
     * Retrieve all exercises stored in the system.
     *
     * @return A list of all exercises.
     */
    @Override
    public List<Exercise> findAll() {
        return exercises;
    }

    /**
     * Find and return an exercise by its unique ID.
     *
     * @param id The ID of the exercise to retrieve.
     * @return The exercise with the given ID or null if not found.
     */
    @Override
    public Exercise findById(int id) {
        return null;
    }

    /**
     * Retrieve exercises associated with a specific workout, identified by its ID.
     *
     * @param workoutId The unique ID of the workout.
     * @return A list of exercises associated with the given workout ID.
     */
    @Override
    public List<Exercise> findExercisesByWorkoutId(int workoutId) {
        return null;
    }

    /**
     * Find and return exercises of a specific type.
     *
     * @param type The type of exercises to retrieve.
     * @return A list of exercises of the specified type.
     */
    @Override
    public List<Exercise> findByExerciseType(ExerciseType type) {
        return null;
    }

    /**
     * Save or update a given exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     * @throws Exception If there is any issue in saving the exercise.
     */
    @Override
    public Exercise saveExercise(Exercise exercise) throws Exception {
        exercises.add(exercise);
        return exercise;
    }

    /**
     * Delete an exercise identified by its unique ID.
     *
     * @param id The ID of the exercise to be deleted.
     */
    @Override
    public void deleteExercise(int id) {

    }
}
