package com.workoutbuilder.enterprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutBuilderController {
    /**
     * Maps the root URL ("/") to the index page.
     *
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }
}
