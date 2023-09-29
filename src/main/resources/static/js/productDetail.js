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

/*상품 선택*/
var resultList = [];        // 선택한 상품 리스트 ( [상품명, 사이즈, 색상, 개수, 총가격] 배열이 들어있음)

function addProduct() {
        var updateTotalPrice = 0;

        var productName = document.getElementById('pn').innerText;   // 상품명
        var selectColorObject = document.getElementById('selectColor');
        var selectSizeObject = document.getElementById('selectSize');
        var selectColor = selectColorObject.options[selectColorObject.selectedIndex].value;  // 선택 색상
        var selectSize = selectSizeObject.options[selectSizeObject.selectedIndex].value;    // 선택 사이즈
        var price = parseInt(document.getElementById('price').innerText, 10);         // 가격
        var productCount = parseInt(document.getElementById('counting').innerText, 10);   // 선택 수량
        var isInclude = false;

        if(resultList.length === 0){
            var newP = [productName, selectSize, selectColor, productCount, price * productCount];
            resultList.push(newP);
        }else{
            for(var i=0; i<resultList.length; i++) {
                if(resultList[i][1] === selectSize && resultList[i][2] === selectColor){
                    // 이미 포함된 상품이면
                    resultList[i][3] += productCount;
                    var newPrice = price * productCount;
                    resultList[i][4] += newPrice;
                    isInclude = true;
                }
            }
            // 처음 선택하는 상품이면
            if(isInclude === false){
                var newP = [productName, selectSize, selectColor, productCount, price * productCount];
                resultList.push(newP);
            }
        }

        var selectList = document.getElementById('selectList');
        var result = "";
        for(var i=0; i<resultList.length; i++) {
            var newLine = resultList[i][1] + '사이즈 ' + resultList[i][2] + '  '
            + resultList[i][3] + '개 ' + resultList[i][4] + '원' + '\n';
            result += newLine;
        }

        selectList.innerText = result;
}

function saveBasket() {

    var productName = document.getElementById('selectSize');
    var selectColorObject = document.getElementById('selectColor');
    var selectSizeObject = document.getElementById('selectSize');

    var color = selectColorObject.options[selectColorObject.selectedIndex].value;
    var size = selectSizeObject.options[selectSizeObject.selectedIndex].value;

    console.log(color, size);
}