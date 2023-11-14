jQuery(($) => {
  const qs = document.querySelector.bind(document);

  let exerciseCount = 0; // Counter to keep track of the number of exercises added

  const addExercise = () => {
    const form = qs("form#logWorkout");
    const addExerciseBtn = form.querySelector("#addExercise");
    const addFormFields = (e) => {
      e && e.preventDefault();
      exerciseCount++; // Increment the counter
      const template = form.querySelector("#exerciseFields");
      const clone = template.content.cloneNode(true);
      const exercises = form.querySelector(".exercises");

      // Set a unique ID for the new select element
      const select = clone.querySelector("select");
      select.id = "exerciseSelect" + exerciseCount;

      exercises.appendChild(clone);
      addSelect2ToExercisePicker("#" + select.id); // Initialize select2 for the new select element
    };
    addExerciseBtn && addExerciseBtn.addEventListener("click", addFormFields);
    addFormFields();
  };

  // Delegate event handling for the select change
  $(document).on("change", "form#logWorkout select", function () {
    console.log("hello?", this);
    updateRequiredFields(this);
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

  // Function to update required fields based on exercise type
  const updateRequiredFields = (selectElement) => {
    const selectedExercise = JSON.parse(selectElement.value);
    console.log({ selectedExercise });

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

      // Collect workout date
      const workoutDate = form.querySelector("#workoutDate").value;

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
        date: workoutDate,
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
        .then((data) => {
          console.log("Success:", data);
          // Handle success (e.g., show a success message, redirect, etc.)
        })
        .catch((error) => {
          console.error("Error:", error);
          // Handle errors here (e.g., show error message)
        });
    });
  };

  addExercise();
  logWorkoutForm();
});
