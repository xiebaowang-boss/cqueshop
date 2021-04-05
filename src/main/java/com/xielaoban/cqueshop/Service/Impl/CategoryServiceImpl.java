package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.Category;
import com.xielaoban.cqueshop.Mapper.CategoryMapper;
import com.xielaoban.cqueshop.Service.CategoryService;
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
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public List<Category> getAllEnabled() {
        return categoryMapper.getAllEnabled();
    }
}
