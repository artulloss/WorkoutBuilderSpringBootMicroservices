package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IStoredExerciseDAO;
import com.workoutbuilder.enterprise.dao.IWorkoutDAO;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Provides service-level operations for managing exercises.
 * This service layer interacts with the underlying DAO to perform CRUD operations on exercises.
 */
@Service
public class StoredExerciseService implements IStoredExerciseService {

    @Autowired
    private IWorkoutDAO workoutDAO;

    @Autowired
    private IStoredExerciseDAO storedExerciseDAO;

    /**
     * Default constructor.
     */
    public StoredExerciseService() {
    }

    /**
     * Overloaded constructor for Dep. Inj. of the exercise DAO.
     *
     * @param storedExerciseDAO the DAO for exercises
     */
    public StoredExerciseService(IStoredExerciseDAO storedExerciseDAO) {
        this.storedExerciseDAO = storedExerciseDAO;
    }

    /**
     * Saves a new exercise.
     *
     * @param exercise the exercise to be saved
     * @return the saved or updated exercise
     */
    public StoredExercise saveStoredExercise(StoredExercise exercise) {
        try {
            return storedExerciseDAO.saveStoredExercise(exercise);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete an exercise.
     *
     * @param id The exercise of the exercise to be deleted.
     */
    @Override
    public void deleteStoredExercise(int id) throws Exception {
        storedExerciseDAO.deleteExercise(id);
    }

    /**
     * Finds a specific exercise by its ID.
     *
     * @param id the ID of the exercise
     * @return the exercise matching the provided ID
     */
    public Optional<StoredExercise> findById(int id) {
        return storedExerciseDAO.findById(id);
    }

    /**
     * Retrieves all exercises.
     *
     * @return a list of all exercises
     */
    public Iterable<StoredExercise> findAll() throws IOException {
        return storedExerciseDAO.findAll();
    }

    /**
     * Retrieves a list of exercises associated with a name
     *
     * @param name name of the exercise
     * @return list of exercise by name
     * @throws IOException
     */
    public List<StoredExercise> findByName(String name) throws IOException {
        return storedExerciseDAO.findByName(name);
    }

    /**
     * Retrieves all exercises associated with a specific workout.
     *
     * @param workoutId the ID of the workout
     * @return a list of exercises associated with the specified workout
     */
    public static List<StoredExercise> findExercisesByWorkoutId(long workoutId) {
        workoutId = workoutDAO.findById(workoutId).getId();
        return storedExerciseDAO.findExercisesByWorkoutId(workoutId);
    }

    /**
     * Finds exercises of a specific type.
     *
     * @param type the type/category of the exercise
     * @return a list of exercises that match the specified type
     */
    public List<StoredExercise> findByType(ExerciseType type) {
        return storedExerciseDAO.findByType(type);
    }
}
