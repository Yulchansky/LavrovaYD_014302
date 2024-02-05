let currency = document.getElementById('currency');
let BYN = document.getElementById('BYN');
let USD = document.getElementById('USD');
let EUR = document.getElementById('EUR');
let RUB = document.getElementById('RUB');

BYN.style.display = 'block';
USD.style.display = 'none';
EUR.style.display = 'none';
RUB.style.display = 'none';

currency.addEventListener('change', function () {
    switch (currency.selectedIndex) {
        case 0: {
            BYN.style.display = 'block';
            USD.style.display = 'none';
            EUR.style.display = 'none';
            RUB.style.display = 'none';
            break;
        }
        case 1: {
            BYN.style.display = 'none';
            USD.style.display = 'block';
            EUR.style.display = 'none';
            RUB.style.display = 'none';
            break;
        }
        case 2: {
            BYN.style.display = 'none';
            USD.style.display = 'none';
            EUR.style.display = 'block';
            RUB.style.display = 'none';
            break;
        }
        case 3: {
            BYN.style.display = 'none';
            USD.style.display = 'none';
            EUR.style.display = 'none';
            RUB.style.display = 'block';
            break;
        }
    }
});

let BYNto = document.getElementById('BYNto');
let BYNtoUSD = document.getElementById('BYNtoUSD');
let BYNtoEUR = document.getElementById('BYNtoEUR');
let BYNtoRUB = document.getElementById('BYNtoRUB');

BYNtoUSD.style.display = 'block';
BYNtoEUR.style.display = 'none';
BYNtoRUB.style.display = 'none';

BYNto.addEventListener('change', function () {
    switch (BYNto.selectedIndex) {
        case 0: {
            BYNtoUSD.style.display = 'block';
            BYNtoEUR.style.display = 'none';
            BYNtoRUB.style.display = 'none';
            break;
        }
        case 1: {
            BYNtoUSD.style.display = 'none';
            BYNtoEUR.style.display = 'block';
            BYNtoRUB.style.display = 'none';
            break;
        }
        case 2: {
            BYNtoUSD.style.display = 'none';
            BYNtoEUR.style.display = 'none';
            BYNtoRUB.style.display = 'block';
            break;
        }

    }
});

let USDto = document.getElementById('USDto');
let USDtoBYN = document.getElementById('USDtoBYN');
let USDtoEUR = document.getElementById('USDtoEUR');
let USDtoRUB = document.getElementById('USDtoRUB');

USDtoBYN.style.display = 'block';
USDtoEUR.style.display = 'none';
USDtoRUB.style.display = 'none';

USDto.addEventListener('change', function () {
    switch (USDto.selectedIndex) {
        case 0: {
            USDtoBYN.style.display = 'block';
            USDtoEUR.style.display = 'none';
            USDtoRUB.style.display = 'none';
            break;
        }
        case 1: {
            USDtoBYN.style.display = 'none';
            USDtoEUR.style.display = 'block';
            USDtoRUB.style.display = 'none';
            break;
        }
        case 2: {
            USDtoBYN.style.display = 'none';
            USDtoEUR.style.display = 'none';
            USDtoRUB.style.display = 'block';
            break;
        }

    }
});

let EURto = document.getElementById('EURto');
let EURtoBYN = document.getElementById('EURtoBYN');
let EURtoUSD = document.getElementById('EURtoUSD');
let EURtoRUB = document.getElementById('EURtoRUB');

EURtoBYN.style.display = 'block';
EURtoUSD.style.display = 'none';
EURtoRUB.style.display = 'none';

EURto.addEventListener('change', function () {
    switch (EURto.selectedIndex) {
        case 0: {
            EURtoBYN.style.display = 'block';
            EURtoUSD.style.display = 'none';
            EURtoRUB.style.display = 'none';
            break;
        }
        case 1: {
            EURtoBYN.style.display = 'none';
            EURtoUSD.style.display = 'block';
            EURtoRUB.style.display = 'none';
            break;
        }
        case 2: {
            EURtoBYN.style.display = 'none';
            EURtoUSD.style.display = 'none';
            EURtoRUB.style.display = 'block';
            break;
        }

    }
});

let RUBto = document.getElementById('RUBto');
let RUBtoBYN = document.getElementById('RUBtoBYN');
let RUBtoUSD = document.getElementById('RUBtoUSD');
let RUBtoEUR = document.getElementById('RUBtoEUR');

RUBtoBYN.style.display = 'block';
RUBtoUSD.style.display = 'none';
RUBtoEUR.style.display = 'none';

RUBto.addEventListener('change', function () {
    switch (RUBto.selectedIndex) {
        case 0: {
            RUBtoBYN.style.display = 'block';
            RUBtoUSD.style.display = 'none';
            RUBtoEUR.style.display = 'none';
            break;
        }
        case 1: {
            RUBtoBYN.style.display = 'none';
            RUBtoUSD.style.display = 'block';
            RUBtoEUR.style.display = 'none';
            break;
        }
        case 2: {
            RUBtoBYN.style.display = 'none';
            RUBtoUSD.style.display = 'none';
            RUBtoEUR.style.display = 'block';
            break;
        }

    }
});