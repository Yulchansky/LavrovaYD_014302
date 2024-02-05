google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(drawTransactions);
google.charts.setOnLoadCallback(drawCurrencyTransactions);
google.charts.setOnLoadCallback(drawCurrency);
google.charts.setOnLoadCallback(drawCurrencyCom);

function drawTransactions() {
    let res = [['Наименование', 'Количество транзакций']];

    for (let i = 0; i < stringTransactions.length; i++) {
        res.push([stringTransactions[i], intTransactions[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    let options = {
        title: 'Топ-5 банкоматов по транзакциям',
        hAxis: {title: 'Наименование'},
        vAxis: {title: 'Количество транзакций'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('drawTransactions'));
    chart.draw(data, options);
}

function drawCurrencyTransactions() {
    let res = [['Валюта', 'Количество транзакций']];

    for (let i = 0; i < stringCurrency.length; i++) {
        res.push([stringCurrency[i], intCurrencyTransactions[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Количество транзакций по валютам',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawCurrencyTransactions'));
    chart.draw(data, options);
}

function drawCurrency() {
    let res = [['Валюта', 'Количество']];

    for (let i = 0; i < stringCurrency.length; i++) {
        res.push([stringCurrency[i], intCurrency[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Количество снятых по валютам',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawCurrency'));
    chart.draw(data, options);
}

function drawCurrencyCom() {
    let res = [['Валюта', 'Количество']];

    for (let i = 0; i < stringCurrency.length; i++) {
        res.push([stringCurrency[i], intCurrencyCom[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Объем комиссии по валютам',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawCurrencyCom'));
    chart.draw(data, options);
}