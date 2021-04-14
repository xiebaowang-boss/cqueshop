package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<Goods> getAllEnabled();

    Goods get(String id);

    List<Goods> getByCategoryId(String categoryId);

    List<Goods> getHot();

    List<Goods> getUpdateGoods();

    List<Goods> getByKeywords(String keywords);

    int add(Goods goods);

    List<Goods> getAll(String query);

    int update(Goods goods);

    int updateStatus(String goodsId, Integer status);

    int del(String goodsId);
}
