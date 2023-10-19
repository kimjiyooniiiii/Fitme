function allCheckFunction(all) {
    const checkboxes = document.getElementsByName("select");

    checkboxes.forEach((checkbox) => {
      checkbox.checked = all.checked;
    })
    console.log(checkboxes);
}

// 장바구니 담기 : http 세션에 저장
function saveBasket() {
        let table = document.getElementById('basketListTable');
        let productId = document.getElementById('productId').value;
        let productName = document.getElementById('pn').innerText;
        let option = {};

        // request 위해 map -> 객체로 자료구조 변경
        // selectedMap(전역변수) : 옵션 선택 배열
        selectedMap.forEach((value, key) => {
          let optionDetail = {};

          optionDetail["color"] = value.color;
          optionDetail["size"] = value.size;
          optionDetail["count"] = value.count;

          option[key] = optionDetail;
        });

        // request 데이터
        let product = { "productId" :  productId,
                        "productName" : productName,
                        "productOptions" : option,
                        "totalPrice" : productTotalPrice
                       };

        sessionStorage.setItem("basket",JSON.stringify(product));

        /*$.ajax({
            url: '/basket/add',
            type: 'post',
            data: JSON.stringify(product),
            processData: true,
            contentType: 'application/json',
            dataType: 'text',

            success: function(data, status, xhr) {
                console.log("data : " + data + "\n" +
                            "status : " + status + "\n" +
                            "xhr : " + xhr);
            },
            error: function(xhr, status, error){
                console.log("error : " + error + "\n" +
                            "status : " + status + "\n" +
                            "xhr : " + xhr);
            }
        })*/
}