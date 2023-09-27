/*수량 선택*/
function counting(type) {
    var countElement = document.getElementById("countResult");
    var count = countElement.innerText;

    if(type === 'plus'){
        count = parseInt(count) + 1;
    }else if(type === 'minus'){
        if(count !== '1'){
            count = parseInt(count) - 1;
        }
    }
    countElement.innerText = count;
}

function saveBasket() {
    var selectColorObject = document.getElementById('selectColor');
    var selectSizeObject = document.getElementById('selectSize');

    var productName = document.getElementById('selectSize');
    var color = selectColorObject.options[selectColorObject.selectedIndex].value;
    var size = selectSizeObject.options[selectSizeObject.selectedIndex].value;

    console.log(color, size);
}