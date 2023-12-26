$(document).ready(function (){
    const content = document.getElementById("content");
    const calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        height : "auto",
        initialView: 'dayGridMonth',
        selectable: true,
        select: function (info) {
            console.log(info)
        },
    });
    calendar.render();



});