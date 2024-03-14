package com.example.shoppingmall.service;

import com.example.shoppingmall.dao.GetDetailsAndItems;
import com.example.shoppingmall.dao.OrderDetailsDao;
import com.example.shoppingmall.repository.MyOrderMapper;
import com.example.shoppingmall.repository.OrderMapper;
import com.example.shoppingmall.security.CustomerUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

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

        List<OrderDetailsDao> orderDetailsList = myOrderMapper.findOrderByCustomerId(customerUserDetails.getCustomerId());


        // file DB에 저장된 이미지들 중 한개만 Get
        /*for(GetDetailsAndItems item : orderList){
            String imageListString = item.getMainImageList();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(imageListString);
            String getImage = jsonArray.get(0).toString();
            item.setMainImageList(getImage);
        }*/

        return null;
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
