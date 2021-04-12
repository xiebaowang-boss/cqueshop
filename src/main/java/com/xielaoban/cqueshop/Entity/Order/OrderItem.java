package com.xielaoban.cqueshop.Entity.Order;

import com.xielaoban.cqueshop.Entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 16:01
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity.Order
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private String id;
    private Goods goods;
    private Integer num;
    private Double totalPrice;
    private String orderId;
    private Integer isComm;
}
