package com.workoutbuilder.enterprise.entity;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Description cannot be null")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Exercise type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType exerciseType;

    @NotNull(message = "User cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
