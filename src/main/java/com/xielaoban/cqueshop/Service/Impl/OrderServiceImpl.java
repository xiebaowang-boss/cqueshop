package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import com.xielaoban.cqueshop.Mapper.OrderItemMapper;
import com.xielaoban.cqueshop.Mapper.OrderMapper;
import com.xielaoban.cqueshop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 17:09
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public PageInfo<Order> getOrderByUserId(String userId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Order> orderList = orderMapper.getByUserId(userId);
        PageInfo<Order> orderPageInfo = new PageInfo<Order>(orderList);
        return orderPageInfo;
    }

    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }

    @Override
    public Boolean saveOrderAndOrderItem(Order order, OrderItem orderItem) {
        int orderResult = orderMapper.add(order);
        int orderItemResult = orderItemMapper.add(orderItem);
        return (orderResult > 0) && (orderItemResult > 0);
    }

    @Override
    public Boolean saveOrderAndOrderItemList(Order order, List<OrderItem> orderItemList) {
        int orderResult = orderMapper.add(order);
        int orderItemResult = orderItemMapper.addBatch(orderItemList);
        return (orderResult > 0) && (orderItemResult > 0);
    }
}
