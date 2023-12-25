package com.example.shoppingmall.enumFile;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(OrderState.class)
public class OrderStateTypeHandler implements TypeHandler<OrderState> {
    @Override
    // 어떤 값을 DB에 저장할지 설정
    public void setParameter(PreparedStatement ps, int i, OrderState parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getState());
    }

    @Override
    // 저장된 DB 값으로 실제 객체 반환하기
    public OrderState getResult(ResultSet rs, String columnName) throws SQLException {
        String state = rs.getString(columnName);
        return getOrderState(state);
    }

    @Override
    public OrderState getResult(ResultSet rs, int columnIndex) throws SQLException {
        String state = rs.getString(columnIndex);
        return getOrderState(state);
    }

    @Override
    public OrderState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String state = cs.getString(columnIndex);
        return getOrderState(state);
    }

    private OrderState getOrderState(String state){
        OrderState orderState = null;

        switch (state) {
            case "상품준비":
                orderState = OrderState.PREPARE;
                break;

            case "배송중":
                orderState = OrderState.DELIVERY;
                break;

            case "주문취소":
                orderState = OrderState.CANCEL;
                break;
        }
        return orderState;
    }
}
