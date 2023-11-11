jQuery(($) => {
    const qs = document.querySelector.bind(document);

    let exerciseCount = 0; // Counter to keep track of the number of exercises added

    const addExercise = () => {
        const form = qs('form#logWorkout');
        const addExerciseBtn = form.querySelector('#addExercise');
        const addFormFields = (e) => {
            e && e.preventDefault();
            exerciseCount++; // Increment the counter
            const template = form.querySelector('#exerciseFields');
            const clone = template.content.cloneNode(true);
            const exercises = form.querySelector('.exercises');

            // Set a unique ID for the new select element
            const select = clone.querySelector('select');
            select.id = 'exerciseSelect' + exerciseCount;

            exercises.appendChild(clone);
            addSelect2ToExercisePicker('#' + select.id); // Initialize select2 for the new select element
        };
        addExerciseBtn && addExerciseBtn.addEventListener('click', addFormFields);
        addFormFields();
    }

    // Delegate event handling for the select change
    $(document).on('change', 'form#logWorkout select', function() {
        console.log('hello?', this);
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
                  results.push({ id: JSON.stringify(d), text: d.name })
                })
                return {
                  results: results,
                };
              },
              cache: true,
            },
            minimumInputLength: 3,
        });
    }

    // Function to update required fields based on exercise type
    const updateRequiredFields = (selectElement) => {
        debugger;
        const selectedExercise = JSON.parse(selectElement.value);
        console.log({selectedExercise});

        const isCardio = selectedExercise.type.toLowerCase() === 'cardio'; // Check if selected exercise is cardio
        const exerciseContainer = $(selectElement).closest('.exercise');

        exerciseContainer.find('#exerciseSets').prop('required', !isCardio);
        exerciseContainer.find('#exerciseReps').prop('required', !isCardio);
        exerciseContainer.find('#exerciseWeight').prop('required', !isCardio);
        exerciseContainer.find('#exerciseDuration').prop('required', isCardio);
    }

    addExercise();
});
