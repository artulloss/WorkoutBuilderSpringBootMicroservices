package com.workoutbuilder.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    /**
     * Handle the root (/) endpoint and return the start (index) page.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handle the user specific dashboard endpoint when a user signs in or signs up.
     * Endpoint will become (/uid/dashboard) when we apply authentication.
     */
    @RequestMapping("autheduser")
    public String autheduser() {
        return "autheduser";
    }

    /**
     * Handle the login endpoint and return the login page.
     */
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /**
     * Handle the signup endpoint and return the signup page.
     */
    @RequestMapping("signup")
    public String signup() {
        return "signup";
    }
}
