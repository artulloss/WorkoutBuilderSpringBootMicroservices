package com.workoutbuilder.enterprise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Description cannot be null")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Duration cannot be null")
    @Column(nullable = false)
    private int duration;

    @ElementCollection
    @CollectionTable(name = "workout_completion_dates", joinColumns = @JoinColumn(name = "workout_id"))
    @Column(name = "date_completed")
    private List<LocalDate> datesCompleted = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;

    @NotNull(message = "User cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
