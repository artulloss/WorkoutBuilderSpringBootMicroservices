package com.workoutbuilder.enterprise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class UserDTO {
    private long id;

    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters.")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters.")
    private String lastName;

    @NotEmpty(message = "This field is required.")
    @Email(message = "Please enter a valid email address.")
    @Size(max = 100, message = "Email should not exceed 100 characters.")
    private String email;

    @Size(min = 8, max = 50, message = "Password should be between 8 and 50 characters.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password should have at least one lowercase, one uppercase, one number, one special character and no spaces.")
    private String password;
}