package com.example.shoppingmall.service;

import com.example.shoppingmall.dao.GetDetailsAndItems;
import com.example.shoppingmall.repository.CustomerMapper;
import com.example.shoppingmall.security.CustomerUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MyPageService {

    private final CustomerMapper customerMapper;

    public MyPageService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    //마이페이지 : 주문내역 불러오기
    public List<GetDetailsAndItems> getMyPage(CustomerUserDetails customerUserDetails) throws ParseException {

        List<GetDetailsAndItems> orderList = customerMapper.findOrderByCustomerId(customerUserDetails.getCustomerId());

        // file DB에 저장된 이미지들 중 한개만 Get
        for(GetDetailsAndItems item : orderList){
            String imageListString = item.getMainImageList();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(imageListString);
            String getImage = jsonArray.get(0).toString();
            item.setMainImageList(getImage);
        }

        return orderList;

    }
}
