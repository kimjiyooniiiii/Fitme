package com.example.shoppingmall.service;

import com.example.shoppingmall.dao.GetDetailsAndItems;
import com.example.shoppingmall.dao.OrderDetailsDao;
import com.example.shoppingmall.dao.OrderedItemsDao;
import com.example.shoppingmall.repository.MyOrderMapper;
import com.example.shoppingmall.repository.OrderMapper;
import com.example.shoppingmall.security.CustomerUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyOrderPageService {

    private final MyOrderMapper myOrderMapper;
    private final OrderMapper orderMapper;

    public MyOrderPageService(MyOrderMapper myOrderMapper, OrderMapper orderMapper) {
        this.myOrderMapper = myOrderMapper;
        this.orderMapper = orderMapper;
    }

    //주문내역 불러오기
    public List<GetDetailsAndItems> getMyPage(CustomerUserDetails customerUserDetails) throws ParseException {

        // 전체 주문 내역
        List<OrderDetailsDao> orderDetailsList = myOrderMapper.findOrderByCustomerId(customerUserDetails.getCustomerId());
        // 클라이언트 반환 형식
        List<GetDetailsAndItems> detailsAndItems = new ArrayList<>();

        for(OrderDetailsDao o : orderDetailsList){
            // 주문 내역 안의 상품 리스트
            List<OrderedItemsDao> itemList = o.getItemsDaoList();

            for(OrderedItemsDao i : itemList){
                detailsAndItems.add(GetDetailsAndItems.builder()
                        .productId(i.getProductId())
                        .productName(i.getProductName())
                        .options(i.getOptions())
                        .orderDetailsId(o.getOrderDetailsId())
                        .orderTotalPrice(o.getOrderTotalPrice())
                        .receiver(o.getReceiver())
                        .orderState(o.getOrderState())
                        .productPrice(i.getProductPrice())
                        .phone(o.getPhone())
                        .payment(o.getPayment())
                        .zipCode(o.getZipCode())
                        .address(o.getAddress())
                        .orderDateTime(o.getOrderDateTime())
                        //.mainImageList(getImage)
                        .build());
            }
        }

        // file DB에 저장된 이미지들 중 한개만 Get
//        for(GetDetailsAndItems item : orderDetailsList){
//            String imageListString = item.getMainImageList();
//            JSONParser parser = new JSONParser();
//            JSONArray jsonArray = (JSONArray) parser.parse(imageListString);
//            String getImage = jsonArray.get(0).toString();
//            item.setMainImageList(getImage);
//        }

        return detailsAndItems;
    }

    // 주문 취소하기
    public boolean cancelOrder(String orderId) {
        String orderState = orderMapper.findOrderStateByOrderId(orderId);
        // "상품 준비" 중이면 취소 가능, 그 외에는 취소 불가능
        if(orderState.equals("상품 준비")){
            orderMapper.cancelOrderByOrderId(orderId);
            return true;
        }else{
            return false;
        }
    }
}
