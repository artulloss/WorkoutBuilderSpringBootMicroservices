package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.IWorkoutDAO;
import com.workoutbuilder.enterprise.dao.WorkoutDAO;
import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides service-level operations for managing workouts.
 * This service layer interacts with the underlying DAO to perform CRUD operations on workouts.
 */
@Service
public class WorkoutService implements IWorkoutService {

    @Autowired
    private IWorkoutDAO workoutDAO;

    /**
     * Default constructor.
     */
    public WorkoutService() {
    }

    /**
     * Overloaded constructor for Dep. Inj. of the workout DAO.
     *
     * @param workoutDAO the DAO for workouts
     */
    public WorkoutService(WorkoutDAO workoutDAO) {
        this.workoutDAO = workoutDAO;
    }

    /**
     * Saves a new workout.
     *
     * @param workout the workout to be saved
     * @return the saved or updated workout
     */
    @Override
    public Workout saveWorkout(Workout workout) {
        return workoutDAO.saveWorkout(workout);
    }

    /**
     * Deletes a specific workout by its ID.
     *
     * @param id the ID of the workout to be deleted
     */
    @Override
    @CacheEvict(value="workout", key="#id")
    public void deleteWorkout(int id) {
        workoutDAO.deleteWorkout(id);
    }

    /**
     * Finds a specific workout by its ID.
     *
     * @param id the ID of the workout
     * @return the workout matching the provided ID
     */
    @Override
    @Cacheable(value="workout", key="#id")
    public Workout findById(int id) {
        return workoutDAO.findById(id);
    }

    /**s
     * Retrieves all workouts.
     *
     * @return a list of all workouts
     */
    @Override
    @Cacheable(value="workouts")
    public List<Workout> findAll() {
        return workoutDAO.findAll();
    }
}
