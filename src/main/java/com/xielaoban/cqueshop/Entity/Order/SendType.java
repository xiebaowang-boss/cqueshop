package com.xielaoban.cqueshop.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 16:17
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity.Order
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SendType {
    private String id;
    private String name;
    private int status;
}
