package com.workoutbuilder.enterprise;

import com.workoutbuilder.enterprise.dto.Workout;
import com.workoutbuilder.enterprise.service.IWorkoutServiceStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnterpriseApplicationTests is a test class that checks the functionality related to creating exercises and workouts.
 */
@SpringBootTest
public class EnterpriseApplicationTests {

	@Autowired
	private IWorkoutServiceStub workoutService;

	@Test
	void contextLoads() {
	}

	/**
	 * Tests findById method checks for workout duration
	 */
	@Test
	void findWorkoutDurationById() {
		Workout workout = workoutService.findById(12);
		int workoutDuration = workout.getDuration();
		assertEquals(45, workoutDuration);
	}
}
