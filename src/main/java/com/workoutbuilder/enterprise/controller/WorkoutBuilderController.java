package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.Workout;
import com.workoutbuilder.enterprise.service.IExerciseService;
import com.workoutbuilder.enterprise.service.IStoredExerciseService;
import com.workoutbuilder.enterprise.service.IWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling requests related to workouts and exercises.
 */
@Controller
public class WorkoutBuilderController {

    @Autowired
    IExerciseService exerciseService;

    @Autowired
    IStoredExerciseService storedExerciseService;

    @Autowired
    IWorkoutService workoutService;

    /**
     * Maps the root URL ("/") to the index page.
     *
     * @param model Model object for UI rendering.
     * @return name of the index view.
     */
    @GetMapping("/")
    public String index(Model model) throws IOException {
        StoredExercise storedExercise = new StoredExercise();
        Workout workout = new Workout();
        model.addAttribute("storedExercise", storedExercise);
        model.addAttribute("workout", workout);
        return "/index";
    }

    /**
     * Retrieves a list of all exercises.
     *
     * @return List of exercises.
     */
    @GetMapping("api/exercise")
    @ResponseBody
    public ResponseEntity<List<Exercise>> fetchAllExercises(){
        try {
            List<Exercise> exercises = exerciseService.findAll();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(exercises, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/searchExerciseAutocomplete")
    public ResponseEntity<List<Exercise>> searchExerciseAutocomplete(@RequestParam(value="name", required = true, defaultValue = "none") String name) {
        try {
            List<Exercise> exercises = exerciseService.findByName(name);
            if(exercises == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(exercises, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/searchExercise")
    public ResponseEntity<List<Exercise>> searchExercise(@RequestParam(value="name", required = true, defaultValue = "none") String name) {
        try {
            List<Exercise> exercises = exerciseService.findByName(name);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(exercises, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetches an exercise by its ID.
     *
     * @param id ID of the exercise.
     * @return Exercise with the given ID.
     */
    @GetMapping("api/exercise/{id}")
    @ResponseBody
    public Optional<StoredExercise> fetchExerciseById(@PathVariable("id") int id){
        return storedExerciseService.findById(id);
    }

    /**
     * Logs a new workout
     */
    @PostMapping(value = "api/workout", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Workout logWorkout(@RequestBody Workout workout) {
        // Save workout first (without exercises)
        workout = workoutService.saveWorkout(workout);

        List<StoredExercise> savedExercises = new ArrayList<>();
        for (StoredExercise exercise : workout.getExercises()) {
            exercise.setWorkout(workout);
            StoredExercise savedExercise = storedExerciseService.saveStoredExercise(exercise);
            savedExercises.add(savedExercise);
        }

        // Add saved exercises to the workout and update it
        workout.setExercises(savedExercises);
        return workoutService.saveWorkout(workout);
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
            newExercise = storedExerciseService.saveStoredExercise(exercise);
        } catch (Exception e) {
            //TODO LOG ERROR
        }
        return newExercise;
    }

    /**
     * Deletes an exercise by its ID.
     *
     * @param id ID of the exercise to be deleted.
     * @return ResponseEntity with either HTTP OK status or HTTP Internal Server Error status.
     */
    @DeleteMapping("api/exercise/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") int id) {
        try{
            storedExerciseService.deleteStoredExercise(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
