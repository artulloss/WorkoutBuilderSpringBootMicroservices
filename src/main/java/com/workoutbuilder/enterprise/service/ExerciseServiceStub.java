package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dto.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceStub implements IExerciseService
{
    @Autowired
    IExerciseDAO exerciseDAO;

    /**
     * @return
     */
    @Override
    public List<Exercise> fetchAll() {
        return null;
    }

    /**
     * @param id unique identifier for an exercise
     * @return
     */
    @Override
    public Exercise fetchById(int id) {
        Exercise exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("Bench Press");
        exercise.setDescription("Lay on bench and press barbell");
        return exercise;
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
     * @return
     * @throws Exception
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
