let preOrderList = [];      // 구매 체크된 상품들
let basketTotalPrice = 0;

// 장바구니 화면 로드시
$(function() {
    let basketList = JSON.parse(localStorage.getItem("basketList"));
    let newRow = '';

    // 장바구니에 상품이 담겨있으면
    if(basketList !== null && basketList.length !== 0){
        for(let i=0; i<basketList.length; i++){
                basketTotalPrice += basketList[i]["productPrice"];  // 총 가격 계산
                preOrderList.push(basketList[i]["productId"]);      // 결제 예정 배열에 추가

                newRow += '<tr class="basketTableRow">';
                newRow += '<td class="td1 td2" style="width:30px">';
                newRow += '<input type="checkbox" class="checkEvent" checked id="check' + (basketList[i]["productId"]) + '" name="checkProduct" value="oneCheck" >';
                newRow += '<label for="check' + (basketList[i]["productId"]) + '"/>';
                newRow += '</td>';
                newRow += '<td class="td1 td2">'
                        + '<a href="/productDetail?id=' + basketList[i]["productId"] + '">'
                        + '<img id = "tablePImage" src="' + basketList[i]["productImage"] + '" alt=\"상품 이미지\" >' + '</a></td>';
                newRow += '<td class="td1 td2">' + basketList[i]["productName"] + '</td>';

                let optionRow = '<td class="td1 td2">';
                for(let key in basketList[i]["productOptions"]) {
                    optionRow += '' + key + '&nbsp;&nbsp;&nbsp;'
                                    + basketList[i]["productOptions"][key] + '개 <br>';
                }
                optionRow += '</td>';
                newRow += optionRow;
                newRow += '<td class="td1 td2">' + basketList[i]["productPrice"] + '원</td>';

                newRow += '<td class="td1 td2">' + '<button type="button" class="delBasketBtn" style="vertical-align : bottom;">'
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

    //----------------- 상품삭제 버튼 이벤트----------------------------------
    $(".delBasketBtn").on("click",function(e){
          Swal.fire({
          title: '삭제 하시겠습니까?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#8C4A2F',
          cancelButtonColor: '#8C4A2F',
          confirmButtonText: 'Yes',
          cancelButtonText: 'No',
          background: '#F3F1ED'
        }).then((result) => {
          if (result.isConfirmed) {
            // 테이블에서 해당 행 삭제 -> 페이지 새로고침
            $(e.target).closest('tr').remove();

            // '주문예정 상품배열'에서 삭제한 '상품의 아이디' 삭제
            let checkProductId = e.target.id.substr(5);

            for(let i=0; i<preOrderList.length; i++){
                if(preOrderList[i] === checkProductId){
                    preOrderList.splice(i,1);
                    break;
                }
            }

            //장바구니 새로고침
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
    });

    //----------------- 체크박스 선택, 해제 시 -> 총 가격 변화----------------------------------
   $(".checkEvent").on("click",function(e){
            let newPriceRow = '';
            let checkProductId = e.target.id.substr(5);    // 체크박스의 상품 아이디

             // 체크박스 위치 상품의 가격
              let price = parseInt($(e.target).closest('tr').find('td')[4].innerText.slice(0, -1), 10);

             if(e.target.checked === true) {
                    basketTotalPrice += price;
                    // '주문예정 상품배열'에 체크한 상품의 아이디 추가
                     preOrderList.push(e.target.id.substr(5));
             }
             else{
                    basketTotalPrice -= price;
                    // '주문예정 상품배열'에서 체크 해제한 상품의 아이디 삭제
                    for(let i=0; i<preOrderList.length; i++){
                          if(preOrderList[i] === checkProductId){
                              preOrderList.splice(i,1);
                              //removeOrderList(i);
                              break;
                          }
                    }
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
      });

    //-----------------------------가격 정보 이하------------------------------------
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
    $(".priceRow").append(newPriceRow);

    // 주문하기 버튼 생성
    let orderBtn = '<button id="order">주문하기</button>';
    $(".orderPosition").append(orderBtn);

    $("#order").on("click",function(e){
        if(preOrderList !== null) {
            localStorage.setItem("preOrderList",JSON.stringify(preOrderList));
        }else{
            // 경고 메시지
            Swal.fire({
                  title: '상품을 선택해주세요!',
                  icon: 'warning',
                  confirmButtonColor: '#8C4A2F',
                  confirmButtonText: '확인',
                  background: '#F3F1ED'
            })
        }
        window.location.href = "/order";
    });

})
