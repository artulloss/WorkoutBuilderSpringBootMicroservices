package com.workoutbuilder.enterprise.dto;

import lombok.Data;

public @Data class Exercise {

    public int id;
    private String name;
    private String workoutType;
    private String description;
    private int duration;

}
