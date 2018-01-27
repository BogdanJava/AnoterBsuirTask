var dateSpan = document.getElementById('currentDate');

function setTime() {
    var currDate = new Date();
    var month = Number(currDate.getMonth()) + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var seconds = Number(currDate.getSeconds());
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    dateSpan.innerHTML = currDate.getDate() + "." +
        month + "." + currDate.getFullYear() + " / " + currDate.getHours() + ":" + currDate.getMinutes() + ":"
        + seconds;
}

setInterval(setTime, 1000);

