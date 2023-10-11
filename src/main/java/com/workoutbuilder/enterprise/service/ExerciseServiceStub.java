package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceStub implements IExerciseService{

    @Autowired
    IExerciseDAO exerciseDAO;


    /**
     * Save an exercise.
     *
     * @param exercise The exercise to be saved or updated.
     * @return The saved or updated exercise.
     */
    @Override
    public StoredExercise saveExercise(StoredExercise exercise) throws Exception {
        return exerciseDAO.saveExercise(exercise);
    }

    /**
     * Delete an exercise.
     *
     * @param id The id of the exercise to be deleted.
     */
    @Override
    public void deleteExercise(int id) throws Exception {
        exerciseDAO.deleteExercise(id);
    }

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID.
     */
    @Override
    public StoredExercise findById(int id) {
        return exerciseDAO.findById(id);
    }

    /**
     * Find all exercises.
     *
     * @return A list of all exercises.
     */
    @Override
    public List<StoredExercise> findAll() {
        return exerciseDAO.findAll();
    }

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    @Override
    public List<StoredExercise> findByExerciseType(ExerciseType type) {
        return null;
    }

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    @Override
    public List<StoredExercise> findExercisesByWorkoutId(int workoutId) {
        return null;
    }
}
