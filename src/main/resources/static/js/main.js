jQuery(($) => {

    const qs = document.querySelector.bind(document);

//    const createExercise = () => {
//        const form = qs('form#createExercise');
//        form.addEventListener('submit', async (e) => {
//            debugger;
//            e.preventDefault();
//            const formData = new FormData(form);
//            const response = await fetch('/api/exercise', {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json',
//                },
//                body: JSON.stringify(Object.fromEntries(formData)),
//            });
//            if (response.ok) {
//                window.location.reload(); // This could be done with DOM manipulation as well - for now this is easier
//            }
//        });
//    }

    const addExercise = () => {
        const form = qs('form#logWorkout');
        const addExerciseBtn = form.querySelector('#addExercise');
        const addFormFields = (e) => {
          e && e.preventDefault();
          const template = form.querySelector('#exerciseFields');
          const clone = template.content.cloneNode(true);
          const exercises = form.querySelector('.exercises');
          const select = clone.querySelector('select');
          exercises.appendChild(clone);
          addSelect2ToExercisePickers(select);
        };
        addExerciseBtn && addExerciseBtn.addEventListener('click', addFormFields);
        addFormFields();
    }

    const addSelect2ToExercisePickers = () => {
        $("form#logWorkout select").select2({
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