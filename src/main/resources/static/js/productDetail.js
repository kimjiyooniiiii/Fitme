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

/*상품 옵션 선택*/
let selectedMap = new Map();    // key : '사이즈,색상',  value : {사이즈, 색상, 수량}
let productTotalPrice = 0;      // 옵션 전체 가격

function addProduct() {
        let table = document.getElementById('selectResultTable');
        let tableSize = table.rows.length;

        // 테이블 리프레쉬를 위해 전체삭제
        for(let i=1; i < tableSize; i++) {
            table.deleteRow(1);
        }

        const productName = document.getElementById('pn').innerText;   // 상품명
        const selectColorObject = document.getElementById('selectColor');
        const selectSizeObject = document.getElementById('selectSize');
        const selectColor = selectColorObject.options[selectColorObject.selectedIndex].value;  // 선택 색상
        const selectSize = selectSizeObject.options[selectSizeObject.selectedIndex].value;    // 선택 사이즈
        const price = parseInt(document.getElementById('price').innerText, 10);         // 가격
        const productCount = parseInt(document.getElementById('countResult').innerText, 10);   // 선택 수량

        // map에 저장할 key,value 생성
        let key = selectColor + selectSize;
        let product = {
            "color" : selectColor,
            "size" : selectSize,
            "count" : productCount,
            "price" : productCount*price
        }

        // map에 상품 저장
        if(selectedMap.has(key)){
            // 개수 업데이트
            selectedMap.get(key).count += productCount;

        }else{
            selectedMap.set(key, product);
        }

        let sequence = 1;
        let newRow = '';
        productTotalPrice = 0;
        let totalPriceElement = document.getElementById('totalPrice');

        // 테이블 재생성
        selectedMap.forEach((v, k) => {
            newRow += '<tr>';
            newRow += '<td>' + "(선택 " + sequence + ")" + '</td>';                // 결과: (선택 i)
            newRow += '<td>' + v.size + '</td>';                     // 결과: 사이즈
            newRow += '<td>' + v.color + '</td>';                     // 결과: 색상
            newRow += '<td>' + v.count + '</td>';                     // 결과: 개수

            var calculate = v.count * price;
            newRow += '<td>' + calculate + "원" + '</td>';              // 결과: 가격
            newRow += '<td>' + '<button type="button" class="delBtn" id="del'
                       + sequence + '" style="vertical-align : bottom;">'
                       + "X" + '</button>' + '</td>';                           // 결과: 삭제버튼
            newRow += '</tr>';

            // 새로운 행 추가
            $("#selectResultTable").append(newRow);
            newRow = '';
            sequence++;
            productTotalPrice += calculate;
        });

        // 총 가격 출력
        totalPriceElement.innerText = "총 가격 : " + productTotalPrice + "원";

        // 상품삭제 버튼 이벤트
        $(".delBtn").on("click",function(e){
            $(e.target).closest('tr').remove();
            let tdArray = $(e.target).closest('tr').find('td');
            selectedMap.delete(tdArray[2].innerText + tdArray[1].innerText);

            // 총 가격 다시 계산
            let delPrice = tdArray[4].innerText.slice(0, -1);
            productTotalPrice -= parseInt(delPrice, 10);
            totalPriceElement.innerText = "총 가격 : " + productTotalPrice + "원";
        });
}
