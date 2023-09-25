package com.workoutbuilder.enterprise;

import com.workoutbuilder.enterprise.dto.Workout;
import com.workoutbuilder.enterprise.service.IWorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class EnterpriseApplicationTests {

	@Autowired
	private IWorkoutService workoutService;

	@Test
	void contextLoads() {
	}

	/**
	 * Tests fetchById method checks for workout type
	 */
	@Test
	void fetchWorkoutTypeById() {
		Workout workout = workoutService.fetchById(12);
		String workoutType = workout.getWorkoutType();
		assertEquals("Cardio", workoutType);
	}

}
