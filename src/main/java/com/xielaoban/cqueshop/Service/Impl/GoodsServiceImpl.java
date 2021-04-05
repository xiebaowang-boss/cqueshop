package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Mapper.GoodsMapper;
import com.xielaoban.cqueshop.Service.GoodsService;
import com.xielaoban.cqueshop.Util.DateUtil;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    public List<Goods> getAllEnabled(){
        return goodsMapper.getAllEnabled();
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
    public int add(Goods goods) throws ParseException {
        goods.setId(GenerateUUID.getUUID());
        goods.setNowprice(goods.getOriprice());
        goods.setCreatetime(DateUtil.getCurrentDate());
        goods.setLastupdatetime(DateUtil.getCurrentDate());
        goods.setStatus(1);
        return goodsMapper.add(goods);
    }

    @Override
    public List<Goods> getHot() {
        return goodsMapper.getHot();
    }

    @Override
    /**
    * @Description: 获取首页最新商品的信息
    * @Name: getUpdateGoods
    * @Param: []
    * @return: java.util.List<com.xielaoban.cqueshop.Entity.Goods>
    * @Author: 12105
    * @Date: 2021-4-1
    * @Time: 17:28
    */
    public List<Goods> getUpdateGoods() {
        return goodsMapper.getUpdateGoods();
    }
}
