package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WorkoutBuilderController {

    @Autowired
    IExerciseService exerciseService;


    /**
     * Maps the root URL ("/") to the index page.
     *
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("api/exercise")
    @ResponseBody
    public List<Exercise> fetchAllExercises(){
        return exerciseService.findAll();
    }

    @PostMapping(value = "api/exercise", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Exercise createExercise(@RequestBody Exercise exercise) {
        Exercise newExercise = null;
        try {
            newExercise = exerciseService.saveExercise(exercise);
        } catch (Exception e) {
            //TODO LOG ERROR
        }

        return newExercise;
    }


}
