package com.xielaoban.cqueshop.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Entity.Cart.Cart;
import com.xielaoban.cqueshop.Entity.Cart.CartItem;
import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 17:32
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Util
 * @Description
 */

public class CartConverterUtil {
    public static Order transferToOrder(Cart cart, JSONObject orderInfo) throws ParseException {
        Order order = new Order();
        order.setId(GenerateUUID.getUUID());
        order.setTotalNum(cart.getTotalNum());
        order.setTotalPrice(cart.getTotalPrice());
        order.setCreateDate(DateUtil.getCurrentDate());
        order.setStatus(1);
        order.setName(orderInfo.getString("name"));
        order.setAddress(orderInfo.getString("address"));
        order.setPhone(orderInfo.getString("phone"));
        order.setRemarks(orderInfo.getString("remarks"));
        return order;
    }

    public static List<OrderItem> getOrderItemList(String orderId, Cart cart) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItemMap().values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(GenerateUUID.getUUID());
            orderItem.setGoods(cartItem.getGoods());
            orderItem.setNum(cartItem.getNum());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setOrderId(orderId);
            orderItem.setIsComm(0);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }
}
