package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Goods;

import java.text.ParseException;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-30 11:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface GoodsService {
    List<Goods> getAll();

    PageInfo<Goods> getAllEnabled(Integer pageSize, Integer currentPage);

    PageInfo<Goods> search(String keywords, Integer pageSize, Integer currentPage);

    Goods get(String id);

    PageInfo<Goods> getByCategoryId(String categoryId, Integer pageSize, Integer currentPage);

    int add(Goods goods) throws ParseException;

    List<Goods> getHot();

    List<Goods> getUpdateGoods();
}
