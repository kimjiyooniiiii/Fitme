let orderTotalPrice = 0;        // 총 주문 가격
let preOrderList = JSON.parse(localStorage.getItem("preOrderList"));    // 구매 체크된 상품들
let orderCompleteOptions = [];
let basketList = JSON.parse(localStorage.getItem("basketList"));        // 장바구니 상품들

// 주문 화면 로드시
$(function() {

    if(basketList === null || basketList.length === 0){
            // 경고 메시지
            Swal.fire({
                  title: '장바구니가 비어있어요!',
                  icon: 'warning',
                  confirmButtonColor: '#8C4A2F',
                  confirmButtonText: '확인',
                  background: '#F3F1ED'
            }).then((result) => {
                if (result.isConfirmed){
                     window.location.href = "/myBasket";
                }
            })
    }
   else{
       // 테이블 동적 생성
       let newRow = '';

        for(let i=0; i<basketList.length; i++) {
            for(let j=0; j<preOrderList.length; j++) {
                if(basketList[i]["productId"] === preOrderList[j]){
                    orderTotalPrice += basketList[i]["productPrice"];       // 총 가격에 추가
                    orderCompleteOptions.push(basketList[i]);

                    newRow += '<tr class="orderTableRow">';

                    // 상품 이미지
                    newRow += '<td class="td1 td2">'
                            + '<a href="/productDetail?id=' + basketList[i]["productId"] + '">'
                            + '<img id = "tablePImage" src="' + basketList[i]["productImage"] + '" alt=\"상품 이미지\" >' + '</a></td>';
                    newRow += '<td class="td1 td2">' + basketList[i]["productName"] + '</td>';

                    // 옵션
                    let optionRow = '<td class="td1 td2">';
                    for(let key in basketList[i]["productOptions"]) {
                        optionRow += '' + key + '&nbsp;&nbsp;&nbsp;'
                                        + basketList[i]["productOptions"][key] + '개 <br>';
                    }
                    optionRow += '</td>';
                    newRow += optionRow;

                    // 가격
                    newRow += '<td class="td1 td2">' + basketList[i]["productPrice"] + '원</td>';

                    // 결과: 삭제버튼
                    newRow += '</tr>';

                    // 새로운 행 추가
                    $("#orderTable").append(newRow);
                    newRow = '';

                    break;
                }
            }
        }

        // 테이블 안의 총 가격 행
        let priceRow = '';

        priceRow += '<tr class="orderTableRow">';
        priceRow += '<td id="orderTotalPrice" colspan="5">상품 금액 ' + orderTotalPrice + '원';
        priceRow += '&nbsp; + &nbsp;배송비 3000원 &nbsp;=&nbsp; 총 금액 ' + (orderTotalPrice + 3000);
        priceRow += '원</td></tr>';

         $("#orderTable").append(priceRow);

         $('#finalPrice').append((orderTotalPrice + 3000) + '원');
        }

})

// 주소찾기 버튼 클릭 이벤트
function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipCodeInput').value = data.zonecode;
                document.getElementById("addressInput").value = addr;
            }
        }).open();
}

//  결제하기 버튼 클릭 이벤트
function payment() {

    // 주문자명, 주소, 결제방법 "미 입력시" 알림
    if($('#receiverInput').val().length === 0) {
        blankAlert('주문자명을');
        $('#receiverInput').focus();
    } else if($('#zipCodeInput').val().length === 0 || $('#addressInput').val().length === 0) {
        blankAlert('주소를');
         $('#zipCodeInput').focus();
    } else if($('#kakaoCheck').is(':checked') === false) {
        blankAlert('결제 방법을');
    }else{
          // 결제 재확인 alert
          Swal.fire({
              title: '결제 하시겠습니까?',
              icon: 'info',
              showCancelButton: true,
              confirmButtonColor: '#8C4A2F',
              cancelButtonColor: '#8C4A2F',
              confirmButtonText: 'Yes',
              cancelButtonText: 'No',
              background: '#F3F1ED'
            }).then((result) => {
              if (result.isConfirmed) {         // 결제 창 띄우기
                IMP.init('imp51407210');
                    IMP.request_pay({
                        pg: "kakaopay.TC0ONETIME",
                        pay_method: "card",
                        merchant_uid: $('#receiverInput').val() + '_' + new Date(),    // 주문자명 + 날짜
                        name:"의류",
                        amount: orderTotalPrice,
                        buyer_name: $('#receiverInput').val(),
                        buyer_tel : $('#receiverPhone').val()
                    }, function(response){
                        if(response.success) {          // 결제 성공
                            // 서버로 내역 전송 (주문자명, 주소, 핸드폰번호, 결제 내역(상품명, 상품아이디, 옵션, 수량, 가격, 결제방법))
                            orderResultToServer();
                            console.log("결제 완료 -> imp_uid : "+response.imp_uid + " / merchant_uid(orderKey) : " + response.merchant_uid);

                        }
                        else{          // 결제 실패
                             Swal.fire({
                                  title: '결제에 실패하였습니다!',
                                  text: response.error_msg,
                                  icon: 'error',
                                  confirmButtonColor: '#8C4A2F',
                                  confirmButtonText: 'Yes',
                                  background: '#F3F1ED'
                                });
                            console.log("실패 : 코드(" + response.error_code + ") / 메시지(" + response.error_msg + ")");
                        }
                    });
              }
          });
     }
}

