package com.workoutbuilder.enterprise;

import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.WorkoutDTO;
import com.workoutbuilder.enterprise.entity.Exercise;
import com.workoutbuilder.enterprise.entity.User;
import com.workoutbuilder.enterprise.service.IExerciseService;
import com.workoutbuilder.enterprise.service.IWorkoutService;
import com.workoutbuilder.enterprise.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;


/**
 * EnterpriseApplicationTests is a test class that checks the functionality related to creating exercises
 * and ensuring they are persisted properly to the database for logged-in users.
 */
@SpringBootTest
public class EnterpriseApplicationTests {

	@Autowired
	private IWorkoutService workoutService;

	@Autowired
	private IExerciseService exerciseService;

	@Autowired
	private UserService userService;

	private Exercise exercise;

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
	/**
	 * Test case to ensure that a logged-in user can create an exercise.
	 * It checks the exercise creation flow including saving it to the database
	 * and ensuring it can be fetched back for other users.
	 */
	@Test
	@WithMockUser(username = "mockuser@testing.com")
	void loggedInUserCanCreateAnExerciseThatCanBeUsedToLogWorkouts() {
		givenThereIsAUserLoggedIn();
		whenTheUserCreatesAnExercise();
		thenTheExerciseIsSavedToTheDatabaseAndCanBeAccessedByOtherUsers();
		// Cleanup (to maintain state and not affect other tests)
		exerciseService.deleteExercise(exercise);
	}

	/**
	 * Helper method to simulate a scenario where a user is logged in.
	 * It sets up the Exercise object with user details and other required attributes.
	 */
	private void givenThereIsAUserLoggedIn() {
		User currentUser = userService.findByEmail("mockuser@testing.com");
		assertNotNull(currentUser, "Current user should not be null.");

		exercise = new Exercise();
		exercise.setName("Bench Press");
		exercise.setExerciseType(ExerciseType.STRENGTH_TRAINING);
		exercise.setDescription("Lay facing up on a bench and press a barbell up and down.");
		exercise.setUser(currentUser);
	}

	/**
	 * Helper method to simulate the action of a user creating an exercise.
	 * It saves the exercise to the database and checks if it's saved correctly.
	 */
	private void whenTheUserCreatesAnExercise() {
		exercise = exerciseService.saveExercise(exercise);
		assertNotNull(exercise, "Saved exercise should not be null.");
	}

	/**
	 * Helper method to verify that the created exercise is persisted in the database.
	 * It fetches the exercise from the database and ensures that its details match the saved exercise.
	 */
	private void thenTheExerciseIsSavedToTheDatabaseAndCanBeAccessedByOtherUsers() {
		Exercise fetchedExercise = exerciseService.findById(exercise.getId());
		assertNotNull(fetchedExercise, "Fetched exercise should not be null.");
		assertEquals(exercise.getName(), fetchedExercise.getName(), "Fetched exercise name should match the saved one.");
	}

	@Test
	@WithMockUser(username = "mockuser@testing.com")
	void loggedInUserCanCreateAnExerciseWithNoDescription() {
		givenThereIsAUserLoggedIn();
		Exception exception = whenTheUserCreatesAnExerciseWithNoDescription();
		thenThereIsAnErrorMessageSpecifyingDescriptionCannotBeNull(exception);
	}

	private Exception whenTheUserCreatesAnExerciseWithNoDescription() {
		exercise.setDescription(null);
		try {
			exercise = exerciseService.saveExercise(exercise);
			return null;
		} catch (Exception e) {
			return e;
		}
	}

	private void thenThereIsAnErrorMessageSpecifyingDescriptionCannotBeNull(Exception exception) {
		assertNotNull(exception, "Exception should not be null when trying to save an exercise with no description.");
		assertTrue(exception.getMessage().contains("Description cannot be null"), "Error message should specify that the description cannot be null.");
	}
}
