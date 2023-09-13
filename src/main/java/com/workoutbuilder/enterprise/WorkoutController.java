package com.workoutbuilder.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkoutController {

    /**
     * Handle the root (/) endpoint and return the start (index) page.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
