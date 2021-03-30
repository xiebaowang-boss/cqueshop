package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Mapper.GoodsMapper;
import com.xielaoban.cqueshop.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-30 11:29
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getAll() {
        return goodsMapper.getAll();
    }

    @Override
    public Goods get(String id) {
        return goodsMapper.get(id);
    }

    @Override
    public List<Goods> getByCategoryId(String categoryId) {
        return goodsMapper.getByCategoryId(categoryId);
    }

    @Override
    public int add(Goods goods) {
        return goodsMapper.add(goods);
    }
}
