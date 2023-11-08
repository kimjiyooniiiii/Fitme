let basketTotalPrice = 0;

// 장바구니 화면 로드시
$(function() {

    let basketList = JSON.parse(localStorage.getItem("basketList"));
    let newRow = '';

    // 장바구니에 상품이 담겨있으면
    if(basketList !== null && basketList.length !== 0){
        for(let i=0; i<basketList.length; i++){
                basketTotalPrice += basketList[i]["productPrice"];  // 총 가격 계산

                newRow += '<tr class="basketTableRow">';
                newRow += '<td class="td1 td2" style="width:30px">';
                newRow += '<input type="checkbox" checked id="check' + i+1 + '" name="checkProduct" value="oneCheck" onchange="changeCheck(event)">';
                newRow += '<label for="check' + i+1 + '"/>';
                newRow += '</td>';
                newRow += '<td class="td1 td2">'
                        + '<a href="/productDetail?id=' + basketList[i]["productId"] + '">'
                        + '<img id = "tablePImage" src="' + basketList[i]["productImage"] + '" alt=\"상품 이미지\" >' + '</a></td>';
                newRow += '<td class="td1 td2">' + basketList[i]["productName"] + '</td>';

                let optionRow = '<td class="td1 td2">';
                for(key in basketList[i]["productOptions"]) {
                    optionRow += '' + key + '&nbsp;&nbsp;&nbsp;'
                                    + basketList[i]["productOptions"][key] + '개 <br>';
                }
                optionRow += '</td>';
                newRow += optionRow;
                newRow += '<td class="td1 td2">' + basketList[i]["productPrice"] + '원</td>';


                newRow += '<td class="td1 td2">' + '<button type="button" class="delBasketBtn" onclick ="delBasketProduct(event)" style="vertical-align : bottom;">'
                         + "X" + '</button>' + '</td>';                           // 결과: 삭제버튼
                newRow += '</tr>';

                // 새로운 행 추가
                $("#basketListTable").append(newRow);
                newRow = '';
            }
    }

    // 장바구니가 비어있으면
    else {
        newRow += '<tr class="basketTableRow">';
        newRow += '<td id="emptyTable" colspan="5">';
        newRow += '장바구니가 비어있습니다';
        newRow += '</td>';
        newRow += '</tr>';

        $("#basketListTable").append(newRow);
        newRow = '';
    }

    // 총 가격 계산 로직
    let newPriceRow = "";

    if(basketTotalPrice === 0) {
        newPriceRow += '<h1 id="priceInfo">상품 금액&nbsp; 0원';
        newPriceRow += '&nbsp; + &nbsp;배송비 0원 &nbsp;=&nbsp; 총 금액&nbsp; 0원</h1>';
    }else{
        newPriceRow += '<h1 id="priceInfo">상품 금액 ' + basketTotalPrice + '원';
        newPriceRow += '&nbsp; + &nbsp;배송비 3000원 &nbsp;=&nbsp; 총 금액 ' + (basketTotalPrice + 3000);
        newPriceRow += '원</h1>';
    }

    let orderBtn = '<button id="order" onclick="order()">주문하기</button>';

    $(".priceRow").append(newPriceRow);
    $(".orderPosition").append(orderBtn);

})

// 주문하기 버튼
function order() {

    location.href = "/order";
}


// 체크박스 선택, 해제 시 -> 총 가격 변화
function changeCheck(e) {
        let newPriceRow = '';

        // 체크박스 위치 상품의 가격
        let price = parseInt($(e.target).closest('tr').find('td')[4].innerText.slice(0, -1), 10);

        if(e.target.checked === true) {
            basketTotalPrice += price;
        }else{
            basketTotalPrice -= price;
        }

        // 기존의 총 가격 삭제
        $(".priceRow").empty();

        if(basketTotalPrice === 0) {
            newPriceRow += '<h1 id="priceInfo">상품 금액&nbsp; 0원';
            newPriceRow += '&nbsp; + &nbsp;배송비 0원 &nbsp;=&nbsp; 총 금액&nbsp; 0원</h1>';
        }else{
            newPriceRow += '<h1 id="priceInfo">상품 금액 ' + basketTotalPrice + '원';
            newPriceRow += '&nbsp; + &nbsp;배송비 3000원 &nbsp;=&nbsp; 총 금액 ' + (basketTotalPrice + 3000);
            newPriceRow += '원</h1>';
        }

        // 새로운 총 가격 생성
        $(".priceRow").append(newPriceRow);
}

