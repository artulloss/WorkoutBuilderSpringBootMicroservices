package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.ExerciseRepository;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.entity.Exercise;
import com.workoutbuilder.enterprise.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements IExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    public Exercise updateExercise(Exercise exercise) {
        // Assuming you check that the ID exists before updating
        return exerciseRepository.save(exercise);
    }

    public Exercise findById(long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> findByExerciseType(ExerciseType type) {
        return exerciseRepository.findByExerciseType(type);
    }

    public List<Exercise> findByUser(User user) {
        return exerciseRepository.findByUser(user);
    }

    public long count() {
        return exerciseRepository.count();
    }
}