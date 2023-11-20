import { toast } from "./toast.js";

document.getElementById('exercise-search').addEventListener('input', () => {
    const inputValue = document.getElementById('exercise-search').value;

    if (inputValue.length > 3) {
        fetch(`api/searchExerciseAutocomplete?name=${inputValue}`)
            .then(response => response.json())
            .then(data => {
                const exerciseList = document.createElement('div');
                exerciseList.classList.add('flex', 'space-y-4', 'flex-col')
                data.forEach(exercise => {
                    exerciseList.innerHTML += createListItem(exercise);
                });

                const exerciseContainer = document.getElementById('exerciseContainer');
                exerciseContainer.innerHTML = '';
                exerciseContainer.appendChild(exerciseList);
            })
            .catch(error => {
                toast.error("Error retrieving exercises!", 2000);
            });
    }
});

const createListItem = (exercise) => {

    const {name, difficulty} = exercise;

    return '' +
        '    <div class="w-full p-4 ps-10 border border-gray-300 rounded-lg flex flex-row space-x-4">\n' +
        '        <p class="font-medium ">' + name +'</p>\n' +
        '        <p class="text-gray-500 font-light ">' + difficulty +'</p>\n' +
        '        <div class="flex-grow "></div>\n' +
        '        <a class="flex justify-end link text-deep-red" href="/exercise/' + name +'">View Details ></a>\n' +
        '    </div>';

}