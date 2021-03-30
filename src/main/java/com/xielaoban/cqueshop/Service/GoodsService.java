package com.xielaoban.cqueshop.Service;

import com.xielaoban.cqueshop.Entity.Goods;

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

    Goods get(String id);

    List<Goods> getByCategoryId(String categoryId);

    int add(Goods goods);
}
