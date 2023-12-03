package com.workoutbuilder.enterprise;

import com.google.gson.Gson;
import com.workoutbuilder.enterprise.dao.ExerciseDAO;
import com.workoutbuilder.enterprise.dao.StoredExerciseDAO;
import com.workoutbuilder.enterprise.dao.WorkoutDAO;
import com.workoutbuilder.enterprise.dao.WorkoutRepository;
import com.workoutbuilder.enterprise.dto.Exercise;
import com.workoutbuilder.enterprise.dto.ExerciseType;
import com.workoutbuilder.enterprise.dto.StoredExercise;
import com.workoutbuilder.enterprise.dto.Workout;
import com.workoutbuilder.enterprise.service.IWorkoutServiceStub;
import com.workoutbuilder.enterprise.service.StoredExerciseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * EnterpriseApplicationTests is a test class that checks the functionality related to creating exercises and workouts.
 */
@SpringBootTest
public class EnterpriseApplicationTests {

	@Autowired
	private IWorkoutServiceStub workoutService;
	private ExerciseDAO exerciseRetrofitDAO;
	private WorkoutDAO workoutDAO;
	private WorkoutRepository workoutRepository;
	private StoredExerciseDAO storedExerciseDAO;

	@Autowired
	private Gson gson;

	@Test
	void contextLoads() {
	}

	/**
	 * Tests findById method checks for workout duration
	 */
	@Test
	void findWorkoutDurationById() {
//		Workout workout = workoutService.findById(12);
//		int workoutDuration = workout.getDuration();
//		assertEquals(45, workoutDuration);
	}

	@Test
	public void testExerciseDeserialization() {
		String json = "{\"name\":\"Weighted pull-up\",\"type\":\"strength\",\"muscle\":\"BACK\",\"equipment\":\"other\",\"difficulty\":\"BEGINNER\",\"instructions\":\"Lorem ipsum\"}";
		Exercise exercise = gson.fromJson(json, Exercise.class);

		assertEquals("Weighted pull-up", exercise.getName());
		assertEquals(ExerciseType.STRENGTH, exercise.getType());
		// Add assertions for other fields
	}

	@Test
	void findExerciseEmptyList() throws IOException {
		Call<List<Exercise>> mockCall = Mockito.mock(Call.class);
		Response<List<Exercise>> mockResponse = Response.success(Collections.emptyList());

		when(exerciseRetrofitDAO.findExercise(any(String.class))).thenReturn((Exercise) mockCall);
		when(mockCall.execute()).thenReturn(mockResponse);

		Exercise result = exerciseRetrofitDAO.findExercise("NonExistentExercise");

		assertEquals(null, result);
	}

	@Test
	void findExercisesByWorkoutId() {
		Workout mockWorkout = new Workout();
		mockWorkout.setId(1L);
		StoredExercise storedExercise1 = new StoredExercise();
		storedExercise1.setId(1L);
		StoredExercise storedExercise2 = new StoredExercise();
		storedExercise2.setId(2L);
		mockWorkout.setExercises(Arrays.asList(storedExercise1, storedExercise2));

		when(workoutDAO.findById(anyLong())).thenReturn(mockWorkout);

		List<StoredExercise> result = storedExerciseDAO.findExercisesByWorkoutId(1L);

		assertEquals(Arrays.asList(storedExercise1, storedExercise2), result);
	}

	@Test
	void findExercisesByWorkoutIdWithNonexistentWorkout() {
		when(workoutDAO.findById(anyLong())).thenReturn(null);

		List<StoredExercise> result = storedExerciseDAO.findExercisesByWorkoutId(2L);

		assertEquals(null, result);
	}

	@Test
	void findAll() {
		Workout workout1 = new Workout();
		workout1.setId(1L);
		Workout workout2 = new Workout();
		workout2.setId(2L);

		when(workoutRepository.findAll()).thenReturn(Arrays.asList(workout1, workout2));

		List<Workout> result = workoutDAO.findAll();

		assertEquals(Arrays.asList(workout1, workout2), result);
	}

	@Test
	void saveWorkout() {
		Workout workout = new Workout();
		workout.setId(1L);
		workout.setName("Sample Workout");

		when(workoutRepository.save(workout)).thenReturn(workout);

		Workout result = workoutDAO.saveWorkout(workout);

		assertEquals(workout, result);

		verify(workoutRepository).save(workout);
	}

	@Test
	void deleteWorkout() {
		long workoutIdToDelete = 1L;

		workoutDAO.deleteWorkout(workoutIdToDelete);

		verify(workoutRepository).deleteById(workoutIdToDelete);
	}

}
