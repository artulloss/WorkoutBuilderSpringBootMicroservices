package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dto.UserDTO;
import com.workoutbuilder.enterprise.entity.User;

/**
 * Interface defining core operations related to user management.
 */
public interface IUserService {

    /**
     * Saves the provided UserDTO into the MySQL db.
     *
     * @param userDTO The user data transfer object containing the details of the user to be saved.
     */
    void saveUser(UserDTO userDTO);

    /**
     * Deletes the provided User from the MySQL db.
     *
     * @param user The user to be deleted.
     */
    void deleteUser(User user);

    /**
     * Retrieves a user entity from the MySQL db based on their email.
     *
     * @param email The email address of the user to be retrieved.
     * @return The User entity associated with the given email, or null if no user is found.
     */
    User findByEmail(String email);
}
