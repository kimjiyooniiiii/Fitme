
// 주문 화면 로드시
$(function() {
    let basketList = JSON.parse(localStorage.getItem("basketList"));        // 장바구니 상품들
    let preOrderList = JSON.parse(localStorage.getItem("preOrderList"));    // 구매 체크된 상품들
    let orderTotalPrice = 0;        // 총 주문 가격

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
        }
})

function payment() {
    IMP.init('imp51407210');
    IMP.request_pay({
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: "fitmeShoppingmall",
        name:"의류",
        amount: 10,
        buyer_email: "coolcool35@naver.com",
        buyer_name: "손님",
        buyer_tel : "01032173893"
    }, function(response){
        if(response.success) {
            alert("결제 완료 -> imp_uid : "+response.imp_uid + " / merchant_uid(orderKey) : " + response.merchant_uid);
        }else{
            alert("실패 : 코드(" + response.error_code + ") / 메시지(" + response.error_msg + ")");
        }
    });
}
