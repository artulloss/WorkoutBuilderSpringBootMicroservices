package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

/**
 * Represents the Data Access Object for Exercises.
 */
//@Repository
public class StoredExerciseDAO implements IStoredExerciseDAO {

    @Autowired
    private IStoredExerciseDAO storedExerciseDAO;

    @Autowired
    private IWorkoutDAO workoutDAO;

    /**
     * Represents a storage for exercises using a Map with exercise id as key.
     */
    private final Map<Long, StoredExercise> allExercises = new HashMap<>();

    /**
     * Retrieve all exercises.
     *
     * @return A list of all exercises.
     */
    @Override
    public Iterable<StoredExercise> findAll() throws IOException {
        return storedExerciseDAO.findAll();
    }

    /**
     * finds a list of exercises by a certain name search query
     * @param name the name of the exercise to be found
     * @return a list of exercises by name
     * @throws IOException if the call to the API fails
     */
    @Override
    public List<StoredExercise> findByName(String name) throws IOException {
        return storedExerciseDAO.findByName(name);
    }

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to find.
     * @return The exercise if found, otherwise null.
     */
    @Override
    public Optional<StoredExercise> findById(long id) {
        return storedExerciseDAO.findById(id);
    }

    /**
     * Find exercises associated with a specific workout ID.
     *
     * @param workoutId The ID of the workout.
     * @return A list of exercises associated with the given workout ID.
     */
    @Override
    public List<StoredExercise> findExercisesByWorkoutId(long workoutId) {
        Workout workout = workoutDAO.findById(workoutId);
        return workout.getExercises();
    }

    /**
     * Find exercises of a specific type.
     *
     * @param type The type of exercises to find.
     * @return A list of exercises of the specified type.
     */
    @Override
    public List<StoredExercise> findByType(ExerciseType type) {
        List<StoredExercise> returnExercises = new ArrayList<>();
        for (StoredExercise exercise : allExercises.values()) {
            if (exercise.getType().equals(type)) {
                returnExercises.add(exercise);
            }
        }
        return returnExercises;
    }

    /**
     * Save or update the given exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     */
    @Override
    public StoredExercise saveStoredExercise(StoredExercise exercise) {
        allExercises.put(exercise.getId(), exercise);
        return exercise;
    }

    /**
     * Delete an exercise by its ID.
     *
     * @param id The ID of the exercise to delete.
     */
    @Override
    public void deleteExercise(long id) {
        allExercises.remove(id);
    }
}
