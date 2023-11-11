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
                let id = 0;
                data.forEach((d) => {
                  results.push({ id: id, text: d })
                  id++;
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

    addExercise();
});
