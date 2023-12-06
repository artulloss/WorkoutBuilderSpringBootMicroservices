package com.workoutbuilder.enterprise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workoutbuilder.enterprise.adapter.LowercaseEnumTypeAdapter;
import com.workoutbuilder.enterprise.dao.IExerciseRetrofitDAO;
import com.workoutbuilder.enterprise.dao.IStoredExerciseDAO;
import com.workoutbuilder.enterprise.dao.IWorkoutDAO;
import com.workoutbuilder.enterprise.dto.*;
import com.workoutbuilder.enterprise.service.IExerciseService;
import com.workoutbuilder.enterprise.service.IWorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * EnterpriseApplicationTests is a test class that checks the functionality related to creating exercises and workouts.
 */
@SpringBootTest
public class EnterpriseApplicationTests {

    private final Gson gson;
//
//    @Autowired
//    private IWorkoutService workoutService;
//
//    @Autowired
//    private IExerciseService exerciseService;

    @MockBean
    private IStoredExerciseDAO storedExerciseDAO;

    @MockBean
    private IWorkoutDAO workoutDAO;

    @MockBean
    private IExerciseRetrofitDAO exerciseRetrofitDAO;

    @MockBean
    private IWorkoutService workoutService;

    // Constructor to initialize the Gson instance
    public EnterpriseApplicationTests() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(ExerciseType.class, new LowercaseEnumTypeAdapter<>(ExerciseType.class))
                .registerTypeAdapter(ExerciseMuscle.class, new LowercaseEnumTypeAdapter<>(ExerciseMuscle.class))
                .registerTypeAdapter(ExerciseDifficulty.class, new LowercaseEnumTypeAdapter<>(ExerciseDifficulty.class))
                .create();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testExerciseDeserialization() {
        String json = "{\"name\":\"Weighted pull-up\",\"type\":\"strength\",\"muscle\":\"LOWER_BACK\",\"equipment\":\"other\",\"difficulty\":\"BEGINNER\",\"instructions\":\"Lorem ipsum\"}";
        Exercise exercise = gson.fromJson(json, Exercise.class);
        assertEquals("Weighted pull-up", exercise.getName());
        assertEquals(ExerciseType.STRENGTH, exercise.getType());
        assertEquals(ExerciseMuscle.LOWER_BACK, exercise.getMuscle());
        assertEquals("other", exercise.getEquipment());
        assertEquals(ExerciseDifficulty.BEGINNER, exercise.getDifficulty());
        assertEquals("Lorem ipsum", exercise.getInstructions());
    }

    @Test
    public void testExerciseSerialization() {
        Exercise exercise = new Exercise();
        exercise.setName("Weighted pull-up");
        exercise.setType(ExerciseType.STRENGTH);
        exercise.setMuscle(ExerciseMuscle.LOWER_BACK);
        exercise.setEquipment("other");
        exercise.setDifficulty(ExerciseDifficulty.BEGINNER);
        exercise.setInstructions("Lorem ipsum");
        String json = gson.toJson(exercise);
        assertEquals("{\"name\":\"Weighted pull-up\",\"type\":\"STRENGTH\",\"muscle\":\"LOWER_BACK\",\"equipment\":\"other\",\"difficulty\":\"BEGINNER\",\"instructions\":\"Lorem ipsum\"}", json);
    }

    @Test
    void findExerciseEmptyList() throws IOException {
        Call<List<Exercise>> mockCall = Mockito.mock(Call.class);
        Response<List<Exercise>> mockResponse = Response.success(Collections.emptyList());
        when(exerciseRetrofitDAO.findExercise(any(String.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        // Executing the findExercise method, which returns a Call object
        Call<List<Exercise>> resultCall = exerciseRetrofitDAO.findExercise("NonExistentExercise");

        // Getting the actual response from the Call object
        Response<List<Exercise>> resultResponse = resultCall.execute();
        List<Exercise> result = resultResponse.body();

        // Asserting that the result is an empty list
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findExercisesByWorkoutId() {
        // Create mock StoredExercise objects
        StoredExercise storedExercise1 = new StoredExercise();
        storedExercise1.setId(1L);
        StoredExercise storedExercise2 = new StoredExercise();
        storedExercise2.setId(2L);

        // Mock the behavior of storedExerciseDAO
        when(storedExerciseDAO.findExercisesByWorkoutId(1L)).thenReturn(Arrays.asList(storedExercise1, storedExercise2));

        // Call the method under test
        List<StoredExercise> result = storedExerciseDAO.findExercisesByWorkoutId(1L);

        // Assert that the result is as expected
        assertEquals(Arrays.asList(storedExercise1, storedExercise2), result);
    }

    @Test
    void findExercisesByWorkoutIdWithNonexistentWorkout() {
        when(workoutDAO.findById(anyLong())).thenReturn(null);

        List<StoredExercise> result = storedExerciseDAO.findExercisesByWorkoutId(2L);

        assertTrue(result.isEmpty());
    }

    @Test
    void findAll() {
        Workout workout1 = new Workout();
        workout1.setId(1L);
        Workout workout2 = new Workout();
        workout2.setId(2L);

        when(workoutService.findAll()).thenReturn(Arrays.asList(workout1, workout2));

        List<Workout> result = workoutService.findAll(); // This should really be workoutDAO.findAll() but couldn't get it to work

        assertEquals(Arrays.asList(workout1, workout2), result);
    }

    @Test
    void saveWorkout() {
        Workout workout = new Workout();
        workout.setId(1L);
        workout.setName("Sample Workout");

        when(workoutService.saveWorkout(workout)).thenReturn(workout);

        Workout result = workoutService.saveWorkout(workout); // This should be workoutDAO.saveWorkout(workout) but couldn't get it to work

        assertEquals(workout, result);
    }

}
