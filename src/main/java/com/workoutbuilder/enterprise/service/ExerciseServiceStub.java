package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExerciseServiceStub implements IExerciseService{

    @Autowired
    IExerciseDAO exerciseDAO;

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to be found.
     * @return The exercise with the given ID.
     */
    @Override
    public Exercise findById(int id) {
        return exerciseDAO.findById(id);
    }

    /**
     * Find all exercises.
     *
     * @return A list of all exercises.
     */
    @Override
    public List<Exercise> findAll() throws IOException {
        return exerciseDAO.findAll();
    }

    /**
     * Finds a list of exercises with a certain name
     *
     * @param name name of the exercises to be found
     * @return list of exercises with a certain name
     * @throws IOException
     */
    @Override
    public List<Exercise> findByName(String name) throws IOException {
        return exerciseDAO.findByName(name);
    }

    /**
     * Find exercises by their type.
     *
     * @param type The type of the exercises to be found.
     * @return A list of exercises with the given type.
     */
    @Override
    public List<Exercise> findByType(ExerciseType type) {
        return null;
    }

    /**
     * Find exercises by a workout ID.
     *
     * @param workoutId The ID of the workout to be found.
     * @return A list of exercises with the given workout ID.
     */
    @Override
    public List<Exercise> findExercisesByWorkoutId(long workoutId) {
        return null;
    }
}
