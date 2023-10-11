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
var selectedMap = new Map();    // key : '사이즈,색상',  value : {사이즈, 색상, 수량}

function addProduct() {
        const productName = document.getElementById('pn').innerText;   // 상품명
        const selectColorObject = document.getElementById('selectColor');
        const selectSizeObject = document.getElementById('selectSize');
        const selectColor = selectColorObject.options[selectColorObject.selectedIndex].value;  // 선택 색상
        const selectSize = selectSizeObject.options[selectSizeObject.selectedIndex].value;    // 선택 사이즈
        const price = parseInt(document.getElementById('price').innerText, 10);         // 가격
        const productCount = parseInt(document.getElementById('countResult').innerText, 10);   // 선택 수량

        // map에 저장할 key,value 생성
        const key = selectColor + selectSize;
        const product = {
            color : selectColor,
            size : selectSize,
            count : productCount
        }

        // map에 상품 저장
        if(selectedMap.has(key)){
            selectedMap.get(key).count += productCount;
        }else{
            selectedMap.set(key, product);
        }

        let table = document.getElementById('selectResultTable');
        let tableSize = table.rows.length;

        // 테이블 리프레쉬를 위한 리셋
        for(let i=1; i < tableSize; i++) {
            table.deleteRow(1);
        }

        let totalPrice = 0;
        let newRow = '';
        let sequence = 1;

        selectedMap.forEach((v, k) => {
            newRow += '<tr>';
            newRow += '<td>' + "(선택 " + sequence + ")" + '</td>';                // 결과: (선택 i)
            newRow += '<td>' + v.size + '</td>';                     // 결과: 사이즈
            newRow += '<td>' + v.color + '</td>';                     // 결과: 색상
            newRow += '<td>' + v.count + '</td>';                     // 결과: 개수

            var calculate = v.count * price;
            newRow += '<td>' + calculate + "원" + '</td>';              // 결과: 가격
            newRow += '<td>' + '<button type="button" class="delBtn" style="width:50px">' +
                        "X" + '</button>' + '</td>';                           // 결과: 삭제버튼
            newRow += '</tr>';

            // 새로운 행 추가
            $("#selectResultTable").append(newRow);
            newRow = '';
            sequence++;
            totalPrice += calculate;

             // 상품삭제 버튼 이벤트
             $(".delBtn").on("click",function(){
                  $(this).closest('tr').remove();
             });
        });

        // 총가격 출력
        let totalPriceElement = document.getElementById('totalPrice');
        totalPriceElement.innerText = "총 가격 : " + totalPrice + "원";

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