// 상품삭제 버튼 이벤트
function delBasketProduct(e) {
       let basketList = JSON.parse(localStorage.getItem("basketList"));

       Swal.fire({
          title: '삭제 하시겠습니까?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#B1450F',
          cancelButtonColor: '#D5B59C',
          confirmButtonText: 'Yes',
          cancelButtonText: 'No',
          background: '#F3F1ED'
        }).then((result) => {
          if (result.isConfirmed) {
            // 테이블에서 해당 행 삭제 -> 페이지 새로고침
            $(e.target).closest('tr').remove();
            location.reload(true);

            let tdArray = $(e.target).closest('tr').find('td');
            let delName = tdArray[2].innerText;

            // localStorage에서도 해당 상품 삭제
            if(basketList !==null) {
                 for(let i=0; i<basketList.length; i++) {
                      if(basketList[i]["productName"] === delName) {
                          basketList.splice(i, 1);
                          localStorage.setItem("basketList",JSON.stringify(basketList));
                          break;
                      }
                 }
            }
          }
        })
}

// 장바구니 담기 -> localStorage에 저장
function saveBasket() {

        // 상품 옵션을 선택했다면
        if(selectedMap.size !== 0) {
            let productId = document.getElementById('productId').value;
            let productName = document.getElementById('pn').innerText;
            let productPrice = parseInt(document.getElementById('price').innerText, 10);
            let productImage = document.getElementById('productImage').src;
            let totalPrice = 0;
            let option = {};

            // selectedMap(전역변수) -> key : '사이즈,색상',  value : {사이즈, 색상, 수량}
            selectedMap.forEach((value, key) => {
                option[key] = value.count;
                totalPrice += (productPrice * value.count);
            });

            let product = {"productId" : productId,
                            "productImage" : productImage,
                            "productName" : productName,
                            "productOptions" : option,
                            "productPrice" : totalPrice
                            };

            let basketList = [];

            if(localStorage.getItem("basketList") === null) {   // 장바구니 이용이 처음이면 장바구니 생성
                basketList.push(product);
                let newBasketList = JSON.stringify(basketList);
                localStorage.setItem("basketList",newBasketList);
            }else{
                let prevBasketList = JSON.parse(localStorage.getItem("basketList"));
                let isExistProduct = false;

                for(let i=0; i<prevBasketList.length; i++){
                    // 아이디가 같은 상품이 이미 담겨 있다면
                    if(prevBasketList[i]["productId"] === productId){
                        isExistProduct = true;
                        let newOption = Object.keys(option);    // 현재 추가하는 옵션

                        // 추가를 원하는 옵션이 이미 존재하는지
                        for(let key of newOption) {
                            if(prevBasketList[i]["productOptions"].hasOwnProperty(key)){
                                prevBasketList[i]["productOptions"][key] += option[key];
                            }else{
                                prevBasketList[i]["productOptions"][key] = option[key];
                            }
                        }
                        prevBasketList[i]["productPrice"] += product["productPrice"];
                        break;  // 같은 상품을 찾았으니 더이상 순회하지 않음
                     }
                }
                // 새로운 상품을 추가하는 경우라면
                if(!isExistProduct) {
                    prevBasketList.push(product);
                }
                localStorage.setItem("basketList",JSON.stringify(prevBasketList));
            }

            // 장바구니 저장 완료 메시지
            Swal.fire({
              title: '장바구니에 담았습니다!',
              icon: 'success',
              showCancelButton: true,
              confirmButtonColor: '#C7B199',
              cancelButtonColor: '#C7B199',
              confirmButtonText: '장바구니 이동',
              cancelButtonText: '계속 쇼핑하기',
              background: '#F3F1ED'
            }).then((result) => {
              if (result.isConfirmed) {
                 location.href = "/myBasket";
              }
            })
        }
        // 아무 상품도 선택하지 않았다면
        else{
            Swal.fire({
              title: '옵션을 선택해주세요',
              icon: 'warning',
              confirmButtonColor: '#C7B199',
              confirmButtonText: '확인',
              background: '#F3F1ED'
            })
        }

}