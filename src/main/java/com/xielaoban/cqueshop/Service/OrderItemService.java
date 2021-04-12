package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 17:19
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface OrderItemService {
    PageInfo<OrderItem> getByOrderId(String orderId, Integer pageSize, Integer currentPage);

    int add(OrderItem orderItem);

    int addBatch(List<OrderItem> orderItemList);
}
