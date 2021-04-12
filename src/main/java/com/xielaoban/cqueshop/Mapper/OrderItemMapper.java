package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 16:44
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface OrderItemMapper {
    List<OrderItem> getByOrderId(String orderId);

    int add(OrderItem orderItem);

    int addBatch(List<OrderItem> orderItemList);

    int changeIsComm(String orderItemId);
}
