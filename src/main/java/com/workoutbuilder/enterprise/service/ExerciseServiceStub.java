package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dto.Exercise;
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
    public Exercise saveExercise(Exercise exercise) throws Exception {
        return exerciseDAO.saveExercise(exercise);
    }

    /**
     * Delete an exercise.
     *
     * @param exercise The exercise to be deleted.
     */
    @Override
    public void deleteExercise(Exercise exercise) {

    }

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID.
     */
    @Override
    public Exercise findById(int id) {
        return null;
    }

    /**
     * Find all exercises.
     *
     * @return A list of all exercises.
     */
    @Override
    public List<Exercise> findAll() {
        return exerciseDAO.findAll();
    }

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    @Override
    public List<Exercise> findByExerciseType(ExerciseType type) {
        return null;
    }

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    @Override
    public List<Exercise> findExercisesByWorkoutId(int workoutId) {
        return null;
    }
}
