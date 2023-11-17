import { toast } from "./toast.js";

// IIFE
(async function () {
    const calendarEl = document.getElementById('calendar');
    // FullCalendar with JSON feed at /api/workout
    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        height: 'auto',
        contentHeight: 'auto',
        aspectRatio: 1,
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth'
        },
        events: {
            url: '/api/workout',
            method: 'GET',
            failure: function() {
                toast.error('There was an error while fetching events!', 2000);
            },
            color: 'green',
            textColor: 'white'
        },
        timeZone: 'UTC',
    });
    calendar.render();
})();