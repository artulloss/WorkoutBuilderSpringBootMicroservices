package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.entity.Exercise;
import com.workoutbuilder.enterprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByExerciseType(ExerciseType type);
    List<Exercise> findByUser(User user);
}