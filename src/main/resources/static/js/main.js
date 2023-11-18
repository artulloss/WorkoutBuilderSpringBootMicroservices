import { toast } from "./toast.js";

jQuery(($) => {
  const qs = document.querySelector.bind(document);
  const qsa = document.querySelectorAll.bind(document);
  const exerciseCount = () => qsa('.exercises .exercise')?.length || 0; // Get the number of exercises

  const addExercise = () => {
    const form = qs("form#logWorkout");
    const addExerciseBtn = form.querySelector("#addExercise");
    const addFormFields = (e) => {
      e && e.preventDefault();
      const template = form.querySelector("#exerciseFields");
      const clone = template.content.cloneNode(true);
      const exercises = form.querySelector(".exercises");

      // Set a unique ID for the new select element
      const select = clone.querySelector("select");
      select.id = "exerciseSelect" + exerciseCount();

      exercises.appendChild(clone);
      addSelect2ToExercisePicker("#" + select.id); // Initialize select2 for the new select element
    };
    addExerciseBtn && addExerciseBtn.addEventListener("click", addFormFields);
    addFormFields();
  };

  const removeExercise = () => {
    const form = qs("form#logWorkout");
    const removeExerciseBtn = form.querySelector("#removeExercise");
    const removeFormFields = (e) => {
      e && e.preventDefault();
      const exercises = form.querySelector(".exercises");
      if(exerciseCount() > 1) {
        exercises.querySelector(".exercise:last-of-type").remove();
      } else {
        toast.error("A workout requires at least one exercise!", 2000);
      }
    };
    removeExerciseBtn && removeExerciseBtn.addEventListener("click", removeFormFields);
  };

  // Delegate event handling for the select change
  $(document).on("change", "form#logWorkout select", function () {
    upStartRequiredFields(this);
  });

  const addSelect2ToExercisePicker = (selector) => {
    $(selector).select2({
      ajax: {
        url: "api/searchExerciseAutocomplete",
        dataType: "json",
        delay: "250",
        data: function (params) {
          return {
            name: params.term,
          };
        },
        processResults: function (data, params) {
          let results = [];
          data.forEach((d) => {
            results.push({ id: JSON.stringify(d), text: d.name });
          });
          return {
            results: results,
          };
        },
        cache: true,
      },
      minimumInputLength: 3,
    });
  };

  // Function to upStart required fields based on exercise type
  const upStartRequiredFields = (selectElement) => {
    const selectedExercise = JSON.parse(selectElement.value);
    console.log({ selectedExercise });

    const exercise = selectElement.closest(".exercise");
    console.log({ exercise });

    if(exercise) {
      const exerciseName = exercise.querySelector("#exerciseName");
      console.log({ exerciseName });
      exerciseName.innerText = selectedExercise.name;
    }

    const isCardio = selectedExercise.type.toLowerCase() === "cardio"; // Check if selected exercise is cardio
    const exerciseContainer = $(selectElement).closest(".exercise");

    exerciseContainer.find("#exerciseSets").prop("required", !isCardio);
    exerciseContainer.find("#exerciseReps").prop("required", !isCardio);
    exerciseContainer.find("#exerciseWeight").prop("required", !isCardio);
    exerciseContainer.find("#exerciseDuration").prop("required", isCardio);
  };

  const logWorkoutForm = (e) => {
    const form = qs("form#logWorkout");
    form.addEventListener("submit", (e) => {
      e.preventDefault(); // Prevent the default form submission

      if (!form.checkValidity()) {
        form.reportValidity(); // Trigger HTML validation
        return;
      }

      // Collect workout start
      const workoutStart = form.querySelector("#workoutStart").value;
      const workoutDuration = form.querySelector("#workoutDuration").value;

      const workoutDurationSum = [...form.querySelectorAll(".exercise")].reduce((sum, exerciseDiv) => {
        const duration = parseInt(exerciseDiv.querySelector('input[name="duration"]').value) || 0;
        return sum + duration;
      }, 0);

      if(workoutDuration < 1) {
        toast.error("Workout duration must be greater than 0!", 2000);
        return;
      }

      if(workoutDurationSum > workoutDuration) {
        toast.error("Workout duration is less than the sum of the duration of the exercises!", 2000);
        return;   
      }

      // Collect exercises
      const exercises = [];
      form.querySelectorAll(".exercise").forEach((exerciseDiv) => {
        const exercise = JSON.parse(
          exerciseDiv.querySelector('select[name="exercises"]').value
        );
        const storedExercise = {
          ...exercise,
          sets:
            parseInt(exerciseDiv.querySelector('input[name="sets"]').value) ||
            null,
          reps:
            parseInt(exerciseDiv.querySelector('input[name="reps"]').value) ||
            null,
          weight:
            parseInt(exerciseDiv.querySelector('input[name="weight"]').value) ||
            null,
          duration:
            parseInt(
              exerciseDiv.querySelector('input[name="duration"]').value
            ) || null,
        };
        exercises.push(storedExercise);
      });

      console.log({ exercises });



      // Create JSON object
      const workoutData = {
        start: workoutStart,
        duration: workoutDuration,
        name: String(
            document.querySelector('input[name="workoutName"]').value
        ),
        description: String(
            document.querySelector('textarea[name="workoutDescription"]').value
        ),
        exercises: exercises,
      };

      // Send AJAX request
      fetch("/api/workout", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(workoutData),
      })
        .then((response) => response.json())
        .then(async () => {
          form.reset();
          await toast.success("Workout logged successfully!", 2000);
          // Remove all exercises except the first one
          document.querySelectorAll('#exercises .exercise').forEach((e, i) => i && e.remove());
        })
        .catch(async (error) => {
          console.error("Error:", error);
          await toast.error("Error logging workout!", 2000);
        });
    });
  };

  addExercise();
  removeExercise();
  logWorkoutForm();
});

