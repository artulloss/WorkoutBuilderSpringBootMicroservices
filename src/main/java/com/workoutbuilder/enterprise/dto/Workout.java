package com.workoutbuilder.enterprise.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

public @Data class Workout {

    public int id;
    private String workoutType;
    private Date date;
    private User createdBy;
    private List<Exercise> exercises;
    private int duration;

}
