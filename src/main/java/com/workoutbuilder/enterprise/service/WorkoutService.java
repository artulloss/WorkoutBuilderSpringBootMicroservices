package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.WorkoutRepository;
import com.workoutbuilder.enterprise.entity.Workout;
import com.workoutbuilder.enterprise.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService implements IWorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(Workout workout) {
        workoutRepository.delete(workout);
    }

    @Override
    public Workout updateWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public Workout findById(long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Workout> findByUser(User user) {
        return workoutRepository.findByUser(user);
    }

    @Override
    public long count() {
        return workoutRepository.count();
    }
}
