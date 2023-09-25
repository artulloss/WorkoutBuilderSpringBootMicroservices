package com.workoutbuilder.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user within the system. This entity maps to the "users" table in the database.
 * A user has attributes like id, name, email, password, and is associated with roles.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    /**
     * Represents the unique ID of the user. This is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Represents the full name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Represents the email address of the user. This is unique for each user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Represents the password of the user, stored in a secure hashed format.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Represents the roles associated with the user.
     * A user can have multiple roles, and a role can be associated with multiple users.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Workout> workouts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exercise> exercises = new ArrayList<>();
}
