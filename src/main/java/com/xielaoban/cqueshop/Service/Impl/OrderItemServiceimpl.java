package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import com.xielaoban.cqueshop.Mapper.OrderItemMapper;
import com.xielaoban.cqueshop.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 17:21
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class OrderItemServiceimpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public PageInfo<OrderItem> getByOrderId(String orderId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<OrderItem> orderItemList = orderItemMapper.getByOrderId(orderId);
        PageInfo<OrderItem> orderItemPageInfo = new PageInfo<>(orderItemList);
        return orderItemPageInfo;
    }

    @Override
    public int add(OrderItem orderItem) {
        return orderItemMapper.add(orderItem);
    }

    @Override
    public int addBatch(List<OrderItem> orderItemList) {
        return orderItemMapper.addBatch(orderItemList);
    }
}
