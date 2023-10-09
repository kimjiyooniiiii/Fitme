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
                            // 식별기준 '사이즈,색상'

function addProduct() {
        const productName = document.getElementById('pn').innerText;   // 상품명
        const selectColorObject = document.getElementById('selectColor');
        const selectSizeObject = document.getElementById('selectSize');
        const selectColor = selectColorObject.options[selectColorObject.selectedIndex].value;  // 선택 색상
        const selectSize = selectSizeObject.options[selectSizeObject.selectedIndex].value;    // 선택 사이즈
        const price = parseInt(document.getElementById('price').innerText, 10);         // 가격
        const productCount = parseInt(document.getElementById('countResult').innerText, 10);   // 선택 수량
        let isInclude = false;

        if(resultList.length === 0){
            let newP = [productName, selectSize, selectColor, productCount, price * productCount];
            resultList.push(newP);
        }else{
            for(var i=0; i<resultList.length; i++) {
                if(resultList[i][1] === selectSize && resultList[i][2] === selectColor){
                    // 이미 포함된 상품이면
                    resultList[i][3] += productCount;
                    let newPrice = price * productCount;
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

        // 테이블 가져오기
        let table = document.getElementById('selectResultTable');
        let tableSize = table.rows.length;

        // 기존 테이블 삭제
        for(let i=1; i < tableSize; i++) {
            table.deleteRow(1);
        }

        // 새로운 행과 요소들 생성
        let newRow;
        let totalPrice = 0;
        for(var i=0; i<resultList.length; i++) {
            newRow = table.insertRow();
            newRow.insertCell(0).innerText = '(선택 ' + (i+1) + ')';       // 결과: (선택 i)
            newRow.insertCell(1).innerText = resultList[i][1];            // 결과: 사이즈
            newRow.insertCell(2).innerText = resultList[i][2];            // 결과: 색상
            newRow.insertCell(3).innerText = resultList[i][3];            // 결과: 개수
            newRow.insertCell(4).innerText = resultList[i][4] + '원';     // 결과: 가격
            totalPrice += resultList[i][4];
        }

        // 총가격 계산
        let totalPriceElement = document.getElementById('totalPrice');
        totalPriceElement.innerText = "총 " + totalPrice + "원";
}

// 장바구니 담기
function saveBasket() {

    var productName = document.getElementById('selectSize');
    var selectColorObject = document.getElementById('selectColor');
    var selectSizeObject = document.getElementById('selectSize');

    var color = selectColorObject.options[selectColorObject.selectedIndex].value;
    var size = selectSizeObject.options[selectSizeObject.selectedIndex].value;

    console.log(color, size);
}