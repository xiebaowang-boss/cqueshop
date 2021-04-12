package com.xielaoban.cqueshop.Entity.Order;

import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-6 15:51
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Integer totalNum;
    private Double totalPrice;
    private Integer status;
    private String address;
    private String name;
    private String phone;
    private Date createDate;
    private String remarks;
    private User orderUser;
    private SendType sendType;
}
