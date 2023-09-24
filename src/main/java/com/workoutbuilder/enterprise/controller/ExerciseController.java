package com.workoutbuilder.enterprise.controller;

import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.service.IExerciseService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    IExerciseService exerciseService;

    @GetMapping("/exercise")
    @ResponseBody
    public ResponseEntity<List<Exercise>> exercise() {
        return ResponseEntity.ok(exerciseService.fetchAll());
    }

    @GetMapping("/exercise/{id}")
    @ResponseBody
    public ResponseEntity<Exercise> exerciseById(@PathVariable("id") int id) {
        return ResponseEntity.ok(exerciseService.fetchById(id));
    }

    @PostMapping("/exercise")
    public ResponseEntity<Exercise> saveExercise(Exercise exercise) {
        try {
            val newExercise = exerciseService.save(exercise);
            return ResponseEntity.ok(newExercise);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/exercise/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable("id") int id) throws Exception {
        exerciseService.delete(exerciseService.fetchById(id));
        return ResponseEntity.ok().build();
    }

}