//서버로 결제 내역 전송
function orderResultToServer() {

    let orderList = [];
    for(let i=0; i<orderCompleteOptions.length; i++) {

        let orderProduct = {"productId" : orderCompleteOptions[i]["productId"],
                            "productName" : orderCompleteOptions[i]["productName"],
                            "productOptions" : orderCompleteOptions[i]["productOptions"],
                            "productPrice" : orderCompleteOptions[i]["productPrice"]
                            };
        orderList.push(orderProduct);

    }
    const orderDetails = {
                          "receiver" : $('#receiverInput').val(),
                          "phone" : $('#receiverPhone').text(),
                          "address" : $('#addressInput').val(),
                          "zipCode" : $('#zipCodeInput').val(),
                          "payment" : "카카오페이",
                          "orderedItemsDto" : orderList,
                          "totalPrice" : orderTotalPrice
                          };

    $.ajax({
        type: "post",
        url: "/orderComplete",
        data: JSON.stringify(orderDetails),
        dataType: "json",               // 받을 데이터 타입
        contentType: "application/json; charset=UTF-8",     // 보낼 데이터 타입

        success: function(result) {
                  Swal.fire({
                          title: '주문을 성공하였습니다!',
                          icon: 'success',
                          confirmButtonColor: '#8C4A2F',
                          confirmButtonText: '확인',
                          background: '#F3F1ED'
                          }).then((result) => {
                                    if (result.isConfirmed) {
                                          window.location.href = "/";
                                     }
                          });

                  // 장바구니에서 주문 목록 삭제
                  let prevBasketList = JSON.parse(localStorage.getItem("basketList"));

                  for(let i=0; i<prevBasketList.length; i++) {
                         for(let j=0; j<preOrderList.length; j++) {
                                if(prevBasketList[i]["productId"] === preOrderList[j]){
                                      prevBasketList.splice(i, 1);
                                      i--;
                                      break;
                                }
                         }
                  }
                  window.localStorage.removeItem('preOrderList');
                  localStorage.setItem("basketList",JSON.stringify(prevBasketList));
        },
        error: function(request, status, error) {
                  Swal.fire({
                           title: '주문에 실패하였습니다!',
                           text: request.status + ", " + error,
                           icon: 'error',
                           confirmButtonColor: '#8C4A2F',
                           confirmButtonText: 'Yes',
                           background: '#F3F1ED'
                 });
        }
    });
}


// 주문서 유효성 알림
function blankAlert(blankInput) {
     Swal.fire({
          title: blankInput + ' 입력해주세요!',
          icon: 'warning',
          confirmButtonColor: '#8C4A2F',
          confirmButtonText: 'Yes',
          background: '#F3F1ED'
        });
}

//  결제취소 버튼 클릭 이벤트
function cancelOrder(){
      // 취소 재확인 alert
      Swal.fire({
          title: '주문을 취소하시겠습니까?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#8C4A2F',
          cancelButtonColor: '#8C4A2F',
          confirmButtonText: 'Yes',
          cancelButtonText: 'No',
          background: '#F3F1ED'
        }).then((result) => {
                 if (result.isConfirmed) {
                    window.location.href = "/myBasket";
                 }
        });
}
