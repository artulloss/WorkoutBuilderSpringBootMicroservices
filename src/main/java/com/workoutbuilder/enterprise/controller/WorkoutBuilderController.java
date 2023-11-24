package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.CalendarWorkout;
import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.Workout;
import com.workoutbuilder.enterprise.service.IExerciseService;
import com.workoutbuilder.enterprise.service.IStoredExerciseService;
import com.workoutbuilder.enterprise.service.IWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling requests related to workouts and
 * exercises.
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

        model.addAttribute("currentPage", "dashboard");
        return "/index";
    }

    /**
     * Maps the '/calendar' URL to the calendar page
     * 
     * @param model Model object for UI rendering.
     * @return calendar page
     * @throws IOException exception
     */
    @GetMapping("/calendar")
    public String calendar(Model model) throws IOException {
        model.addAttribute("currentPage", "calendar");
        return "/calendar";
    }

    /**
     * Maps a workout with an ID to a page to view workout details
     * 
     * @param model Model object for UI rendering
     * @param id    workout ID
     * @return single workout page
     * @throws IOException exception
     */
    @GetMapping("/workout/{id}")
    public String workout(Model model, @PathVariable("id") int id) throws IOException {
        Optional<Workout> workout = Optional.ofNullable(workoutService.findById(id));
        if (workout.isPresent()) {
            model.addAttribute("workout", workout.get());
            model.addAttribute("currentPage", "workout");
            return "/single-workout";
        } else {
            return "redirect:/";
        }
    }

    /**
     * Maps an exercise with an ID to a page to view exercise details
     * 
     * @param model Model object for UI rendering
     * @return single exercise page
     * @throws IOException exception
     */
    @GetMapping("/exercise/{name}")
    public String singleExercise(Model model, @PathVariable("name") String name) throws IOException {
        Exercise exercise = exerciseService.findExercise(name);
        if (exercise != null) {
            model.addAttribute("exercise", exercise);
            model.addAttribute("currentPage", "exercise");
            return "/single-exercise";
        } else {
            return "redirect:/";
        }
    }

    /**
     * Maps the '/exercises' URL to the exercise library page
     * 
     * @param model Model object for UI rendering
     * @return exercise library page
     * @throws IOException exercise
     */
    @GetMapping("/exercises")
    public String exercises(Model model) throws IOException {
        model.addAttribute("currentPage", "exercise");
        return "/exercises";
    }

    /**
     * Retrieves a list of all exercises.
     *
     * @return List of exercises.
     */
    @GetMapping("api/exercise")
    @ResponseBody
    public ResponseEntity<List<Exercise>> fetchAllExercises() {
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

    /**
     * Returns a list of exercises from api from a given name
     * 
     * @param name search name for exercises to be found
     * @return a list of 10 exercises
     */
    @GetMapping("api/searchExerciseAutocomplete")
    public ResponseEntity<List<Exercise>> searchExerciseAutocomplete(
            @RequestParam(value = "name", required = true, defaultValue = "none") String name) {
        try {
            List<Exercise> exercises = exerciseService.findByName(name);
            if (exercises == null) {
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

    /**
     * Returns a single exercise from api from a given name
     * 
     * @param name name of the exercise to be found
     * @return a single exercise
     */
    @GetMapping("api/searchExercise")
    public ResponseEntity<Exercise> searchExercise(
            @RequestParam(value = "name", required = true, defaultValue = "none") String name) {
        try {
            Exercise exercise = exerciseService.findExercise(name);

            if (exercise == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(exercise, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
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
    public Optional<StoredExercise> fetchExerciseById(@PathVariable("id") int id) {
        return storedExerciseService.findById(id);
    }

    /**
     * Gets workouts for a given date range.
     * For use with FullCalendar.
     * 
     * @link <a href="https://fullcalendar.io/docs/events-json-feed">Fullcalendar
     *       JSON feed</a>
     * @return List of workout dates and IDs.
     */
    @GetMapping("api/workout")
    @ResponseBody
    public ResponseEntity<List<CalendarWorkout>> fetchWorkouts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<Workout> workouts = workoutService.findAll();

        var calendarWorkouts = new ArrayList<CalendarWorkout>();

        for (var workout : workouts) {
            // Convert java.util.Date to LocalDateTime
            LocalDateTime workoutStart = workout.getStart().toInstant()
                    .atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                    .toLocalDateTime();

            LocalDateTime workoutEnd = workout.getEnd().toInstant()
                    .atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                    .toLocalDateTime();

            // Check if workout date is within the specified range
            if ((start == null || !workoutStart.isBefore(start)) && (end == null || !workoutEnd.isAfter(end))) {
                var calendarWorkout = new CalendarWorkout();
                calendarWorkout.setTitle(workout.getName());
                calendarWorkout.setStart(workoutStart.format(DateTimeFormatter.ISO_DATE_TIME));
                calendarWorkout.setEnd(workoutEnd.format(DateTimeFormatter.ISO_DATE_TIME));
                calendarWorkout.setUrl("/workout/" + workout.getId());

                calendarWorkouts.add(calendarWorkout);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(calendarWorkouts, headers, HttpStatus.OK);
    }

    /**
     * Logs a new workout
     */
    @PostMapping(value = "api/workout", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> logWorkout(@RequestBody Workout workout) {
        try {
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
            return new ResponseEntity<>(workoutService.saveWorkout(workout), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            // TODO LOG ERROR
        }
        return newExercise;
    }

    /**
     * Deletes an exercise by its ID.
     *
     * @param id ID of the exercise to be deleted.
     * @return ResponseEntity with either HTTP OK status or HTTP Internal Server
     *         Error status.
     */
    @DeleteMapping("api/exercise/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") int id) {
        try {
            storedExerciseService.deleteStoredExercise(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
