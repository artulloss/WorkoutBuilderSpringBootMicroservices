package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.dto.Exercise;
import lombok.val;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ExerciseDAO implements IExerciseDAO {
    /**
     * @param exercise
     */
    @Override
    public void saveExercise(Exercise exercise) {
    }

    /**
     * @param exercise For update, send the same id, and change everything else
     */
    @Override
    public void updateExercise(Exercise exercise) {

    }

    /**
     * @param exercise
     */
    @Override
    public void deleteExercise(Exercise exercise) {

    }

//    @Override
//    public Exercise fetchAll() {
////        val retrofitInstance = RetrofitInstance.getRetrofitInstance();
//    }
}
