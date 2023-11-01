package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Data Access Object for Workouts.
 */
@Repository
public class WorkoutDAO implements IWorkoutDAO {

    /**
     * Represents a storage for workouts using a Map with workout id as key.
     */
    final private Map<Long, Workout> allWorkouts = new HashMap<>();

    /**
     * Find a workout by its ID.
     *
     * @param id The ID of the workout to find.
     * @return The workout if found, otherwise null.
     */
    @Override
    public Workout findById(long id) {
        return allWorkouts.get(id);
    }

    /**
     * Retrieve all workouts.
     *
     * @return A list of all workouts.
     */
    @Override
    public List<Workout> findAll() {
        return new ArrayList<>(allWorkouts.values());
    }

    /**
     * Save or update the given workout.
     *
     * @param workout The workout to be saved or updated.
     * @return The saved or updated workout.
     */
    @Override
    public Workout saveWorkout(Workout workout) {
        allWorkouts.put(workout.getId(), workout);
        return workout;
    }

    /**
     * Delete a workout by its ID.
     *
     * @param id The ID of the workout to delete.
     */
    @Override
    public void deleteWorkout(long id) {
        allWorkouts.remove(id);
    }
}
