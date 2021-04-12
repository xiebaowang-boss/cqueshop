package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 17:03
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface OrderService {
    PageInfo<Order> getOrderByUserId(String userId, Integer pageSize, Integer currentPage);

    int add(Order order);

    @Transactional
    Boolean saveOrderAndOrderItem(Order order, OrderItem orderItem);

    @Transactional
    Boolean saveOrderAndOrderItemList(Order order, List<OrderItem> orderItemList);
}
