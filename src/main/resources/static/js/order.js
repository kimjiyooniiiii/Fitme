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