package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Data Access Object for Exercises.
 */
@Repository
public class ExerciseDAO implements IExerciseDAO {

    @Autowired
    private IWorkoutDAO workoutDAO;

    @Autowired
    private Retrofit retrofit;

    /**
     * Represents a storage for exercises using a Map with exercise id as key.
     */
    private final Map<Integer, StoredExercise> allExercises = new HashMap<>();

    /**
     * Retrieve all exercises.
     *
     * @return A list of all exercises.
     */
    @Override
    public List<Exercise> findAll() throws IOException {

        IExerciseRetrofitDAO exerciseRetrofitDAO = retrofit.create(IExerciseRetrofitDAO.class);
        Call<List<Exercise>> retrieveExercises = exerciseRetrofitDAO.getExercises();
        Response<List<Exercise>> exercises = retrieveExercises.execute();
        return exercises.body();

    }

    /**
     * finds a list of exercises by a certain name search query
     * @param name the name of the exercise to be found
     * @return a list of exercises by name
     * @throws IOException if the call to the API fails
     */
    public List<Exercise> findByName(String name) throws IOException {

        IExerciseRetrofitDAO exerciseRetrofitDAO = retrofit.create(IExerciseRetrofitDAO.class);
        Call<List<Exercise>> retrieveExercises = exerciseRetrofitDAO.getExercisesByName(name);
        Response<List<Exercise>> exercises = retrieveExercises.execute();
        return exercises.body();

    }

    /**
     * Find an exercise by its ID.
     *
     * @param id The ID of the exercise to find.
     * @return The exercise if found, otherwise null.
     */
    @Override
    public StoredExercise findById(long id) {
        return allExercises.get(id);
    }

    /**
     * Find exercises associated with a specific workout ID.
     *
     * @param workoutId The ID of the workout.
     * @return A list of exercises associated with the given workout ID.
     */
    @Override
    public List<StoredExercise> findExercisesByWorkoutId(long workoutId) {
        Workout workout = workoutDAO.findById(workoutId);
        return workout.getExercises();
    }

    /**
     * Find exercises of a specific type.
     *
     * @param type The type of exercises to find.
     * @return A list of exercises of the specified type.
     */
    @Override
    public List<StoredExercise> findByType(ExerciseType type) {
        List<StoredExercise> returnExercises = new ArrayList<>();
        for (StoredExercise exercise : allExercises.values()) {
            if (exercise.getType().equals(type)) {
                returnExercises.add(exercise);
            }
        }
        return returnExercises;
    }
}
