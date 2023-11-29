// 주문 화면 로드시
$(function() {
    let newRow = '';
    let orderIdSet = new Set();
    let orderIdArray = [];
    console.log(orderList);

    // 주문 내역이 있으면
    if(orderList !== null && orderList.length != 0){
        // 주문 번호대로 분류한다.
        for(let i=0; i<orderList.length; i++){
                orderIdSet.add(orderList[i].orderDetailsId);
        }
        orderIdArray = Array.from(orderIdSet);
        orderIdArray = orderIdArray.sort().reverse();
        $('#orderCount').append('총 '+ orderIdArray.length + '건');       //주문서 갯수

        // 같은 주문번호끼리 묶어서 출력
        let isSame = false;
        for(let i=0; i<orderIdArray.length; i++){
              for(let j=0; j<orderList.length; j++){
                      if(orderList[j]["orderDetailsId"] ===  orderIdArray[i]){
                            if(isSame==false){    // 같은 주문번호는 (주문일, 받는분, 주소, 연락처)를 한번만 출력한다.
                                newRow += '<div class=orderList>'
                                newRow += '<div class="row" id="orderNumRow">';
                                newRow += '<div class="col-md-6 col-sm-6" id="orderNumColumn">';
                                newRow += '<p style="font-weight:bold; font-size: 30px;"> 주문번호 ' + orderIdArray[i] + '</p>';
                                newRow += '</div>';
                                newRow += '<div class="col-md-6 col-sm-6 text-end" id="orderDateColumn">';
                                newRow += '<p style="font-weight:bold; font-size: 30px;"> 주문일자 ' + orderList[j]["orderDateTime"] + '</p>';
                                newRow += '</div></div>';

                                newRow += '<div class="row" id="personalRow">';
                                newRow += '<p> 받는분 : ' + orderList[j]["receiver"] + '</p>';
                                newRow += '<p> 우편번호 : ' + orderList[j]["zipCode"] + '&nbsp;&nbsp; 주소 : ' + orderList[j]["address"] + '</p>';
                                newRow += '<p> 연락처 : ' + orderList[j]["phone"] + '</p>';
                                newRow += '</div>';

                                newRow += '<div class="row" style="margin-top:5px; margin-left:20px; margin-right:20px;">';
                                newRow += '<table class="orderTable" id="orderListTable'+ i +'">';
                                newRow += '<tr class="orderTableRow" style="border-top:0px;">';
                                newRow += '<td class="titleTd" />';
                                newRow += '<td class="titleTd">상품명</td>';
                                newRow += '<td class="titleTd">옵션 / 수량</td>';
                                newRow += '<td class="titleTd">가격</td>';
                                newRow += '</tr></table></div>';

                                $("#list").append(newRow);
                                newRow = '';
                            }
                            newRow += '<tr class="orderTableRow">';
                            newRow += '<td class="td1 td2">'
                                       + '<a href="/productDetail?id=' + orderList[j]["productId"] + '">'
                                       + '<img id = "tablePImage" src="' + orderList[j]["mainImageList"] + '" alt=\"상품 이미지\" >' + '</a></td>';
                            newRow += '<td class="td1 td2">' + orderList[j]["productName"] + '</td>';   /*상품 이름*/

                            /*옵션 데이터 파싱, 원본형식 : {"blackM"=1, "blueXL"=2}*/
                            let optionList = orderList[j]["options"].slice(1,-1).split(", ");
                            let optionResult = "";
                            for(let o=0; o<optionList.length; o++){
                                let strSlice = optionList[o].split("=");
                                let optionName = strSlice[0];
                                let optionCount = strSlice[1];
                                optionResult += optionName + '&nbsp;' + optionCount + '개' +'<br>';
                            }

                            newRow += '<td class="td1 td2">' + optionResult + '</td>';       /*상품 옵션*/
                            newRow += '<td class="td1 td2">' + orderList[j]["productPrice"] + '원</td>';  /*상품 가격*/
                            newRow += '</tr>';

                            $('#orderListTable'+i).append(newRow);
                            newRow = '';

                            isSame = true;
                      }
              }
              // 결제, 주문 취소 행 생성
               newRow += '<tr class="orderTableRow" style="font-size:25px; border-bottom:0px">';
               newRow += '<td class="td1 td2" colspan="3" style="text-align:left; margin-left:20px;">';
               newRow += '상품 금액 &nbsp;' + orderList[0].orderTotalPrice + '원 + 배송비 3000원';
               newRow += ' = 총 결제금액 &nbsp;' + (orderList[0].orderTotalPrice+3000) +'원<Br>';

               newRow += '결제 방법 : ' + orderList[0].payment + '<br>';
               newRow += '주문 상태 : ' + orderList[0].orderState;
               newRow += '</td>';

               newRow += '<td class="td1 td2 style="height:100px"><button id="orderCancel"' + i + '>주문취소</button></td>';
               newRow += '</tr></div>'
                $('#orderListTable'+i).append(newRow);
                newRow = '';

              isSame = false;
        }
    }

    // 주문 내역이 없으면
    else {
        newRow += '<tr class="orderTableRow">';
        newRow += '<td id="emptyTable" colspan="5">';
        newRow += '주문 내역이 없습니다';
        newRow += '</td>';
        newRow += '</tr>';

        $("#orderListTable").append(newRow);
        newRow = '';
    }
})