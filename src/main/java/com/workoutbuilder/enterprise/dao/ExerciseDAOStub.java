package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExerciseDAOStub implements IExerciseDAO {

    List<Exercise> exerciseList = new ArrayList<>();

    @Override
    public void saveExercise(Exercise exercise) throws Exception {
        exerciseList.add(exercise);
    }

    @Override
    public void updateExercise(Exercise exercise) throws Exception {
        // We rebuild the list with the new exercise replace where the id matches
        exerciseList = exerciseList.stream()
                .map(existingExercise -> existingExercise.getId() == exercise.getId() ? exercise : existingExercise)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExercise(Exercise exercise) throws Exception {
        exerciseList.removeIf(existingExercise -> existingExercise.getId() == exercise.getId());
    }

    @Override
    public List<Exercise> fetchAll() {
        return exerciseList;
    }
}
