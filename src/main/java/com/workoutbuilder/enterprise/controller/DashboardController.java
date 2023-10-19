package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {
    private final IExerciseService exerciseService;
    @Autowired
    public DashboardController(IExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Maps the root URL ("/") to the index page.
     *
     * @param model Model object for UI rendering.
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index(Model model) {
        Exercise exercise = new Exercise();
        model.addAttribute("exercise", exercise);
        List<Exercise> exercises = exerciseService.findAll();
        model.addAttribute("exercises", exercises);
        return "/index";
    }
}
