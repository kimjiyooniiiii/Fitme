package com.example.shoppingmall.enumFile;

public enum OrderState {
    PREPARE("상품준비"),    // 상품 준비
    DELIVERY("배송중"),   // 배송 중
    CANCEL("주문취소");     // 주문 취소

    private String state;

    OrderState(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
