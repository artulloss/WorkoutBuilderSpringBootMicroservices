package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on the Role entity.
 * This interface extends JpaRepository which provides JPA functionalities for the Role entity.
 * It facilitates querying, updating, and deleting Role instances in the MySQL db.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Fetches a Role entity from the database based on the provided role name.
     *
     * @param name The role name used as a search parameter.
     * @return The Role entity with the matching name or null if no such role exists.
     */
    Role findByName(String name);
}
