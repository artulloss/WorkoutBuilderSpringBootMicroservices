package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements IExerciseService {


    /**
     * @return
     */
    @Override
    public List<Exercise> fetchAll() {
        return null;
    }

    /**
     * @param id unique identifier for an exercise
     * @return our matching exercise or null if not found
     */
    @Override
    public Exercise fetchById(int id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Exercise> fetchByWorkoutId(int id) {
        return null;
    }

    /**
     * @param exercise
     * @return fluent
     * @throws Exception If error occurs while saving
     */
    @Override
    public Exercise save(Exercise exercise) throws Exception {
        return null;
    }

    /**
     * @param exercise
     * @throws Exception
     */
    @Override
    public void delete(Exercise exercise) throws Exception {

    }

    /**
     * @param exercise
     * @throws Exception
     */
    @Override
    public void update(Exercise exercise) throws Exception {

    }
}