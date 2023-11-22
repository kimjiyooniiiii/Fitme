// 주문 화면 로드시
$(function() {

    let newRow = '';
    let orderId = [];
    console.log(orderList);

    // 주문 내역이 있으면
    if(orderList !== null && orderList.length != 0){
        // 주문 번호대로 분류한다.
        for(let i=0; i<orderList.length; i++){
                orderId.push(orderList[i].orderDetailsId);
        }

        //String address = "", zipCode = "", orderDate = "", totalPrice = 0, payment = "", receiver = "", phone = "", orderState = "";
        let isSame = false;
        for(let i=0; i<orderId.length; i++){
              for(let j=0; j<orderList.length; j++){
                      if(orderList[j]["orderDetailsId"] ===  orderId[i]){   // 주문번호 순서대로 출력
                            if(!isSame){
                                   newRow += '<tr class="orderTableRow">';
                                   newRow += '<td class="td1 td2">' + orderList[i]["address"] + '&nbsp;&nbsp;' + orderList[i]["zipCode"] + '</td>';
                                   newRow += '<td class="td1 td2">' + orderList[i]["orderDateTime"] + '</td>';
                                   newRow += '<td class="td1 td2"> 총 가격 : ' + orderList[i]["orderTotalPrice"] + '</td>';
                                   newRow += '<td class="td1 td2">' + orderList[i]["payment"] + '</td>';
                                   newRow += '<td class="td1 td2">' + orderList[i]["receiver"] + '</td>';
                                   newRow += '<td class="td1 td2">' + orderList[i]["phone"] + '</td>';
                                   newRow += '<td class="td1 td2">' + orderList[i]["orderState"] + '</td></tr>';

                                   $("#orderListTable").append(newRow);
                                   newRow = '';
                            }
                            newRow += '<tr class="orderTableRow">';
                            newRow += '<td class="td1 td2">'
                                       + '<a href="/productDetail?id=' + orderList[i]["productId"] + '">'
                                       + '<img id = "tablePImage" src="' + orderList[i]["mainImageList"] + '" alt=\"상품 이미지\" >' + '</a></td>';
                            newRow += '<td class="td1 td2">' + orderList[i]["productName"] + '</td>';
                            newRow += '<td class="td1 td2">' + orderList[i]["options"] + '</td>';
                            newRow += '<td class="td1 td2">' + orderList[i]["productPrice"] + '</td>';
                            newRow += '</tr>';

                            $("#orderListTable").append(newRow);
                            newRow = '';

                            isSame = true;
/*
                            address = orderList[i]["address"];
                            zipCode = orderList[i]["zipCode"];
                            orderDate = orderList[i]["orderDateTime"];
                            totalPrice = orderList[i]["orderTotalPrice"];
                            payment = orderList[i]["payment"];
                            receiver = orderList[i]["receiver"];
                            phone = orderList[i]["phone"];
                            orderState = orderList[i]["orderState"];*/

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