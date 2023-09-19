package com.workoutbuilder.enterprise;

import com.workoutbuilder.enterprise.dto.UserDTO;
import com.workoutbuilder.enterprise.entity.User;
import com.workoutbuilder.enterprise.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

/**
 * AuthController handles incoming web requests for user authentication
 * and related actions like login, signup, and user dashboard.
 */
@Controller
public class AuthController {

    private final IUserService userService;

    /**
     * Constructs an AuthController with a specified IUserService.
     *
     * @param userService service to manage user related operations.
     */
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Maps the root URL ("/") to the index page.
     *
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * Maps the "/login" URL to the login page.
     * If a user is already authenticated, redirects to the user's dashboard.
     *
     * @param principal provides details of the currently authenticated user.
     * @return name of the login view or redirect to the user's dashboard.
     */
    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            User currentUser = userService.findByEmail(principal.getName());
            if (currentUser != null) {
                return "redirect:/" + currentUser.getId() + "/dashboard";
            }
        }
        return "/login";
    }

    /**
     * Maps the "/signup" URL to the signup page.
     * If a user is already authenticated, redirects to the user's dashboard.
     *
     * @param model model to bind objects to the view.
     * @param principal provides details of the currently authenticated user.
     * @return name of the signup view or redirect to the user's dashboard.
     */
    @GetMapping("/signup")
    public String signup(Model model, Principal principal) {
        if (principal != null) {
            User currentUser = userService.findByEmail(principal.getName());
            if (currentUser != null) {
                return "redirect:/" + currentUser.getId() + "/dashboard";
            }
        }
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "/signup";
    }

    /**
     * Handles the signup form submission.
     * Creates a new user if validation passes or returns back to the signup page with errors.
     *
     * @param userDTO DTO containing user's details from the signup form.
     * @param result binding result containing form validation errors.
     * @param model model to bind objects to the view.
     * @return redirect to the login page if successful, else returns to the signup page with errors.
     */
    @PostMapping("/signup")
    public String signupUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        User existingUser = userService.findByEmail(userDTO.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/signup";
        }
        userService.saveUser(userDTO);
        return "redirect:/login";
    }

    /**
     * Generic endpoint to handle URLs with just an ID.
     * This can validate the ID and return an error page if the ID is not valid.
     * If valid, redirect user to dashboard.
     * This might be useful to prevent potential issues caused by third-party tools or bots.
     *
     * @param id provided in the URL.
     * @return error page if the ID is invalid or not related to the authenticated user.
     */
    @GetMapping("/{id}")
    public String handleIdOnlyEndpoint(@PathVariable("id") Long id, Principal principal) {
        User currentUser = userService.findByEmail(principal.getName());

        if (id == null || currentUser.getId() != id) {
            return "/error";
        }
        else {
            return "redirect:/" + id + "/dashboard";
        }
    }

    /**
     * Maps to the user's dashboard page.
     *
     * @param id user's unique ID.
     * @param model model to bind objects to the view.
     * @param principal provides details of the currently authenticated user.
     * @return user's dashboard if the ID matches the authenticated user's ID, else returns an error page.
     */
    @GetMapping("/{id}/dashboard")
    public String dashboard(@PathVariable("id") Long id, Model model, Principal principal) {
        User currentUser = userService.findByEmail(principal.getName());

        if (id == null || currentUser.getId() != id) {
            return "/error";
        }

        model.addAttribute("user", currentUser);
        return "dashboard";
    }

    /**
     * Maps to the user's settings page.
     *
     * @param id user's unique ID.
     * @param model model to bind objects to the view.
     * @param principal provides details of the currently authenticated user.
     * @return user's settings if the ID matches the authenticated user's ID, else returns an error page.
     */
    @GetMapping("/{id}/settings")
    public String userSettings(@PathVariable("id") Long id, Model model, Principal principal) {
        User currentUser = userService.findByEmail(principal.getName());

        if (id == null || currentUser.getId() != id) {
            return "/error";
        }

        model.addAttribute("user", currentUser);
        return "settings";
    }
}
