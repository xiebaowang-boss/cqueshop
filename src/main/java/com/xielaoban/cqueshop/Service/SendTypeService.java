package com.xielaoban.cqueshop.Service;

import com.xielaoban.cqueshop.Entity.Order.SendType;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 16:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface SendTypeService {
    List<SendType> getAllEnabled();
}
