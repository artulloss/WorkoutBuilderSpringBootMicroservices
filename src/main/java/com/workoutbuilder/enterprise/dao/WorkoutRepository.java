package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Workout;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
}
