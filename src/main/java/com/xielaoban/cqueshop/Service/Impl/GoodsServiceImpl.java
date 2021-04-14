package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Mapper.GoodsMapper;
import com.xielaoban.cqueshop.Service.GoodsService;
import com.xielaoban.cqueshop.Util.DateUtil;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import lombok.SneakyThrows;
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
    public PageInfo<Goods> getAll(String query, Integer pageSize, Integer currentPage) {
        if (query.isEmpty() || query == null) {
            query = null;
        } else {
            query = "%" + query + "%";
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> goodsList = goodsMapper.getAll(query);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        return pageInfo;
    }

    @Override
    public PageInfo<Goods> getAllEnabled(Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> goodsList = goodsMapper.getAllEnabled();
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        return pageInfo;
    }

    @Override
    public PageInfo<Goods> search(String keywords, Integer pageSize, Integer currentPage) {
        String newKeywords = "%" + keywords + "%";
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> goodsList = goodsMapper.getByKeywords(newKeywords);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        return pageInfo;
    }

    @Override
    public Goods get(String id) {
        return goodsMapper.get(id);
    }

    @Override
    public PageInfo<Goods> getByCategoryId(String categoryId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> goodsList = goodsMapper.getByCategoryId(categoryId);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        return pageInfo;
    }

    @Override
    public int add(Goods goods) throws ParseException {
        goods.setId(GenerateUUID.getUUID());
        goods.setNowprice(goods.getOriprice());
        goods.setCreatetime(DateUtil.getCurrentDate());
        goods.setLastupdatetime(DateUtil.getCurrentDate());
        goods.setStatus(1);
        goods.setSalesVolume(0);
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

    @Override
    public int del(String goodsId) {
        return goodsMapper.del(goodsId);
    }

    @SneakyThrows
    @Override
    public int update(Goods goods) {
        goods.setLastupdatetime(DateUtil.getCurrentDate());
        return goodsMapper.update(goods);
    }

    @Override
    public int updateStatus(String goodsId, Integer status) {
        return goodsMapper.updateStatus(goodsId, status);
    }
}
