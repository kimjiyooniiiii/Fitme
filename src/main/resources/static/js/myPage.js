// 주문 화면 로드시
$(function() {

    let newRow = '';
    let orderIdSet = new Set();
    let orderIdArray = [];

    // 주문 내역이 있으면
    if(orderList !== null && orderList.length != 0){
        // 주문 번호대로 분류한다.
        for(let i=0; i<orderList.length; i++){
                orderIdSet.add(orderList[i].orderDetailsId);
        }
        orderIdArray = Array.from(orderIdSet);
        orderIdArray = orderIdArray.sort().reverse();

        // 같은 주문번호끼리 묶어서 출력
        let isSame = false;
        for(let i=0; i<orderIdArray.length; i++){
              for(let j=0; j<orderList.length; j++){
                      if(orderList[j]["orderDetailsId"] ===  orderIdArray[i]){
                            if(isSame==false){    // 같은 주문번호는 (주문일, 받는분, 주소, 연락처)를 한번만 출력한다.
                                newRow += '<div class="row" id="orderNumRow" style="margin-top:30px">';
                                newRow += '<div class="col-md-6 col-sm-6" id="orderNumColumn">';
                                newRow += '<p> 주문번호 ' + orderIdArray[i] + '</p>';
                                newRow += '</div>';
                                newRow += '<div class="col-md-6 col-sm-6 text-end" id="orderDateColumn">';
                                newRow += '<p> 주문일자 ' + orderList[j]["orderDateTime"] + '</p>';
                                newRow += '</div></div>';

                                newRow += '<div class="row" id="personalRow" style="margin-top:0px">';
                                newRow += '<p> 받는분 : ' + orderList[j]["receiver"] + '</p>';
                                newRow += '<p> 우편번호 : ' + orderList[j]["zipCode"] + '&nbsp;&nbsp; 주소 : ' + orderList[j]["address"] + '</p>';
                                newRow += '<p> 연락처 : ' + orderList[j]["phone"] + '</p>';
                                newRow += '</div>';

                                newRow += '<div class="row" style="margin-top:30px">';
                                newRow += '<table id="orderListTable'+ i +'">';
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
                            newRow += '<td class="td1 td2">' + orderList[j]["productPrice"] + '</td>';  /*상품 가격*/
                            newRow += '</tr>';

                            $('#orderListTable'+i).append(newRow);
                            newRow = '';

                            /*if(!isSame){    // 같은 주문번호는 (총 결제금액, 결제방법, 주문상태)를 한번만 출력한다.
                                newRow += '<tr class="orderTableRow">';
                                newRow += '<td class="td1 td2">' + orderList[i]["productName"] + '</td>';   *//*상품 이름*//*
                            }*/
                            isSame = true;
                      }
              }
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