package com.workoutbuilder.enterprise.dao;

import com.workoutbuilder.enterprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on the User entity.
 * This interface extends JpaRepository which provides JPA functionalities for the User entity.
 * It allows for the querying, updating, and deleting of User instances in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Fetches a User entity from the database based on the provided email address.
     *
     * @param email The email address used as a search parameter.
     * @return The User entity with the matching email or null if no such user exists.
     */
    User findByEmail(String email);
}
