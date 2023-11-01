package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoredExerciseRepository extends CrudRepository<StoredExercise, Long> {

    List<StoredExercise> findByName(String name);
    List<StoredExercise> findByExerciseType(ExerciseType type);
}
