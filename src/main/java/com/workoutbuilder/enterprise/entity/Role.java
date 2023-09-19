package com.workoutbuilder.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a role within the system. This entity maps to the "roles" table in the database.
 * Roles are used to manage access control and permissions for users.
 * A role has attributes like id and name, and can be associated with multiple users.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    /**
     * Represents the unique ID of the role. This is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Represents the name of the role. Each role has a unique name.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Represents the users associated with this role.
     * A role can be associated with multiple users, and a user can have multiple roles.
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
