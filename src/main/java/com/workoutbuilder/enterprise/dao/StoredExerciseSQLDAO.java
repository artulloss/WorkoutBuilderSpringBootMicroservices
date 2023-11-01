package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository("StoredExerciseDAO")
public class StoredExerciseSQLDAO implements IStoredExerciseDAO {

    @Autowired
    StoredExerciseRepository storedExerciseRepository;

    @Override
    public Iterable<StoredExercise> findAll() throws IOException {
        return storedExerciseRepository.findAll();
    }

    @Override
    public List<StoredExercise> findByName(String name) throws IOException {
        return storedExerciseRepository.findByName(name);
    }


    @Override
    public Optional<StoredExercise> findById(long id) {
        return storedExerciseRepository.findById(id);
    }

    @Override
    public List<StoredExercise> findExercisesByWorkoutId(long workoutId) {
        return null;
    }

    @Override
    public List<StoredExercise> findByExerciseType(ExerciseType type) {
        return storedExerciseRepository.findByExerciseType(type);
    }

    @Override
    public StoredExercise saveStoredExercise(StoredExercise exercise) throws Exception {
        return storedExerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(long id) throws Exception {
        storedExerciseRepository.deleteById(id);
    }
}
