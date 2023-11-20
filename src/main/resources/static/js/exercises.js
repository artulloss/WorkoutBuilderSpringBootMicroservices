import { toast } from "./toast.js";

document.getElementById('exercise-search').addEventListener('input', () => {
    const inputValue = document.getElementById('exercise-search').value;

    if (inputValue.length > 3) { // prevents query from running unless 3 characters have been entered
        fetch(`api/searchExerciseAutocomplete?name=${inputValue}`)
            .then(response => response.json())
            .then(data => {
                const exerciseContainer = document.getElementById('exerciseContainer');
                exerciseContainer.innerHTML = '';

                if (data.length === 0) { // if nothing is found, display fallback message
                    const noExercisesMessage = document.createElement('p');
                    noExercisesMessage.classList.add('text-deep-red', 'flex', 'justify-center', 'align-middle', 'pt-10')
                    noExercisesMessage.textContent = 'No exercises found!';
                    exerciseContainer.appendChild(noExercisesMessage);
                } else {
                    const exerciseList = document.createElement('div');
                    exerciseList.classList.add('flex', 'space-y-4', 'flex-col');

                    data.forEach(exercise => {
                        exerciseList.innerHTML += createExerciseListItem(exercise);
                    });

                    exerciseContainer.appendChild(exerciseList);
                }
            })
            .catch(error => {
                toast.error("Error retrieving exercises!", 2000);
            }, 500); // set the delay in ms
    }
});

const createExerciseListItem = (exercise) => { // template for list of exercises

    const {name, difficulty, muscle} = exercise;

    return '' +
        '    <div class="w-full p-4 ps-10 border border-gray-300 rounded-lg flex flex-row space-x-4 items-center">\n' +
        '        <p class="font-medium ">' + name +'</p>\n' +
        '        <p class="text-deep-red font-light ">' + muscle +'</p>\n' +
        '        <p class="text-gray-500 font-light ">' + difficulty +'</p>\n' +
        '        <div class="flex-grow "></div>\n' +
        '        <a class="flex justify-end link text-deep-red pr-4" href="/exercise/' + name +'">View Details ></a>\n' +
        '    </div>';

}