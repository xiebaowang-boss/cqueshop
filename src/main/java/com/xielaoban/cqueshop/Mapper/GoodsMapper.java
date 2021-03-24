package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:30
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface GoodsMapper {
    List<Goods> getAll();
}
