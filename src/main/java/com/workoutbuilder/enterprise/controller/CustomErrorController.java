package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.entity.User;
import com.workoutbuilder.enterprise.service.IUserService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * A custom error controller to handle application-specific error scenarios.
 * This controller is intended to provide additional error information
 * and user-specific data to the error page when an error occurs.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Service interface for user-related operations.
     */
    private final IUserService userService;

    /**
     * Constructor that initializes the userService dependency.
     *
     * @param userService a service interface providing user operations.
     */
    public CustomErrorController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Handle application errors and return a view named 'error'.
     * If the user is authenticated, their data will be fetched
     * and passed to the model for the error view.
     *
     * @param model      a model object to bind attributes for rendering the view.
     * @param principal  the current authenticated user, if any.
     * @return the error view name.
     */
    @RequestMapping("/error")
    public String handleError(Model model, Principal principal) {
        if (principal != null) {
            User currentUser = userService.findByEmail(principal.getName());
            model.addAttribute("user", currentUser);
        }
        return "error";
    }
}
