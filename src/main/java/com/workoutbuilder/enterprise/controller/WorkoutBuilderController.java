package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling requests related to workouts and exercises.
 */
@Controller
public class WorkoutBuilderController {

    @Autowired
    IExerciseService exerciseService;

    /**
     * Maps the root URL ("/") to the index page.
     *
     * @param model Model object for UI rendering.
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index(Model model) {
        StoredExercise exercise = new StoredExercise();
        model.addAttribute("exercise", exercise);
        List<StoredExercise> exercises = exerciseService.findAll();
        model.addAttribute("exercises", exercises);
        return "/index";
    }

    /**
     * Retrieves a list of all exercises.
     *
     * @return List of exercises.
     */
    @GetMapping("api/exercise")
    @ResponseBody
    public List<StoredExercise> fetchAllExercises(){
        return exerciseService.findAll();
    }

    /**
     * Fetches an exercise by its ID.
     *
     * @param id ID of the exercise.
     * @return Exercise with the given ID.
     */
    @GetMapping("api/exercise/{id}")
    @ResponseBody
    public StoredExercise fetchExerciseById(@PathVariable("id") int id){
        return exerciseService.findById(id);
    }

    /**
     * Creates a new exercise.
     *
     * @param exercise Exercise object to be created.
     * @return Newly created exercise.
     */
    @PostMapping(value = "api/exercise", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public StoredExercise createExercise(@RequestBody StoredExercise exercise) {
        StoredExercise newExercise = null;
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
    @RequestMapping("api/saveExercise")
    public String saveExercise(StoredExercise exercise) {
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
