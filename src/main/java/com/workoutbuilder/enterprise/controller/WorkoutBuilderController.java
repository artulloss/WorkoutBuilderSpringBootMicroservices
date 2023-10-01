package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/exercise/{id}")
    @ResponseBody
    public Exercise fetchExerciseById(@PathVariable("id") int id){
        return exerciseService.findById(id);
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

    @DeleteMapping("api/exercise/{id}")
    public ResponseEntity deleteExercise(@PathVariable("id") int id) {
        try{
            exerciseService.deleteExercise(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
