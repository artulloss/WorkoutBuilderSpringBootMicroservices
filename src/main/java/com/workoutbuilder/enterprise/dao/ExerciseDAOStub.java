package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExerciseDAOStub implements IExerciseDAO {

    HashMap<Number, Exercise> exercises = new HashMap<>();

    @Override
    public void saveExercise(Exercise exercise) throws Exception {
        exercises.put(exercise.getId(), exercise);
    }

    @Override
    public void updateExercise(Exercise exercise) throws Exception {
        if(exercises.containsKey(exercise.getId())) {
            exercises.put(exercise.getId(), exercise); // Overwrites existing exercise
        } else {
            throw new Exception("Exercise does not exist");
        }
    }

    @Override
    public void deleteExercise(Exercise exercise) throws Exception {
        exercises.remove(exercise.getId());
    }

    @Override
    public List<Exercise> fetchAll() {
        return new ArrayList<>(exercises.values());
    }
}
