package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Data Access Object for Workouts.
 */
@Repository
public class WorkoutDAO implements IWorkoutDAO {

    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Find a workout by its ID.
     *
     * @param id The ID of the workout to find.
     * @return The workout if found, otherwise null.
     */
    @Override
    public Workout findById(long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    /**
     * Retrieve all workouts.
     *
     * @return A list of all workouts.
     */
    @Override
    public List<Workout> findAll() {
        List<Workout> workouts = new ArrayList<>();
        workoutRepository.findAll().forEach(workouts::add);
        return workouts;
    }

    /**
     * Save or update the given workout.
     *
     * @param workout The workout to be saved or updated.
     * @return The saved or updated workout.
     */
    @Override
    public Workout saveWorkout(Workout workout) {
        workoutRepository.save(workout);
        return workout;
    }

    /**
     * Delete a workout by its ID.
     *
     * @param id The ID of the workout to delete.
     */
    @Override
    public void deleteWorkout(long id) {
        workoutRepository.deleteById(id);
    }
}
