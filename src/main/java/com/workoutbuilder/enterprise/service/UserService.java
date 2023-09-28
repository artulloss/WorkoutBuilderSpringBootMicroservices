package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.RoleRepository;
import com.workoutbuilder.enterprise.dao.UserRepository;
import com.workoutbuilder.enterprise.dto.UserDTO;
import com.workoutbuilder.enterprise.entity.Role;
import com.workoutbuilder.enterprise.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer class that implements the IUserService interface. This class provides methods to interact with
 * the underlying data repositories to perform operations related to users and their roles.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserService with the provided UserRepository, RoleRepository, and PasswordEncoder.
     *
     * @param userRepository   the user repository to use for user operations
     * @param roleRepository   the role repository to use for role operations
     * @param passwordEncoder the password encoder to use for encoding passwords
     */
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves the provided UserDTO to the database as a User entity.
     *
     * @param userDTO the user data transfer object containing the user details
     */
    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    /**
     * Deletes the provided User from the database.
     *
     * @param user the user to be deleted
     */
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * Finds a User by their email address.
     *
     * @param email the email address of the user to find
     * @return the User entity if found, otherwise null
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks if the "ROLE_USER" role exists in the database, if not, it creates it.
     *
     * @return the Role entity for "ROLE_USER"
     */
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}