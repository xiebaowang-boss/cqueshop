package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Category;
import com.xielaoban.cqueshop.Mapper.CategoryMapper;
import com.xielaoban.cqueshop.Service.CategoryService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 15:58
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageInfo<Category> getAll(Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Category> categoryList = categoryMapper.getAll();
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
        return categoryPageInfo;
    }

    @Override
    public List<Category> getAllEnabled() {
        return categoryMapper.getAllEnabled();
    }

    @Override
    public int add(Category category) {
        category.setId(GenerateUUID.getUUID());
        category.setStatus(1);
        return categoryMapper.add(category);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public int updateStatus(String id, Integer status) {
        return categoryMapper.updateStatus(id, status);
    }

    @Override
    public int del(String id) {
        return categoryMapper.del(id);
    }

    @Override
    public Category get(String catId) {
        return categoryMapper.get(catId);
    }
}
