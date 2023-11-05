package com.workoutbuilder.enterprise.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a workout that is essentially a collection of exercises.
 * <p>
 * This DTO captures details such as the workout's name, description, duration,
 * completion dates, and the individual exercises that make up the workout.
 * </p>
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Workout {

    /**
     * The unique identifier for this workout.
     */
    @GeneratedValue
    @Id
    @Column(nullable = false)
    private long id;

    /**
     * The name of the workout (e.g., "Full Body Strength Routine").
     */
    private String name;

    /**
     * A detailed description of the workout, providing insights or instructions on
     * the purpose and benefits of the workout.
     */
    private String description;

    /**
     * The date when the workout took place
     */
    private Date date;


    /**
     * A list of stored exercises that constitute this workout.
     */
    @OneToMany(mappedBy = "workout")
    @ToString.Exclude
    private List<StoredExercise> exercises = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Workout workout = (Workout) o;
        return id == workout.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
