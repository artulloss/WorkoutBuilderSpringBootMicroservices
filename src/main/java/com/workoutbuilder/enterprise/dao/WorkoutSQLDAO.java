package com.workoutbuilder.enterprise.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutSQLDAO {

    @Autowired
    WorkoutRepository workoutRepository;

}
