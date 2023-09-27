package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.entity.User;
import com.workoutbuilder.enterprise.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);
}
