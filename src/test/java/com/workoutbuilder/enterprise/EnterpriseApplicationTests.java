package com.workoutbuilder.enterprise;

import com.workoutbuilder.enterprise.dao.ExerciseDAO;
import com.workoutbuilder.enterprise.dao.IExerciseDAO;
import com.workoutbuilder.enterprise.dao.IWorkoutDAO;
import com.workoutbuilder.enterprise.dto.Exercise;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EnterpriseApplicationTests {

	@MockBean
	private IExerciseDAO exerciseDAO;

	@MockBean
	private IWorkoutDAO workoutDAO;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetExercise() {

	}

}
