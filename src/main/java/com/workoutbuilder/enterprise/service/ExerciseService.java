package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dao.IWorkoutDAO;
import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Provides service-level operations for managing exercises.
 * This service layer interacts with the underlying DAO to perform CRUD operations on exercises.
 */
@Service
public class ExerciseService implements IExerciseService {

    @Autowired
    private IWorkoutDAO workoutDAO;

    @Autowired
    private IExerciseDAO exerciseDAO;

    /**
     * Default constructor.
     */
    public ExerciseService() {
    }

    /**
     * Overloaded constructor for Dep. Inj. of the exercise DAO.
     *
     * @param exerciseDAO the DAO for exercises
     */
    public ExerciseService(IExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    /**
     * Saves a new exercise.
     *
     * @param exercise the exercise to be saved
     * @return the saved or updated exercise
     * @throws Exception if any issues arise during the save operation
     */
    public StoredExercise saveExercise(StoredExercise exercise) throws Exception {
        return exerciseDAO.saveExercise(exercise);
    }

    /**
     * Delete an exercise.
     *
     * @param id The exercise of the exercise to be deleted.
     */
    @Override
    public void deleteExercise(int id) throws Exception {
        exerciseDAO.deleteExercise(id);
    }

    /**
     * Deletes a specific exercise.
     *
     * @param exercise the exercise to be deleted
     */


    /**
     * Finds a specific exercise by its ID.
     *
     * @param id the ID of the exercise
     * @return the exercise matching the provided ID
     */
    public StoredExercise findById(int id) {
        return exerciseDAO.findById(id);
    }

    /**
     * Retrieves all exercises.
     *
     * @return a list of all exercises
     */
    public List<Exercise> findAll() throws IOException {
        return exerciseDAO.findAll();
    }

    /**
     * Retrieves a list of exercises associated with a name
     *
     * @param name name of the exercise
     * @return list of exercise by name
     * @throws IOException
     */
    public List<Exercise> findByName(String name) throws IOException {
        return exerciseDAO.findByName(name);
    }

    /**
     * Retrieves all exercises associated with a specific workout.
     *
     * @param workoutId the ID of the workout
     * @return a list of exercises associated with the specified workout
     */
    public List<StoredExercise> findExercisesByWorkoutId(int workoutId) {
        workoutId = workoutDAO.findById(workoutId).getId();
        return exerciseDAO.findExercisesByWorkoutId(workoutId);
    }

    /**
     * Finds exercises of a specific type.
     *
     * @param type the type/category of the exercise
     * @return a list of exercises that match the specified type
     */
    public List<StoredExercise> findByExerciseType(ExerciseType type) {
        return exerciseDAO.findByExerciseType(type);
    }
}
