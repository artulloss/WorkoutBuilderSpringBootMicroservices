package com.workoutbuilder.enterprise.service;

import com.workoutbuilder.enterprise.dao.UserRepository;
import com.workoutbuilder.enterprise.entity.Role;
import com.workoutbuilder.enterprise.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This service provides custom user details for authentication purposes.
 * It fetches user information from the MySQL db and maps roles to Spring's authorities.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new CustomUserDetailsService.
     *
     * @param userRepository The repository to fetch user information.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details by the provided email.
     * Fetches the user's details from the MySQL db and maps their roles to authorities.
     *
     * @param email The email of the user.
     * @return A UserDetails object filled with user information.
     * @throws UsernameNotFoundException If the user is not found in the MySQL db.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles())
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    /**
     * Maps a collection of Role entities to a collection of GrantedAuthority objects.
     *
     * @param roles The collection of roles to be mapped.
     * @return A collection of GrantedAuthority objects.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
