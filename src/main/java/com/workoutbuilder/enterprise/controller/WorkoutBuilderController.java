package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling requests related to workouts and exercises.
 */
@Controller
@RequestMapping("/api/exercise")
public class WorkoutBuilderController {

    private final IExerciseService exerciseService;

    @Autowired
    public WorkoutBuilderController(IExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Retrieves a list of all exercises.
     *
     * @return List of exercises.
     */
    @GetMapping("/")
    @ResponseBody
    public List<Exercise> fetchAllExercises(){
        return exerciseService.findAll();
    }

    /**
     * Fetches an exercise by its ID.
     *
     * @param id ID of the exercise.
     * @return Exercise with the given ID.
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Exercise fetchExerciseById(@PathVariable("id") int id){
        return exerciseService.findById(id);
    }

    /**
     * Creates a new exercise.
     *
     * @param exercise Exercise object to be created.
     * @return Newly created exercise.
     */
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
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

    /**
     * Saves an exercise.
     *
     * @param exercise Exercise object to be saved.
     * @return Redirection to the index page.
     */
    @PutMapping("/updateExercise")
    public String updateExercise(Exercise exercise) {
        try {
            exerciseService.saveExercise(exercise);
        } catch (Exception e) {
            //TODO LOG ERROR
            return "/index";
        }
        return "/index";
    }

    /**
     * Deletes an exercise by its ID.
     *
     * @param id ID of the exercise to be deleted.
     * @return ResponseEntity with either HTTP OK status or HTTP Internal Server Error status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable("id") int id) {
        try{
            exerciseService.deleteExercise(id);
            return new ResponseEntity<>("Entry successfully deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Entry delete request FAILED.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
