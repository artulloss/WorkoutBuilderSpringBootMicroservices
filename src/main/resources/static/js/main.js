(() => {

    const qs = document.querySelector.bind(document);

    const createExercise = () => {
        const form = qs('form#createExercise');
        form.addEventListener('submit', async (e) => {
            debugger;
            e.preventDefault();
            const formData = new FormData(form);
            const response = await fetch('/api/exercise', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(Object.fromEntries(formData)),
            });
            if (response.ok) {
                window.location.reload(); // This could be done with DOM manipulation as well - for now this is easier
            }
        });
    }

    createExercise();

})();