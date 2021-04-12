package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Order.SendType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 16:19
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface SendTypeMapper {
    List<SendType> getAllEnabled();
}
