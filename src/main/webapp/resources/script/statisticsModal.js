var fowlCountSpan = document.getElementById('fowlCount');
var fowlSpeciesCountSpan = document.getElementById('fowlSpeciesCount');
var randomFowlSpan = document.getElementById('randomFowl');

function getFowlCount() {
    var numberArray = [];
    var body = document.getElementsByTagName('tbody')[0];
    var rows = body.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var value = row.getElementsByTagName('td')[3].innerHTML;
        numberArray.push(value);
    }
    var count = 0;
    while (numberArray.length !== 0) {
        count += Number(numberArray.pop());
        console.log('count: ' + count);
    }
    return count;
}

function getRandomFowl() {
    var numberArray = [];
    var body = document.getElementsByTagName('tbody')[0];
    var rows = body.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var value = row.getElementsByTagName('td')[2].innerHTML;
        numberArray.push(value);
    }

    return numberArray[Math.floor(Math.random() * numberArray.length)];
}

function getFowlSpeciesCount() {
    var body = document.getElementsByTagName('tbody')[0];
    var rows = body.getElementsByTagName('tr');

    return rows.length;
}

function setupStatistics() {
    fowlCountSpan.innerHTML = getFowlCount();
    fowlSpeciesCountSpan.innerText = getFowlSpeciesCount();
    randomFowlSpan.innerHTML = getRandomFowl();
}
