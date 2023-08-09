function initialize() {
    let allseats = document.getElementsByClassName('seat');
    for (let i = 0; i < allseats.length; i++) {
        let seat = allseats[i];
        seat.addEventListener('click', function () {
            this.style.backgroundColor = 'green';
            if (this.hasAttribute('seat-id')) {
                this.style.backgroundColor = 'inherit';
                this.removeAttribute('seat-id');
            }
            else {
                this.setAttribute('seat-id', this.innerText);
            }
            updatePrice();
        });
    }
}
function updatePrice() {
    let seatnos = [];
    let count = 0;
    let allseats = document.getElementsByClassName('seat');
    for (let j = 0; j < allseats.length; j++) {
        let curr = allseats[j];
        if (curr.hasAttribute('seat-id')) {
            seatnos.push(curr.innerText);
            count++;
        }
    }
    let dseats = document.getElementById('seatsYouSelected');
    let pseats = document.getElementById('priceForYourSelection');
    dseats.innerText = "";
    dseats.value = seatnos.toString();
    pseats.vlaue = count;
}