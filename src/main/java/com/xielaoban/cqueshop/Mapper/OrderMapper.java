package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 16:25
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface OrderMapper {
    List<Order> getByUserId(String userId);

    int add(Order order);
}
